package defaultsubject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.database.DAO.BaseDAO;
import health.database.datamarket.DataMarket;
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
public class PostAddDatastreamToMarketJSON {


	public static void main(String args[]) {
		try {
			URL url = new URL(
					"http://api2.wiki-health.org:55555/healthbook/AddDatastreamToMarketJson?accesstoken=bed28bbdabbd4a0992b3d9c15bdc263e");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());

		
			Gson gson = new Gson();
			DataMarket dm=new DataMarket();
			dm.setLoginID("testtest4");
			dm.setStreamid("6ba17172-6100-414a-af76-1c9a9f993de3");
			dm.setPrice(0);
			dm.setTerms("terms");
			

			out.write(gson.toJson(dm));
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
