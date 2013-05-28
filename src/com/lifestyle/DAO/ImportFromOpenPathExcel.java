/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lifestyle.models.Locationlogs;

/**
 * 
 * @author Leon
 */
public class ImportFromOpenPathExcel {

	public void importfromOpenPathExcel() throws FileNotFoundException,
			IOException {
		String folderName = "F:/Dropbox/Dropbox/PhD/Data Sets/openpath/";
		String filename = "openpaths_project_GEYDAMBQGI2Q.xlsx";

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(folderName + filename);

			List<Locationlogs> logList = new ArrayList<Locationlogs>();

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int i = 1;
			while (sheet.getRow(i) != null) {
				String userID = sheet.getRow(i).getCell(0).getStringCellValue();
				double lat=sheet.getRow(i).getCell(1)
						.getNumericCellValue();
				double lon=sheet.getRow(i).getCell(2)
						.getNumericCellValue();
				double alt=sheet.getRow(i).getCell(3)
						.getNumericCellValue();
				 Locationlogs log = new Locationlogs();
                 log.setUserID(userID);
                 log.setId(sheet.getRow(i).getCell(4).getDateCellValue().getTime() + "-" + userID);
                 log.setDatetime(sheet.getRow(i).getCell(4).getDateCellValue());
          
                 log.setLat(lat);
                 log.setLon(lon);
                 log.setAlt(alt);
                 logList.add(log);
				System.out.println(userID+","+sheet.getRow(i).getCell(1)
						.getNumericCellValue()+","+sheet.getRow(i).getCell(4).getDateCellValue().getTime());
				i++;
			}
			LocationLogDAO locationDao=new LocationLogDAO();
			locationDao.putListLog(logList);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

	}

	public static void main(String args[]) throws FileNotFoundException,
			IOException {

		ImportFromOpenPathExcel importOpenPath = new ImportFromOpenPathExcel();
		importOpenPath.importfromOpenPathExcel();

	}
}
