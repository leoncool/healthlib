/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.DAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import util.DateUtil;

import com.lifestyle.models.Locationlogs;

/**
 *
 * @author leon
 */
public class exportToExcel1 {

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
        DateUtil dateUtil = new DateUtil();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow firstRow = sheet.createRow((short) 0);
        firstRow.createCell(0).setCellValue("time");
        firstRow.createCell(1).setCellValue("lat");
        firstRow.createCell(2).setCellValue("lon");
        firstRow.createCell(3).setCellValue("accuracy");
        LocationLogDAO locDao = new LocationLogDAO();
        List<Locationlogs> loclogList = locDao.getLocatioonLogs("leoncool", null, null);
        int rowCounter = 1;
        for (Locationlogs log : loclogList) {
            HSSFRow row = sheet.createRow(rowCounter);
            rowCounter++;
            row.createCell(0).setCellValue(dateUtil.format(log.getDatetime(), dateUtil.utcFormat));
            row.createCell(1).setCellValue(log.getLat());
            row.createCell(2).setCellValue(log.getLon());
            row.createCell(3).setCellValue(log.getAccuracy());
        }
        FileOutputStream fileOut = new FileOutputStream("E:/workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }
}
