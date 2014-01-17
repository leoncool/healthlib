package humanmodels;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.input.jsonmodels.JsonDatastream;
import health.input.jsonmodels.JsonDatastreamUnits;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import util.AllConstants.api_entryPoints;

import com.google.gson.Gson;
import com.physiology.calculator.HumanTemperature;
import com.physiology.input.jsonmodels.JsonTemperature;

/**
 * 
 * @author Leon
 */
public class GetTemperature {

	public static void main(String args[]) {
		try {
			String subjectID = "663";
			// String parameter =
			// AllConstants.api_entryPoints.request_devicetemplateID + "=" +
			// "d98eb956-7236-45ab-ac15-dec9c370bca7";
			String parameter = "";
			URL url = new URL("http://146.169.35.28:55555/healthbook/v1/hm");
			// + "?" + parameter);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			Gson gson = new Gson();
			double[] T = { 36.96, 35.07, 34.81, 34.58, 36.89, 36.28, 34.53,
					33.62, 35.53, 34.12, 33.59, 33.25, 35.41, 35.38, 35.30,
					35.22, 35.81, 35.30, 35.31, 34.10, 35.14, 35.03, 35.11,
					35.04, 36.71 };
			JsonTemperature jsonObject = new JsonTemperature();
			jsonObject
					.setT_list(HumanTemperature.convertDoubleArrayToString(T));
			jsonObject.setWeight(80);
			jsonObject.setRh(0.5);
			jsonObject.setTair(30);
			jsonObject.setVair(1);
			jsonObject.setWork(80);
			out.write(gson.toJson(jsonObject));
			System.out.println(gson.toJson(jsonObject));
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
}
