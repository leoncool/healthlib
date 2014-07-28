package defaultsubject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.database.DAO.BaseDAO;
import health.input.jsonmodels.JsonDataImport;
import health.input.jsonmodels.singleunitstream.JsonSingleDataPoints;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * 
 * @author Leon
 */
public class PostECGDataThroughHealth {
	public static void sendPostRequest(int MaxK)
	{
		try {

			URL url = new URL(
					"http://api.wiki-health.org:55555/healthbook/v1/health/title/"+"ecg1"+"/datapoints-benchmarks?accesstoken=5b08af23d9f2490cb253fa5d221aa74e&api_key=special-key");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());

			File fileInput = new File(
					"F:/Dropbox/PhD/Wiki Health/Yang Li/ECG/ecg_data_101.out");
			String fileOutputFolder = "F:/Dropbox/PhD/Wiki Health/Yang Li/ECG/For Benchmarks/";
			Scanner scanner = new Scanner(fileInput);
			File fileOutput=new File(fileOutputFolder+MaxK+".json");
//			FileWriter fileWriter=new FileWriter(fileOutput);
			BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(fileOutput), "UTF-8"
				));
			Gson gson = new Gson();
			BaseDAO dao = new BaseDAO();
			ArrayList<JsonSingleDataPoints> value_list = new ArrayList<JsonSingleDataPoints>();
			Date now = new Date(1406224391);
			long nowLong = now.getTime();
			double nowLongDouble = nowLong;
			ArrayList<String> blockArray = new ArrayList<String>();
			int counter = 0;
			while (counter<MaxK)
			{
				if(!scanner.hasNextLine()){
					scanner = new Scanner(fileInput);	
				}
				String nextLine = scanner.nextLine();
				JsonSingleDataPoints value = new JsonSingleDataPoints();
//				 System.out.println(nextLine);
				value.setVal(nextLine);
				value.setAt(Long.toString((long) nowLongDouble));
				value_list.add(value);
				nowLongDouble = nowLongDouble + 1000.00 / 360;
				counter = counter + 1;
			}
			scanner.close();
			JsonDataImport importData = new JsonDataImport();
			importData.setData_points_single_list(value_list);
			String fileoutputString=gson.toJson(importData);
//			System.out.println(fileoutputString);
//			fileWriter.write(fileoutputString);
			out.write(fileoutputString);
			out.close();
			fileWriter.close();
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
		for(int p=10;p<=10;p=p*10)
		{
			sendPostRequest(p);
		}
		
	}
}
