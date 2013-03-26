/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.DAO;

import com.lifestyle.models.Locationlogs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import util.DateUtil;

/**
 *
 * @author Leon
 */
public class importFromTxt {

    LocationsDAO locationDao = new LocationsDAO();

    public void importfromFile(File file) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String currentRecord;
        ArrayList rows = new ArrayList();
        while ((currentRecord = br.readLine()) != null) {
            rows.add(currentRecord.split(","));
            if (currentRecord.startsWith("time,lat,lon")) {
            } else {
                String[] cells =
                        currentRecord.split(",");
                if (cells.length > 6) {
                    try {
                        Locationlogs log = new Locationlogs();
                        log.setUserID("leoncool");
                        DateUtil dateUtil = new DateUtil();
                        log.setDatetime(dateUtil.convert(cells[0], dateUtil.utcFormat));
                        log.setAccuracy(Double.parseDouble(cells[4]));
                        log.setBearing(Double.parseDouble(cells[5]));
                        log.setElevation(Double.parseDouble(cells[3]));
                        log.setLat(Double.parseDouble(cells[1]));
                        log.setLon(Double.parseDouble(cells[2]));
                        log.setSpeed(Double.parseDouble(cells[6]));
                        locationDao.putLog(log);
                        System.out.println(currentRecord + "?");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("-------------FileName:----" + file.getName());
                    }
                }
            }
        }
        br.close();
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {

        File folder = new File("F:/Dropbox/Dropbox/Apps/GPSLogger for Android");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                importFromTxt importfromTxt = new importFromTxt();
                importfromTxt.importfromFile(file);
            }
        }

    }
}
