package database;

import health.database.DAO.DatastreamDAO;
import health.database.DAO.nosql.HBaseDatapointDAO;
import health.database.models.Datastream;
import health.database.models.DatastreamUnits;
import health.hbase.models.HBaseDataImport;
import health.input.jsonmodels.JsonDataPoints;
import health.input.jsonmodels.JsonDataValues;
import health.input.util.DBtoJsonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import server.exception.ErrorCodeException;

public class importEEG_64channels {
	public static void main(String args[]) throws IOException,
			ErrorCodeException, ParseException {
		DatastreamDAO streamDao = new DatastreamDAO();
		Datastream stream = streamDao.getHealthDatastreamByTitle(664, "eeg",
				true, false);
		if (stream == null) {
			System.out.println("stream not found!");
			return;
		}
		String folder="F:/Dropbox/Dropbox/PhD/Data Sets/EEG/";
		FileInputStream signal_label_file = new FileInputStream(new File(
				folder+"labels.xlsx"));
		FileInputStream signal_file = new FileInputStream(new File(
				folder+"/eeg01.xlsx"));

		// Get the workbook instance for XLS file

		XSSFWorkbook signal_label_workbook = new XSSFWorkbook(signal_label_file);
		XSSFWorkbook signal_workbook = new XSSFWorkbook(signal_file);
		// Get first sheet from the workbook
		XSSFSheet signal_label_sheet = signal_label_workbook.getSheetAt(0);
		XSSFSheet signal_sheet = signal_workbook.getSheetAt(0);
		XSSFRow row_label = signal_label_sheet.getRow(0);
		XSSFRow row_signal=signal_sheet.getRow(0);
		List<DatastreamUnits> unitList = stream.getDatastreamUnitsList();
		System.out.println("unitList:" + unitList.size());
		HBaseDatapointDAO datapointDao = new HBaseDatapointDAO();
		HBaseDataImport importdata = new HBaseDataImport();
//		DatastreamBlocks block = streamDao.CreateDatastreamBlock(stream,
//				"eeg1", "eeg1");
		importdata.setBlock_id("2870e4e7-dcd3-4e89-9466-15345af6c95d");
		List<JsonDataPoints> data_pointsList = new ArrayList<JsonDataPoints>();
		Date now = new Date();
		List<String> labels = new ArrayList<String>();
		for (int i = 0; i < 64; i++) {
			labels.add(row_label.getCell(i).getStringCellValue());
		}
		long now_long = now.getTime();
		if (stream == null) {
			System.out.println("stream not found!");
			return;
		} else {
			// row_signal.getPhysicalNumberOfCells()
			for (int i = 0; i < row_signal.getPhysicalNumberOfCells(); i++) {
				JsonDataPoints point = new JsonDataPoints();
				List<JsonDataValues> listValues = new ArrayList<JsonDataValues>();
				for (int j = 0; j < 64; j++) {
					String signal_label_str = labels.get(j);
					now_long = (long) (now_long + 6.25);
					point.setAt(Long.toString(now_long));
					for (DatastreamUnits unit : unitList) {
						if (unit.getUnitLabel().equalsIgnoreCase(
								signal_label_str)) {
							JsonDataValues value = new JsonDataValues();
							value.setUnit_id(unit.getShortUnitID());
							value.setVal(Double.toString(signal_sheet.getRow(j)
									.getCell(i).getNumericCellValue()));
							System.out.println(signal_label_str
									+ " :"
									+ signal_sheet.getRow(j).getCell(i)
											.getNumericCellValue());
							listValues.add(value);
						}
					}
				}
				point.setValue_list(listValues);
				data_pointsList.add(point);
			}

		}
		importdata.setData_points(data_pointsList);
		DBtoJsonUtil dbtoJsonUtil=new DBtoJsonUtil();
		
		importdata.setDatastream(dbtoJsonUtil.convertDatastream(stream, null));
		importdata.setDatastream_id(stream.getStreamId());
		System.out.println(stream.getStreamId());
		System.out.println(datapointDao.importDatapointsDatapoints(importdata));

		signal_label_file.close();
	}
}
