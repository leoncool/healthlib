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
				returnV = returnV + input.get(i) + ",";
			}

		}
		return returnV;
	}

	public static void main(String args[]) {
		try {
			URL url = new URL(
					"http://wikihealth.bigdatapro.org:55555/healthbook/v1/health/title/ecg/datapoints?accesstoken=8ede656b5a834d0ebcf5233f4e9ff263&api_key=special-key");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());

			File fileInput = new File(
					"F:/Dropbox/PhD/Wiki Health/Yang Li/ECG/ecg_data_101.out");

			Scanner scanner = new Scanner(fileInput);

			Gson gson = new Gson();
			BaseDAO dao = new BaseDAO();
			ArrayList<JsonSingleDataPoints> value_list = new ArrayList<JsonSingleDataPoints>();
			Date now = new Date();
			long nowLong = now.getTime();
			double nowLongDouble = nowLong;
			ArrayList<String> blockArray = new ArrayList<String>();
			int counter = 0;
			while (scanner.hasNextLine()&&counter<70000) {
				String nextLine = scanner.nextLine();
				JsonSingleDataPoints value = new JsonSingleDataPoints();
//				 System.out.println(nextLine);
				value.setVal(nextLine);
				value.setAt(Long.toString((long) nowLongDouble));
				value_list.add(value);
				nowLongDouble = nowLongDouble + 1000.00 / 360;
				counter = counter + 1;
			}

			//
			// value1.setVal("25");
			// value1.setAt(Long.toString(new Date().getTime()));
			// value_list.add(value1);
			JsonDataImport importData = new JsonDataImport();
			importData.setData_points_single_list(value_list);
			// importData.setBlock_id("8ad4c077-d30d-4a14-b882-0cbfcfb3e4ea");
			// System.out.println(gson.toJson(importData));

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
