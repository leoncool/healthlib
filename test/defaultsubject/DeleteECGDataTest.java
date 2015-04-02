package defaultsubject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.database.DAO.BaseDAO;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

/**
 * 
 * @author Leon
 */
public class DeleteECGDataTest {

	public static void main(String args[]) {
		try {
			String titleID="604";
			URL url = new URL(
					"http://api2.wiki-health.org:55555/healthbook/v1/health/title/"+"ecg"+titleID+"?accesstoken=26e370d5a6df4917840a91458f4fce72");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(false);
			connection.setRequestMethod("DELETE");
			connection.setDoInput(true);

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cache-Control", "no-cache");
//			OutputStreamWriter out = new OutputStreamWriter(
//					connection.getOutputStream());
			Gson gson = new Gson();
			BaseDAO dao = new BaseDAO();
			
		
//			out.close();
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
