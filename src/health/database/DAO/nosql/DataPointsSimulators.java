package health.database.DAO.nosql;

import health.hbase.models.HBaseDataImport;
import health.input.jsonmodels.JsonDataPoints;
import health.input.jsonmodels.JsonDataValues;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import server.exception.ErrorCodeException;
import util.AllConstants;
import util.DateUtil;

import com.google.gson.Gson;

public class DataPointsSimulators {

	
	public HBaseDataImport exportHeartRateDatapoints(String streamID, Long start,
			Long end, String blockID, HashMap<String, String> dsUnitsList,
			SimpleDateFormat format, HashMap<String, Object> settings)
			throws ErrorCodeException {

		try {
			
			int max = 1000;
			if (settings != null
					&& settings
							.get(AllConstants.ProgramConts.exportSetting_MAX) != null) {
				max = (Integer) settings
						.get(AllConstants.ProgramConts.exportSetting_MAX);
			}

			List<JsonDataPoints> jsonDPList = new ArrayList<JsonDataPoints>();

			int counter = 0;
			boolean truncated = false;
			boolean finish=false;
			long time_steps=5*60*1000L;
			long timeCounter=start;
			int previous_hr=50;
			while (!finish) {
				counter++;

				// if (counter > max) {
				// continue;
				// }
				
				JsonDataPoints datapoint = new JsonDataPoints();
				List<JsonDataValues> hbaseDatavalueList = new ArrayList<JsonDataValues>();
				
				if (format == null) {
					// date.setTime(Long.parseLong(getTimefromRowKey(res)));
					datapoint.setAt(Long.toString(timeCounter));
				} else {
					// Date date=new Date();
					// date.setTime(Long.parseLong(getTimefromRowKey(res)));
					DateUtil dateUtil = new DateUtil();
					Date date = new Date();
					date.setTime(timeCounter);
					try {
						datapoint.setAt(dateUtil.format(date,
								dateUtil.millisecFormat));
					} catch (Exception ex) {
						throw new ErrorCodeException(
								AllConstants.ErrorDictionary.HBase_Internal_Error);
					}

				}
				Random rand = new Random();
				int min_hr=50, max_hr=140;
				
				int randomNum = rand.nextInt(max_hr - min_hr + 1) + min_hr;
				randomNum=(previous_hr+randomNum)/2;
				previous_hr=randomNum;
				JsonDataValues value=new JsonDataValues();
				value.setUnit_id(dsUnitsList.keySet().iterator().next());
				value.setVal(Integer.toString(randomNum));
				hbaseDatavalueList.add(value);
				datapoint.setValue_list(hbaseDatavalueList);
				
				if (counter == max) {
					jsonDPList.add(datapoint);
					continue;
				} else if (counter > max) {
					truncated = true;
					break;
				} else {
					jsonDPList.add(datapoint);
				}
				
				timeCounter=timeCounter+time_steps;
			}
			
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

			// HBaseConfig.putTable(table);
			return dataexport;
		} catch (NumberFormatException ex) {
			// HBaseConfig.putTable(table);
			ex.printStackTrace();
			throw new ErrorCodeException(
					AllConstants.ErrorDictionary.HBase_Internal_Error);
		}

	}
	
	
	/**
	 * @param args
	 * @throws ErrorCodeException 
	 */
	public static void main(String[] args) throws ErrorCodeException {
	DataPointsSimulators simulator=new DataPointsSimulators();
	Date now=new Date();
	HashMap<String, String> dsUnitsList=new HashMap<String, String>();
	dsUnitsList.put("idididid", "idididid");
	HBaseDataImport export=simulator.exportHeartRateDatapoints("sdf", now.getTime(), null, "", dsUnitsList, null, null);
	Gson gson=new Gson();
	System.out.println(gson.toJson(export));
	}

}
