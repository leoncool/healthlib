package z.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import health.input.jsonmodels.JsonFollower;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import util.AllConstants;

import com.google.gson.Gson;

/**
 *
 * @author Leon
 */
public class PostFollower {

    public static void main(String args[]) {
        try {
            URL url = new URL("http://localhost:8080/healthbook/v1/"+AllConstants.api_entryPoints.api_follower);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cache-Control", "no-cache");
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            Gson gson = new Gson();
            JsonFollower jfollower=new JsonFollower();
            jfollower.setLoginid("leoncool");
            jfollower.setFollower_id("test2");
            System.out.println(gson.toJson(jfollower));
            out.write(gson.toJson(jfollower));
            out.close();
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
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
