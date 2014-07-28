package defaultsubject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.database.DAO.BaseDAO;
import health.input.jsonmodels.JsonDataImport;
import health.input.jsonmodels.JsonDatastream;
import health.input.jsonmodels.JsonDatastreamUnits;
import health.input.jsonmodels.singleunitstream.JsonSingleDataPoints;

import java.io.File;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import util.AllConstants.api_entryPoints;

import com.google.gson.Gson;

/**
 * 
 * @author Leon
 */
public class PostManyECGDatastreamsTest {
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

	public static void createECG_DataStreamTest(String title) {
		try {

			URL url = new URL("http://api.wiki-health.org:55555/healthbook/v1/"
					+ api_entryPoints.api_health
					+ "/title?accesstoken=5b08af23d9f2490cb253fa5d221aa74e");
			// + "?" + parameter);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			Gson gson = new Gson();
			JsonDatastreamUnits unit1 = new JsonDatastreamUnits("physical",
					"mv", "mv", "float");

			ArrayList<JsonDatastreamUnits> unitList = new ArrayList<JsonDatastreamUnits>();
			unitList.add(unit1);

			JsonDatastream jdStream = new JsonDatastream();
			jdStream.setUnits_list(unitList);
			jdStream.setTitle(title);
			System.out.println(gson.toJson(jdStream));
			out.write(gson.toJson(jdStream));
			// out.write("{\"title\":\"nte123111\"}");
			out.close();
			// System.out.println("{\"title\":\"nte123111\"}");

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

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		for (int i = 3; i <= 1000; i++) {
			createECG_DataStreamTest("ecg" + i);
		}
	}
}
