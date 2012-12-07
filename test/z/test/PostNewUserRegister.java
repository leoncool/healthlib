package z.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import health.input.jsonmodels.JsonUser;

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
public class PostNewUserRegister {

    public static void main(String args[]) {
        try {
            URL url = new URL("http://146.169.35.28:55555/healthbook/v1/"+AllConstants.api_entryPoints.api_user
            		+"/"+AllConstants.api_entryPoints.api_register);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cache-Control", "no-cache");
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            Gson gson = new Gson();
            JsonUser juser=new JsonUser();
            juser.setLoginid("test5511112");
            juser.setEmail("test55111111@sdfd.com1");
            juser.setPassword("test55");
            juser.setGender("male");
            juser.setScreenname("test551");
            juser.setLanguage("en");
          // juser.setGender("male");
            juser.setBirthday("11/11/1987");
            System.out.println(gson.toJson(juser));
            out.write(gson.toJson(juser));
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
