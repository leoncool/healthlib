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
public class PostAccDataThroughHealth {
	public static void sendPostRequest(int MaxK)
	{
		try {

			URL url = new URL(
					"http://api2.wiki-health.org:55555/healthbook/v1/health/title/"+"accelerometer"+"/datapoints?accesstoken=d62fcd7b7bd2444fa5010b6fac363a98&api_key=special-key");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());

			
			  Scanner scannerCSV = new Scanner(new File("C:/Users/leonc_000/Desktop/test.csv"));
		         
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
		        	String dateString=strValues[0].substring(0,(strValues[0].length()-3));
		        	System.out.println(dateString);
		       
		        	Date rDate=sdf.parse(dateString);
		        	String x=strValues[1];
		        	String y =strValues[2];
		        	String z= strValues[3];
		        	
		        	List<JsonDataValues> datavaluesList=new ArrayList<JsonDataValues>();
		        	JsonDataValues valueX=new JsonDataValues();
		        	valueX.setUnit_id("U9tEZ");
		        	valueX.setVal(x);
		        	JsonDataValues valueY=new JsonDataValues();
		        	valueY.setUnit_id("iXrak");
		        	valueY.setVal(y);
		        	JsonDataValues valueZ=new JsonDataValues();
		        	valueZ.setUnit_id("dh6vT");
		        	valueZ.setVal(z);
		        	datavaluesList.add(valueX);
		        	datavaluesList.add(valueY);
		        	datavaluesList.add(valueZ);
		        	JsonDataPoints dataPoint=new JsonDataPoints();
		        	dataPoint.setAt(Long.toString(rDate.getTime()));
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
