package defaultsubject;

import health.database.DAO.BaseDAO;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class DeleteECGDataThread extends Thread{
	public int titleID;
	public DeleteECGDataThread(int title_ID)
	{
		this.titleID=title_ID;
	}
	 public void run() {
		 try {
//				String titleID="604";
			 System.out.println("deleteing:"+titleID);
				URL url = new URL(
						"http://api.wiki-health.org:55555/healthbook/v1/health/title/"+"ecg"+titleID+"?accesstoken=96b77929454040dfa8f751e90290b94e");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(false);
				connection.setRequestMethod("DELETE");
				connection.setDoInput(true);

				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("Cache-Control", "no-cache");
//				OutputStreamWriter out = new OutputStreamWriter(
//						connection.getOutputStream());
				Gson gson = new Gson();
				BaseDAO dao = new BaseDAO();
				
			
//				out.close();
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
	    	for(int i=500;i<70;i++){
	        new DeleteECGDataThread(i).start();
	        
	    	}
	    }
}
