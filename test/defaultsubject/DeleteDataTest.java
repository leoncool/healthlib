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
public class DeleteDataTest {

	public static void main(String args[]) {
		try {
			String subjectID = "663";
			String dataStreamID = "feb6f678-a518-4d57-b0c3-f8ae34e22354";
			URL url = new URL(
					"http://146.169.35.28:55555/healthbook/v1/subjects/"+subjectID+"/datastreams/"
							+ dataStreamID + "/datapoints?start=1357833333560");
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
