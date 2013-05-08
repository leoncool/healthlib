/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.DAO;

import health.database.DAO.DatastreamDAO;
import health.database.DAO.nosql.HBaseDatapointDAO;
import health.database.models.Datastream;
import health.hbase.models.HBaseDataImport;
import health.input.jsonmodels.JsonDataPoints;
import health.input.util.DBtoJsonUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import server.exception.ErrorCodeException;
import util.DateUtil;

/**
 * 
 * @author leon
 */
public class exportFitbitToExcel {

	public static void main(String args[]) throws FileNotFoundException,
			IOException, ParseException, ErrorCodeException {

		HBaseDataImport hbaseexport = null;
		HBaseDatapointDAO diDao = new HBaseDatapointDAO();

		DateUtil dateUtil = new DateUtil();
		String yearMonthDateString = "2012-12-16";
		Date date = dateUtil.convert(yearMonthDateString,
				dateUtil.YearMonthDay_DateFormat);
		System.out.println("DateRequest:" + date);
		LocalDate localDate = LocalDate.fromDateFields(date);
		
		String liguoStringID="8005b15c-15af-49bb-9429-b2d19a436e21";
		String leoncoolStringID="aa30b3a2-78c1-49b9-98a9-93fa5c46bbd4";
		String dongdongStreamID = "1bfedead-2b5c-420f-b3a5-7a542be19292";
		DatastreamDAO dstreamDao = new DatastreamDAO();
		DBtoJsonUtil dbtoJUtil = new DBtoJsonUtil();
		Datastream datastream = null;
		try {
			datastream = dstreamDao.getHealthDatastreamByTitle(646,
					"steps", true, false);
		} catch (NonUniqueResultException ex) {
			ex.printStackTrace();
			return;
		}
		
		
		short cellCounter = 0;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();

		for (int k = 0; k < 1000; k++) {
			localDate = localDate.plusDays(1);
			Date now=new Date();
			LocalDate nowLocalDate=LocalDate.fromDateFields(now);
			if(localDate.isAfter(nowLocalDate))
			{
				continue;
			}
			System.out.println("localdate:"+localDate);
			date = localDate.toDate();
			long start = 0;
			long end = 0;
			Calendar calStart = Calendar.getInstance(DateUtil.UTC);
			Calendar calEnd = Calendar.getInstance(DateUtil.UTC);
			calStart.setTime(date);
			calEnd.setTime(date);
			calStart.set(Calendar.HOUR_OF_DAY, 0);
			calStart.set(Calendar.MINUTE, 0);
			start = calStart.getTimeInMillis();
			calEnd.set(Calendar.HOUR_OF_DAY, 23);
			calEnd.set(Calendar.MINUTE, 59);
			end = calEnd.getTimeInMillis();

			HashMap<String, String> mapUnits = new HashMap<String, String>();
			mapUnits.put(
					datastream.getDatastreamUnitsList().get(0).getUnitID(),
					datastream.getDatastreamUnitsList().get(0).getUnitID());

			hbaseexport = diDao.exportDatapoints(datastream.getStreamId(), start, end,
					null, mapUnits, null);

			
			if(hbaseexport==null||hbaseexport.getData_points()==null||hbaseexport.getData_points().size()<1)
			{
				continue;
			}
			System.out.println(hbaseexport.getData_points().size());
			List<JsonDataPoints> listDataPoints = hbaseexport.getData_points();
			HashMap<Integer, Double> mapMatrix = new HashMap<Integer, Double>();
			for (int i = 1; i < 25; i++) {
				mapMatrix.put(i, 0.0);
			}
			double sum=0;
			for (JsonDataPoints point : listDataPoints) {
				sum=sum+Double.parseDouble(point.getValue_list()
						.get(0).getVal());
			}
			if(sum<100)
			{
				System.out.println("sum<100");
				continue;
			}
			for (JsonDataPoints point : listDataPoints) {
				Date time = new Date(Long.parseLong(point.getAt()));
				LocalTime localTime = LocalTime.fromDateFields(time);
				// System.out.println(localTime.hourOfDay().get());
				int Hours = localTime.hourOfDay().get();
				if (Hours == 0) {
					Hours = 24;
				}
				mapMatrix.put(
						Hours,
						mapMatrix.get(Hours)
								+ Double.parseDouble(point.getValue_list()
										.get(0).getVal()));
			}

			for (int i = 1; i < 25; i++) {
				HSSFRow row=null;
				if(sheet.getRow(i-1)==null)
				{
					row= sheet.createRow(i - 1);
				}
				else{
					row=sheet.getRow(i-1);
				}
				row.createCell(cellCounter).setCellValue(mapMatrix.get(i));
//				System.out.println(i + "," + mapMatrix.get(i));
			}
			cellCounter++;
		}

		FileOutputStream fileOut = new FileOutputStream("F:/FitbitWorkbook_liguo.xls");
		wb.write(fileOut);
		fileOut.close();
	}
}
