package z.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import health.database.DAO.BaseDAO;
import health.input.jsonmodels.JsonDataImport;
import health.input.jsonmodels.JsonDataPoints;
import health.input.jsonmodels.JsonDataValues;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

/**
 *
 * @author Leon
 */
public class PostWeightData {

    public static void main(String args[]) {
        try {
            String subjectID = "627";
            URL url = new URL("http://localhost:8080/healthbook/v1/subjects/" + subjectID + "/datastreams/45c53baf-dafc-43cd-a634-3f0d8e8b0e75/datapoints");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cache-Control", "no-cache");
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            Gson gson = new Gson();
            BaseDAO dao = new BaseDAO();
            ArrayList<JsonDataPoints> datapoint_List = new ArrayList<JsonDataPoints>();
                JsonDataPoints point = new JsonDataPoints();
                Calendar cal=Calendar.getInstance();
                cal.setTime(new Date());
                cal.set(2012, 9, 25);
                point.setAt(Long.toString(cal.getTime().getTime()));
                ArrayList<JsonDataValues> value_list = new ArrayList<JsonDataValues>();
                JsonDataValues value1 = new JsonDataValues();
                value1.setUnit_id("1820a975-5f39-442e-9bbd-57e181179535");
                value1.setVal("180.2");
                value_list.add(value1);
//                value_list.add(value3);
//                value_list.add(value4);
//                value_list.add(value5);
                point.setValue_list(value_list);
                datapoint_List.add(point);
            JsonDataImport importData = new JsonDataImport();
          //  importData.setBlock_id("9ecbc259-3918-45c7-aeb5-c6850d601e0c");
            importData.setData_points(datapoint_List);

            System.out.println(gson.toJson(importData));
            out.write(gson.toJson(importData));
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
//          
//            if(Bytes.toBytes(now.getTime())Bytes.toBytes(now2.getTime()))
//            {
//                System.out.println("smaller");
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
