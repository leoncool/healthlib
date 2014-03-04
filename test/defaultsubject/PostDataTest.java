package defaultsubject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.database.DAO.BaseDAO;
import health.input.jsonmodels.JsonDataImport;
import health.input.jsonmodels.singleunitstream.JsonSingleDataPoints;

import java.io.File;
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
public class PostDataTest {
	public static String arrayToDelimitedString(ArrayList<String> input) {
		String returnV = "";
		for (int i = 0; i < input.size(); i++) {
			if (i == (input.size() - 1)) {
				returnV = returnV + input.get(i);
			} else {
				returnV = returnV+input.get(i)+",";
			}

		}
		return returnV;
	}

	public static void main(String args[]) {
		try {
			URL url = new URL(
				"http://wikihealth.bigdatapro.org:55555/healthbook/v1/health/title/ecg/datapoints?accesstoken=ef9b6d64b1924d9cb490eddcd871858b&api_key=special-key");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			
			File fileInput=new File("F:/Dropbox/PhD/Wiki Health/Yang Li/ECG/ecg_data_101.out");
			
			Scanner scanner=new Scanner(fileInput);

			Gson gson = new Gson();
			BaseDAO dao = new BaseDAO();
			ArrayList<JsonSingleDataPoints> value_list = new ArrayList<JsonSingleDataPoints>();
			Date now=new Date();
			long nowLong=now.getTime();
			ArrayList<String> blockArray=new ArrayList<String>();
			while(scanner.hasNextLine())
			{	String nextLine=scanner.nextLine();
				if(blockArray.size()>=360)
				{
					JsonSingleDataPoints value = new JsonSingleDataPoints();
					value.setAt(Long.toString(nowLong));
//					System.out.println(arrayToDelimitedString(blockArray));
					value.setVal(arrayToDelimitedString(blockArray));
					value_list.add(value);
					nowLong=nowLong+1000L;	
					blockArray=new ArrayList<String>();
					blockArray.add(nextLine);
				}
				else{
					blockArray.add(nextLine);
				}
			}
		
//
//			value1.setVal("25");
//			value1.setAt(Long.toString(new Date().getTime()));
//			value_list.add(value1);
			JsonDataImport importData = new JsonDataImport();
			importData.setData_points_single_list(value_list);
			// importData.setBlock_id("8ad4c077-d30d-4a14-b882-0cbfcfb3e4ea");
//			System.out.println(gson.toJson(importData));
			out.write(gson.toJson(importData));
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
}
