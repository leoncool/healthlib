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
public class PostHeartRateDataThroughHealth {
	public static void sendPostRequest(int MaxK)
	{
		try {

			URL url = new URL(
					"http://api2.wiki-health.org:55555/healthbook/v1/health/title/"+"heart_rate"+"/datapoints?accesstoken=8fba9f939d7b4d03b2f3cb1b828b1173&api_key=special-key");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());

			
			  Scanner scannerCSV = new Scanner(new File("C:/workspace/healthlib/testData/heartrate_export.txt"));
		         
		
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
		        	String[] strValues=scannerCSV.nextLine().split("\\s+");
		       
		        	String at=strValues[1];
		        	String x=strValues[2];
//		        	String y =strValues[2];
//		        	String z= strValues[3];
		        	System.out.println(at+","+strValues[2]);
		        	List<JsonDataValues> datavaluesList=new ArrayList<JsonDataValues>();
		        	JsonDataValues valueX=new JsonDataValues();
		        	valueX.setUnit_id("DiJhd");
		        	valueX.setVal(x);
//		        	JsonDataValues valueY=new JsonDataValues();
//		        	valueY.setUnit_id("TcYPX");
//		        	valueY.setVal(y);
//		        	JsonDataValues valueZ=new JsonDataValues();
//		        	valueZ.setUnit_id("ouYMl");
//		        	valueZ.setVal(z);
		        	datavaluesList.add(valueX);
//		        	datavaluesList.add(valueY);
//		        	datavaluesList.add(valueZ);
		        	JsonDataPoints dataPoint=new JsonDataPoints();
		        	dataPoint.setAt(at);
		        	dataPoint.setValue_list(datavaluesList);
		        	value_list.add(dataPoint);
		        	counter++;
		        }
		         
		        //Do not forget to close the scanner 
		        scannerCSV.close();
//		        
//		    if(1==1)
//		    {
//		    	return;
//		    }
		
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
