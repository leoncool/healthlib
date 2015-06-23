package defaultsubject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.database.DAO.BaseDAO;
import health.input.jsonmodels.JsonDataImport;
import health.input.jsonmodels.JsonDataPoints;
import health.input.jsonmodels.JsonDataValues;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * 
 * @author Leon
 */
public class PostMeasuredTThroughHealth {
	public static void sendPostRequest(int MaxK)
	{
		try {

			URL url = new URL(
					"http://api2.wiki-health.org:55555/healthbook/v1/health/title/"+"environment"+"/datapoints?accesstoken=d62fcd7b7bd2444fa5010b6fac363a98&api_key=special-key");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());

			  Scanner scannerCSV = new Scanner(new File("C:/workspace/healthlib/testData/measuredT_export.txt"));
			         
		        //Set the delimiter used in file
		
		         
		        //Get all tokens and store them in some data structure
		        //I am just printing them
			  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//			  System.out.println(sdf.format(new Date()));
			 
		  
			
		
			Gson gson = new Gson();
			BaseDAO dao = new BaseDAO();
			ArrayList<JsonDataPoints> value_list=new ArrayList<JsonDataPoints> ();
			
			
			ArrayList<String> blockArray = new ArrayList<String>();
			int counter = 0;
			
		      while (scannerCSV.hasNextLine())
		        {
		    	  if(counter>10)
		    	  {
//		    		  break;
		    	  }
		        	String[] strValues=scannerCSV.nextLine().split(",");
		        	String at=strValues[0].trim();
		       
		        	String x=strValues[1];
		        	String y =strValues[2];
		        	String z= strValues[3];
		        	System.out.println(at+","+x+","+y+","+z);
		        	List<JsonDataValues> datavaluesList=new ArrayList<JsonDataValues>();
		        	JsonDataValues valueX=new JsonDataValues();
		        	valueX.setUnit_id("EGcIU");
		        	valueX.setVal(x);
		        	JsonDataValues valueY=new JsonDataValues();
		        	valueY.setUnit_id("isjAR");
		        	valueY.setVal(y);
		        	JsonDataValues valueZ=new JsonDataValues();
		        	valueZ.setUnit_id("QsWOo");
		        	valueZ.setVal(z);
		        	datavaluesList.add(valueX);
		        	datavaluesList.add(valueY);
		        	datavaluesList.add(valueZ);
		        	JsonDataPoints dataPoint=new JsonDataPoints();
		        	dataPoint.setAt(at);
		        	dataPoint.setValue_list(datavaluesList);
		        	value_list.add(dataPoint);
		        	counter++;
		        }
		         
		        //Do not forget to close the scanner 
		        scannerCSV.close();
		        
		    
		
			JsonDataImport importData = new JsonDataImport();
		importData.setData_points(value_list);
			String fileoutputString=gson.toJson(importData);
//			System.out.println(fileoutputString);
//			fileWriter.write(fileoutputString);
			out.write(fileoutputString);
			out.close();
			java.io.BufferedReader br = new java.io.BufferedReader(
					new java.io.InputStreamReader(connection.getInputStream()));
			java.lang.StringBuffer sb = new java.lang.StringBuffer();
			java.lang.String str = br.readLine();
			while (str != null) {
				sb.append(str);
				str = br.readLine();
			}
			br.close();
			java.lang.String responseString = sb.toString();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int code = httpConnection.getResponseCode();
			System.out.println("returncode:" + code);
			System.out.println(responseString);
			//
			// if(Bytes.toBytes(now.getTime())Bytes.toBytes(now2.getTime()))
			// {
			// System.out.println("smaller");
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String args[]) {
	
			sendPostRequest(1);
	
		
	}
}
