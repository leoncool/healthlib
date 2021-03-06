package health.database.DAO.nosql;

import health.database.DAO.DatastreamDAO;
import health.hbase.models.HBaseDataImport;
import health.input.jsonmodels.JsonCloudStorageFile;
import health.input.jsonmodels.JsonDataPoints;
import health.input.jsonmodels.JsonDataValues;
import health.input.jsonmodels.JsonDatastreamUnits;
import health.input.jsonmodels.singleunitstream.JsonSingleDataPoints;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import server.exception.ErrorCodeException;
import util.AllConstants;
import util.DateUtil;
import util.HBaseConfig;
import util.ServerConfigUtil;
import cloudstorage.cacss.S3Engine;

import com.zhumulangma.cloudstorage.server.entity.CloudFile;

/**
 * 
 * @author Leon
 */
public class HBaseDatapointDAO implements DatapointDAOInterface {

	private final byte[] PROP_COL = Bytes.toBytes("p");
	private final byte[] VALUE_COL = Bytes.toBytes("v");
	private final byte[] VALUE_TAG_COL = Bytes.toBytes("vt");
	private final byte[] TIME_TAG = Bytes.toBytes("tt");
	private final byte[] BLOCK_ID = Bytes.toBytes("b");
	private final String health_book = "hb";
	private final String str_unassignBlockID = "noid";
	private final byte[] unassignBlockID = Bytes.toBytes(str_unassignBlockID);

	// create 'hb','p','v','vt'
	public HBaseDatapointDAO() {

		// System.out.println("getinConfig..");
		// // HBaseAdmin admin = new HBaseAdmin(config);
		// // System.out.println("initualizeing......admin..");
		// // if (!admin.tableExists(health_book)) {
		// // System.out.println("initualizeing......createTable..");
		// // createTable();
		// // }
		// System.out.println("initualizeing......DONE!..");
	}

	public void testTable() throws ErrorCodeException {
		HTableInterface table;
		try {
			table = HBaseConfig.getTable(health_book);
			Put put = new Put(toBytes("asdfsadf"));
			put.add(PROP_COL, toBytes("acasc"), toBytes("acasc"));
			table.put(put);
			table.flushCommits();
			Get get = new Get(toBytes("asdfsadf"));
			System.out.println(table.get(get));
		} catch (ErrorCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}
	}

	public void createTable() throws ErrorCodeException {
		try {
			HBaseConfig hbaseconfig = new HBaseConfig();
			Configuration config = hbaseconfig.getConfig();
			HBaseAdmin admin = new HBaseAdmin(config);
			HTableDescriptor desc = new HTableDescriptor(health_book);
			HColumnDescriptor prop = new HColumnDescriptor(PROP_COL);
			HColumnDescriptor value_col = new HColumnDescriptor(VALUE_COL);
			HColumnDescriptor val_tag_col = new HColumnDescriptor(VALUE_TAG_COL);
			desc.addFamily(prop);
			desc.addFamily(val_tag_col);
			desc.addFamily(value_col);
			admin.createTable(desc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}
	}

	public int importDatapoints(HBaseDataImport importData,
			String newBlockName, String newBlockDecs) throws ErrorCodeException {
		try {
			DatastreamDAO dsDAO = new DatastreamDAO();
			return importDatapointsDatapoints(importData);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}
	}

	public int importDatapointsDatapoints(HBaseDataImport importData)
			throws ErrorCodeException {
		HTableInterface table = null;
		try {
			int dataCounter = 0;
			table = HBaseConfig.getTable(health_book);
			List<Put> putList = new ArrayList<Put>();
			if (importData.getDatastream_id() == null
					|| importData.getDatastream_id().length() < 5) {
				throw new ErrorCodeException(
						AllConstants.ErrorDictionary.MISSING_DATA);
			}
			if (importData.getData_points() != null
					&& importData.getData_points().size() > 0) {
				List<JsonDataPoints> dataPoints = importData.getData_points();
				for (int i = 0; i < dataPoints.size(); i++) {
					long longAt = 0;
					boolean isNumeric = dataPoints.get(i).getAt()
							.matches("[0-9]+");
					try {
						if (isNumeric) {
							longAt = Long.parseLong(dataPoints.get(i).getAt());
							// System.out.println("from long:longAt:"+longAt);
						} else {
							DateUtil dateUtil = new DateUtil();
							Date at = dateUtil.convert_SetLenient(dataPoints
									.get(i).getAt(), dateUtil.utcFormat);
							longAt = at.getTime();
							System.out.println("fromUTC:longAt:" + longAt);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						throw new ErrorCodeException(
								AllConstants.ErrorDictionary.Input_data_contains_invalid_date_format);
					}
					// server.conf.Utils.log(importData.getStreamID() + "/" +
					// Long.toString(longAt));
					byte[] rowKey = toBytes(importData.getDatastream_id() + "/"
							+ Long.toString(longAt));
					dataCounter = dataCounter + rowKey.length;
					Put put = new Put(rowKey);
					List<JsonDataValues> dataValues = dataPoints.get(i)
							.getValue_list();
					for (JsonDataValues value : dataValues) {

						if (!check_ExistUnitID(value.getUnit_id(), importData
								.getDatastream().getUnits_list())) {
							System.out.println("-----value.getUnit_id():"+value.getUnit_id()+"-------");
							throw new ErrorCodeException(
														AllConstants.ErrorDictionary.Input_data_contains_invalid_unit_id);
						}
						try {
							put.add(VALUE_COL, toBytes(value.getUnit_id()),
									toBytes(value.getVal()));
							dataCounter = dataCounter + rowKey.length
									+ VALUE_COL.length
									+ toBytes(value.getUnit_id()).length
									+ toBytes(value.getVal()).length;
							if (value.getVal_tag() != null) {
								// System.out.println("debug 2:saving:"
								// + value.getUnit_id()+","+value.getVal_tag());
								put.add(VALUE_TAG_COL,
										toBytes(value.getUnit_id()),
										toBytes(value.getVal_tag()));
								dataCounter = dataCounter
										+ rowKey.length
										+ VALUE_TAG_COL.length
										+ toBytes(value.getUnit_id()
												+ value.getVal_tag()).length;
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							throw new ErrorCodeException(
									AllConstants.ErrorDictionary.Internal_Fault);
						}
					}

					if (importData.getBlock_id() != null) {
						put.add(PROP_COL, BLOCK_ID,
								toBytes(importData.getBlock_id()));
						dataCounter = dataCounter + rowKey.length
								+ PROP_COL.length + BLOCK_ID.length
								+ toBytes(importData.getBlock_id()).length;
					} else {
						put.add(PROP_COL, BLOCK_ID, unassignBlockID);
						dataCounter = dataCounter + rowKey.length
								+ PROP_COL.length + BLOCK_ID.length
								+ unassignBlockID.length;
					}
					if (dataPoints.get(i).getTimetag() != null) {
						put.add(PROP_COL, TIME_TAG, toBytes(dataPoints.get(i)
								.getTimetag()));
						dataCounter = dataCounter
								+ rowKey.length
								+ PROP_COL.length
								+ TIME_TAG.length
								+ toBytes(dataPoints.get(i).getTimetag()).length;
					}
					putList.add(put);
				}
			} else if (importData.getData_points_single_list() != null
					&& importData.getData_points_single_list().size() > 0) {
				String single_unitID = importData.getSingle_Unit_ID();
				System.out.println("singal unit id:" + single_unitID);
				// for single datapoints format
				List<JsonSingleDataPoints> dataPoints = importData
						.getData_points_single_list();
				for (int i = 0; i < dataPoints.size(); i++) {
					long longAt = 0;
					try {
						longAt = Long.parseLong(dataPoints.get(i).getAt());

					} catch (NumberFormatException ex) {
						ex.printStackTrace();
						throw new ErrorCodeException(
								AllConstants.ErrorDictionary.INPUT_DATE_FORMAT_ERROR);
					}
					// server.conf.Utils.log(importData.getStreamID() + "/" +
					// Long.toString(longAt));
					byte[] rowKey = toBytes(importData.getDatastream_id() + "/"
							+ Long.toString(longAt));
					dataCounter = dataCounter + rowKey.length;
					Put put = new Put(rowKey);
					// System.out.println("Single data point import, value:"+dataPoints.get(i).getVal());
					put.add(VALUE_COL, toBytes(single_unitID),
							toBytes(dataPoints.get(i).getVal()));
					dataCounter = dataCounter + rowKey.length
							+ VALUE_COL.length + toBytes(single_unitID).length
							+ toBytes(dataPoints.get(i).getVal()).length;
					if (dataPoints.get(i).getVal_tag() != null) {
						put.add(VALUE_TAG_COL, toBytes(single_unitID),
								toBytes(dataPoints.get(i).getVal_tag()));
						dataCounter = dataCounter
								+ rowKey.length
								+ VALUE_TAG_COL.length
								+ toBytes(single_unitID
										+ dataPoints.get(i).getVal_tag()).length;
					}

					if (importData.getBlock_id() != null) {
						put.add(PROP_COL, BLOCK_ID,
								toBytes(importData.getBlock_id()));
						dataCounter = dataCounter + rowKey.length
								+ PROP_COL.length + BLOCK_ID.length
								+ toBytes(importData.getBlock_id()).length;
					} else {
						put.add(PROP_COL, BLOCK_ID, unassignBlockID);
						dataCounter = dataCounter + rowKey.length
								+ PROP_COL.length + BLOCK_ID.length
								+ unassignBlockID.length;
					}
					putList.add(put);
				}
			} else {
				throw new ErrorCodeException(
						AllConstants.ErrorDictionary.No_Input_Single_Datapoints);
			}

			table.put(putList);
			table.flushCommits();
			table.close();
			// HBaseConfig.putTable(table);
			return dataCounter;
		} catch (IOException ex) {
			// HBaseConfig.putTable(table);
			// TODO: handle exception
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}

	}

	public HBaseDataImport exportDatapoints(String streamID, Long start,
			Long end, String blockID, HashMap<String, String> dsUnitsList,
			SimpleDateFormat format, HashMap<String, Object> settings)
			throws ErrorCodeException {

		try {
			HTableInterface table = HBaseConfig.getTable(health_book);
			Date timerStart = new Date();
			System.out.println("starting Exporting...." + timerStart);
			Scan scan = new Scan();
			scan.setCaching(1000);
			int max = 1000;
			if (settings != null
					&& settings
							.get(AllConstants.ProgramConts.exportSetting_MAX) != null) {
				max = (Integer) settings
						.get(AllConstants.ProgramConts.exportSetting_MAX);
			}
			// scan.setStartRow(toBytes(streamID + "/" + Long.toString(start)));
			// scan.setCacheBlocks(true);
			FilterList filterList = new FilterList();
			if (start != null && start != 0) {
				// RowFilter fowFilter_startDate = new RowFilter(
				// CompareFilter.CompareOp.GREATER_OR_EQUAL,
				// new BinaryComparator(toBytes(streamID + "/"
				// + Long.toString(start))));
				// filterList.addFilter(fowFilter_startDate);
				scan.setStartRow(toBytes(streamID + "/" + Long.toString(start)));
			} else {
				scan.setStartRow(toBytes(streamID + "/" + Long.toString(0)));
			}
			if (end != null && end != 0) {
				scan.setStopRow(toBytes(streamID + "/" + Long.toString(end)));
			} else {
				scan.setStopRow(toBytes(streamID + "/"
						+ Long.toString(Long.MAX_VALUE)));
			}
			// if((start == null || start == 0)&&(end==null ||end == 0))
			// {
			RowFilter rowFilterStreamID = new RowFilter(
					CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(
							toBytes(streamID)));
			filterList.addFilter(rowFilterStreamID);
			System.out
					.println("----------------use rowFilterStreamid--------------");
			// }
			if (blockID != null
					&& !blockID.equalsIgnoreCase(str_unassignBlockID)) {
				SingleColumnValueFilter blockIDfilter = new SingleColumnValueFilter(
						PROP_COL, BLOCK_ID, CompareFilter.CompareOp.EQUAL,
						toBytes(blockID));
				blockIDfilter.setFilterIfMissing(true);
				filterList.addFilter(blockIDfilter);
			}
			if (dsUnitsList != null && dsUnitsList.size() > 0) {
				Set<String> entrySet = dsUnitsList.keySet();
				Iterator<String> itr = entrySet.iterator();
				while (itr.hasNext()) {
					String id = itr.next();
					// System.out.println("unitid debug po1:"+id);
					scan.addColumn(VALUE_COL, toBytes(id));
					scan.addColumn(VALUE_TAG_COL, toBytes(id));
					System.out.println("adding unitID for scanning:"+id);
				}
				scan.addFamily(PROP_COL);
				scan.addFamily(VALUE_TAG_COL);
				scan.addFamily(VALUE_COL);
			}
			scan.setFilter(filterList);

			// long rowCount = aggregationClient.rowCount(toBytes(health_book),
			// null, scan);
			// System.out.println("totalnumber of rows:"+rowCount);
			ResultScanner scanner = table.getScanner(scan);
			Iterator<Result> itr = scanner.iterator();

			List<JsonDataPoints> jsonDPList = new ArrayList<JsonDataPoints>();
			System.out.println("Just After scanner...."
					+ (new Date().getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds");
			int counter = 0;
			boolean truncated = false;
			while (itr.hasNext()) {
				counter++;

				// if (counter > max) {
				// continue;
				// }
				Result res = itr.next();
				JsonDataPoints datapoint = new JsonDataPoints();
				List<JsonDataValues> hbaseDatavalueList = toJsonDatavaluesModel(
						res, dsUnitsList);
				// System.out.println("List<JsonDataValues>:" +
				// hbaseDatavalueList.size());
				if (hbaseDatavalueList != null) {
					datapoint.setValue_list(hbaseDatavalueList);
				}
				if (format == null) {
					// date.setTime(Long.parseLong(getTimefromRowKey(res)));
					datapoint.setAt(getTimefromRowKey(res));
				} else {
					// Date date=new Date();
					// date.setTime(Long.parseLong(getTimefromRowKey(res)));
					DateUtil dateUtil = new DateUtil();
					Date date = new Date();
					date.setTime(Long.parseLong(getTimefromRowKey(res)));
					try {
						datapoint.setAt(dateUtil.format(date,
								dateUtil.utcFormat));
					} catch (Exception ex) {
						ex.printStackTrace();
						throw new ErrorCodeException(
								AllConstants.ErrorDictionary.HBase_Internal_Error);
					}
				}
				if (counter <= 1) {
					// System.out.println(counter+",First Date from Export:"+Long.parseLong(getTimefromRowKey(res)));
					// Date date = new Date();
					// date.setTime(Long.parseLong(getTimefromRowKey(res)));
					// System.out.println(counter+",First Date from Export long:"+date.getTime());
				}
				if (res.getValue(PROP_COL, TIME_TAG) != null) {
					datapoint.setTimetag(toString(res.getValue(PROP_COL,
							TIME_TAG)));
				}

				// jsonDPList.add(datapoint);
				if (counter == max) {
					jsonDPList.add(datapoint);
					continue;
				} else if (counter > max) {
					truncated = true;
					break;
				} else {
					jsonDPList.add(datapoint);
				}
			}

			System.out.println("Just After While loop...."
					+ (new Date().getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds"+",counter:"+counter);
			HBaseDataImport dataexport = null;
			if (jsonDPList.size() > 0) {
				dataexport = new HBaseDataImport();
				dataexport.setBlock_id(blockID);
				dataexport.setData_points(jsonDPList);
				dataexport.setDatastream_id(streamID);
				dataexport.setDeviceid(streamID);
				String truncated_json = truncated ? "true" : "false";
				dataexport.setTruncated(truncated_json);
			}
			Date timerEnd = new Date();
			System.out.println("Finished Exporting...." + timerEnd);
			System.out.println("Finished Exporting...."
					+ (timerEnd.getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds");
			scanner.close();
			table.close();
			// HBaseConfig.putTable(table);
			return dataexport;
		} catch (IOException ex) {
			// HBaseConfig.putTable(table);
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		} catch (NumberFormatException ex) {
			// HBaseConfig.putTable(table);
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}

	}

	public HBaseDataImport exportDatapointsForSingleUnit(String streamID,
			Long start, Long end, String blockID, String unitID,
			SimpleDateFormat format, HashMap<String, Object> settings)
			throws ErrorCodeException {
		HTableInterface table = null;
		try {
			Date timerStart = new Date();
			System.out.println("single unit export.....starting Exporting...."
					+ timerStart);
			table = HBaseConfig.getTable(health_book);
			Scan scan = new Scan();
			scan.setCaching(1000);
			// scan.setBatch(1000);

			// scan.setCacheBlocks(true);
			FilterList filterList = new FilterList();

			if (start != null && start != 0) {
				// RowFilter fowFilter_startDate = new RowFilter(
				// CompareFilter.CompareOp.GREATER_OR_EQUAL,
				// new BinaryComparator(toBytes(streamID + "/"
				// + Long.toString(start))));
				// filterList.addFilter(fowFilter_startDate);
				scan.setStartRow(toBytes(streamID + "/" + Long.toString(start)));
			} else {
				scan.setStartRow(toBytes(streamID + "/" + Long.toString(0)));// setup
																				// minimum
																				// for
																				// speeding
																				// up
																				// requests
			}
			if (end != null && end != 0) {
				scan.setStopRow(toBytes(streamID + "/" + Long.toString(end)));
			} else {
				scan.setStopRow(toBytes(streamID + "/"
						+ Long.toString(Long.MAX_VALUE)));// setup maximum for
															// speeding up
															// requests
			}
			if ((start == null || start == 0) && (end == null || end == 0)) {
				RowFilter rowFilterStreamID = new RowFilter(
						CompareFilter.CompareOp.EQUAL,
						new BinaryPrefixComparator(toBytes(streamID)));
				filterList.addFilter(rowFilterStreamID);
			}
			if (blockID != null
					&& !blockID.equalsIgnoreCase(str_unassignBlockID)) {
				SingleColumnValueFilter blockIDfilter = new SingleColumnValueFilter(
						PROP_COL, BLOCK_ID, CompareFilter.CompareOp.EQUAL,
						toBytes(blockID));
				blockIDfilter.setFilterIfMissing(true);
				filterList.addFilter(blockIDfilter);
			}
			scan.addColumn(VALUE_COL, toBytes(unitID));
			scan.addColumn(VALUE_TAG_COL, toBytes(unitID));

			scan.addFamily(PROP_COL);
			scan.addFamily(VALUE_TAG_COL);
			scan.setFilter(filterList);

			// long rowCount = aggregationClient.rowCount(toBytes(health_book),
			// null, scan);
			// System.out.println("totalnumber of rows:"+rowCount);
			ResultScanner scanner = table.getScanner(scan);
			Iterator<Result> itr = scanner.iterator();

			List<JsonSingleDataPoints> jsonDPList = new ArrayList<JsonSingleDataPoints>();
			System.out.println("Just After scanner...."
					+ (new Date().getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds");
			int counter = 0;
			while (itr.hasNext()) {
				counter++;
				if (counter > 5000) {
					continue;
				}
				Result res = itr.next();
				JsonSingleDataPoints datapoint = new JsonSingleDataPoints();
				if (res.getValue(VALUE_COL, toBytes(unitID)) != null) {
					datapoint.setVal(toString(res.getValue(VALUE_COL,
							toBytes(unitID))));
					if (format == null) {
						// date.setTime(Long.parseLong(getTimefromRowKey(res)));
						datapoint.setAt(getTimefromRowKey(res));
					} else {
						// Date date=new Date();
						// date.setTime(Long.parseLong(getTimefromRowKey(res)));
						datapoint.setAt(format.format(getTimefromRowKey(res)));
					}
				} else {
					continue;
				}
				if (counter <= 1) {
					// System.out.println(counter+",First Date from Export:"+Long.parseLong(getTimefromRowKey(res)));
					Date date = new Date();
					date.setTime(Long.parseLong(getTimefromRowKey(res)));
					// System.out.println(counter+",First Date from Export long:"+date.getTime());
				}
				if (res.getValue(PROP_COL, TIME_TAG) != null) {
					datapoint.setVal_tag(toString(res.getValue(PROP_COL,
							TIME_TAG)));
					// System.out.println("debug 11:value tag:"
					// + toString(res.getValue(PROP_COL, TIME_TAG)));
				}
				if (res.getValue(VALUE_TAG_COL, toBytes(unitID)) != null) {
					datapoint.setVal_tag(toString(res.getValue(VALUE_TAG_COL,
							toBytes(unitID))));
				}
				jsonDPList.add(datapoint);
			}
			System.out.println("SingleUnit, Just After While loop...."
					+ (new Date().getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds" + ",number of records:" + counter);
			HBaseDataImport dataexport = null;
			if (jsonDPList.size() > 0) {
				dataexport = new HBaseDataImport();
				dataexport.setBlock_id(blockID);
				dataexport.setData_points_single_list(jsonDPList);
				dataexport.setDatastream_id(streamID);
				dataexport.setDeviceid(streamID);
			}
			Date timerEnd = new Date();
			System.out.println("Finished Exporting...." + timerEnd);
			System.out.println("Finished Exporting...."
					+ (timerEnd.getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds");
			scanner.close();
			// HBaseConfig.putTable(table);
			table.close();
			return dataexport;
		} catch (IOException ex) {
			// HBaseConfig.putTable(table);
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		} catch (NumberFormatException ex) {
			// HBaseConfig.putTable(table);
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}

	}

	public String getTimefromRowKey(Result result) {
		byte[] rowbytes = result.getRow();
		String rowKey = toString(rowbytes);
		String[] values = rowKey.split("/");
		// Date date=new Date(Long.parseLong(values[1]));
		return values[1];
	}

	public String getStreamIDfromRowKey(Result result) {
		byte[] rowbytes = result.getRow();
		String rowKey = toString(rowbytes);
		String[] values = rowKey.split("/");
		return values[0];
	}

	public List<JsonDataValues> toJsonDatavaluesModel(Result res,
			HashMap<String, String> dsUnitsList) {
		NavigableMap<byte[], byte[]> familyMap = res.getFamilyMap(VALUE_COL);
		Set<Map.Entry<byte[], byte[]>> entries = (Set<Map.Entry<byte[], byte[]>>) (Set<Map.Entry<byte[], byte[]>>) familyMap
				.entrySet();
		Iterator<Entry<byte[], byte[]>> itrFamily = entries.iterator();
		List<JsonDataValues> valueList = new ArrayList<JsonDataValues>();

		/*
		 * NavigableMap<byte[], byte[]> familyMapValueTag =
		 * res.getFamilyMap(VALUE_TAG_COL); Set<Map.Entry<byte[], byte[]>>
		 * entriesValueTag = (Set<Map.Entry<byte[], byte[]>>)
		 * (Set<Map.Entry<byte[], byte[]>>) familyMapValueTag .entrySet();
		 * Iterator<Entry<byte[], byte[]>> itrFamilyValueTag =
		 * entriesValueTag.iterator(); while(itrFamilyValueTag.hasNext()) {
		 * Map.Entry<byte[], byte[]> data = (Map.Entry<byte[], byte[]>)
		 * itrFamily .next();
		 * System.out.println("finalDebug:"+toString(data.getKey())); }
		 */

		while (itrFamily.hasNext()) {
			Map.Entry<byte[], byte[]> data = (Map.Entry<byte[], byte[]>) itrFamily
					.next();
			// System.out.println("Row:" + toString(res.getRow()) + "," +
			// toString(data.getKey()) + "," + toString(data.getValue()));

			JsonDataValues value = new JsonDataValues();
			value.setUnit_id(toString(data.getKey()));
			value.setVal(toString(data.getValue()));
			// System.out.println("debug 33: data.getKey():"+data.getKey()+",resGetValue:"+res.getValue(VALUE_TAG_COL,
			// data.getKey()));
			// System.out.println("debug 44: Unit ID:"+toString(data.getKey())+",Unit Value:"+toString(data.getValue()));
			// if (res.getValue(VALUE_TAG_COL, data.getKey()) != null) {
			// value.setVal_tag(toString(res.getValue(VALUE_TAG_COL,
			// data.getKey())));
			// }

			if (dsUnitsList != null && dsUnitsList.size() > 0) {
				if (dsUnitsList.containsKey(value.getUnit_id())) {
					// System.out.println("adding:"+dsUnitsList.get(toString(data.getKey())));
					// if(res.getValue(VALUE_TAG_COL,
					// toBytes(value.getUnit_id()))!=null)
					// {
					// }
					value.setVal_tag(toString(res.getValue(VALUE_TAG_COL,
							toBytes(value.getUnit_id()))));
					// System.out.println("debug 45:value tag:"+toString(res.getValue(VALUE_TAG_COL,
					// toBytes(value.getUnit_id()))));
					valueList.add(value);
				} else {
					// System.out.println("not included" +
					// toString(data.getKey()));
				}
			} else {
				// System.out.println("is null and -----------");
				value.setVal_tag(toString(res.getValue(VALUE_TAG_COL,
						toBytes(value.getUnit_id()))));
				// System.out.println("debug 46:value tag:"+toString(res.getValue(VALUE_TAG_COL,
				// toBytes(value.getUnit_id()))));
				valueList.add(value);
			}
		}
		// System.out.println("valueList size:" + valueList.size());
		return valueList;
	}

	public long delete_A_Datapoint(String streamID, long at,
			List<String> unitIDList, String loginID) throws ErrorCodeException {
		
		try {
			Hashtable<String, Object> returnValues = null;
			String bucketName = ServerConfigUtil
					.getConfigValue(AllConstants.ServerConfigs.CloudStorageBucket);
			returnValues = (Hashtable<String, Object>) S3Engine.s3.GetBucket(
					bucketName, "leoncool", null, null, 100000, loginID + "/"
							+ streamID + "/" + at);
			List<CloudFile> list = (List<CloudFile>) returnValues.get("data");
			List<JsonCloudStorageFile> csFiles = new ArrayList<JsonCloudStorageFile>();
			for (CloudFile file : list) {
				JsonCloudStorageFile csFile = new JsonCloudStorageFile();
				String absKey = (String) file.get(CloudFile.NAME);
				S3Engine.s3.DeleteObject(bucketName, "leoncool", absKey, null);
			}
			delete_A_Datapoint(streamID,at,unitIDList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}

	public long delete_A_Datapoint(String streamID, long at,
			List<String> unitIDList) throws ErrorCodeException {
		HTableInterface table = null;
		try {
			Date timerStart = new Date();
			System.out.println("starting Deleting...." + at);
			table = HBaseConfig.getTable(health_book);
			// Scan scan = new Scan();
			// scan.setCaching(100);
			// scan.setStartRow(toBytes(streamID + "/" + Long.toString(start)));
			// scan.setCacheBlocks(true);
			// FilterList filterList = new FilterList();
			// RowFilter rowFilterStreamID = new RowFilter(
			// CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(
			// toBytes(streamID)));
			// filterList.addFilter(rowFilterStreamID);
			//
			// RowFilter fowFilter_startDate = new RowFilter(
			// CompareFilter.CompareOp.EQUAL, new BinaryComparator(
			// toBytes(streamID + "/" + Long.toString(at))));
			// filterList.addFilter(fowFilter_startDate);

			Delete delete = new Delete(toBytes(streamID + "/"
					+ Long.toString(at)));

			table.delete(delete);

			table.close();
			// HBaseConfig.putTable(table);
			HBaseDataImport dataexport = null;
			Date timerEnd = new Date();
			System.out.println("Finished Deleting...." + timerEnd);
			System.out.println("Finished Deleting...."
					+ (timerEnd.getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds");
			return 1;
		} catch (IOException ex) {
			// HBaseConfig.putTable(table);
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}
	}

	public long delete_range_Datapoint(String streamID, long start, long end)
			throws ErrorCodeException {
		HTableInterface table = null;
		try {
			Date timerStart = new Date();
			System.out.println("Range Delete streamID:" + streamID
					+ ", starting Deleting....start:" + start + ",end:" + end);
			table = HBaseConfig.getTable(health_book);
			Scan scan = new Scan();
			scan.setCaching(200000);
			// scan.setStartRow(toBytes(streamID + "/" + Long.toString(start)));
			// scan.setCacheBlocks(true);
			FilterList filterList = new FilterList();
			RowFilter rowFilterStreamID = new RowFilter(
					CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(
							toBytes(streamID)));
			filterList.addFilter(rowFilterStreamID);
			if (start >= 0) {
				RowFilter fowFilter_startDate = new RowFilter(
						CompareFilter.CompareOp.GREATER_OR_EQUAL,
						new BinaryComparator(toBytes(streamID + "/"
								+ Long.toString(start))));
				filterList.addFilter(fowFilter_startDate);
			}

			if (end > 0) {
				RowFilter fowFilter_endDate = new RowFilter(
						CompareFilter.CompareOp.LESS_OR_EQUAL,
						new BinaryComparator(toBytes(streamID + "/"
								+ Long.toString(end))));
				filterList.addFilter(fowFilter_endDate);
			}

			scan.setFilter(filterList);
			
			ResultScanner scanner = table.getScanner(scan);
			Iterator<Result> itr = scanner.iterator();
			List<Delete> deleteList = new ArrayList<>();
			long no_deleted = 0;
			while (itr.hasNext()) {
				Result res = itr.next();
				if(no_deleted<10){
				System.out.println("scanning..." + toString(res.getRow()));
				}
				Delete delete = new Delete(res.getRow());
				deleteList.add(delete);
				no_deleted++;
			}
			System.out.println("scanning deleting finished..."+new Date());
			table.delete(deleteList);
			table.close();
			System.out.println("deleting finished..."+new Date());
			// HBaseConfig.putTable(table);
			HBaseDataImport dataexport = null;
			Date timerEnd = new Date();
			System.out.println("Finished Deleting...." + timerEnd);
			System.out.println("Finished Deleting...."
					+ (timerEnd.getTime() - timerStart.getTime()) / (1000.00)
					+ "seconds");
			return no_deleted;
		} catch (IOException ex) {
			// HBaseConfig.putTable(table);
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}
	}

	public static void main(String args[]) throws MasterNotRunningException,
			IOException, ParseException, ErrorCodeException {
		// DataPointDAO importDao = new DataPointDAO();
		// long start =
		// DateUtil.parseMillisecFormatToLong("21:49:59.725 16/11/2003");
		// long end =
		// DateUtil.parseMillisecFormatToLong("21:49:59.722 16/11/2012");
		// System.out.println(Long.toString(start));

		// importDao.exportDatapoints("70376e83-10b4-4de8-bde9-989e3111cf69",
		// start, end, null);
		HBaseDatapointDAO importDao = new HBaseDatapointDAO();
		importDao.testTable();
	}

	public byte[] toBytes(String str) {
		return Bytes.toBytes(str);
	}

	public String toString(byte[] bytes) {
		return Bytes.toString(bytes);
	}

	public boolean check_ExistUnitID(String unitID,
			List<JsonDatastreamUnits> junitList) {
		boolean exist = false;
		if (junitList == null || junitList.size() < 1)
			return false;
		else {
			for (JsonDatastreamUnits junit : junitList) {
				if (junit.getUnit_id().equals(unitID)) {
					exist = true;
				}
			}
		}
		return exist;
	}
}