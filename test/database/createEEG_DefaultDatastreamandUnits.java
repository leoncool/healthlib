package database;

import health.database.DAO.DatastreamDAO;
import health.database.models.Datastream;
import health.database.models.DatastreamUnits;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class createEEG_DefaultDatastreamandUnits {
	public static void main(String args[]) throws IOException
	{
	DatastreamDAO streamDao=new DatastreamDAO();
	Datastream stream=streamDao.getHealthDatastreamByTitle(664, "eeg", true, false);
	
	  
    FileInputStream signal_label_file = new FileInputStream(new File("E:/IC_Dropbox/Dropbox/PhD/Data Sets/EEG/labels.xlsx"));
     
    //Get the workbook instance for XLS file 
    
    XSSFWorkbook signal_label_workbook = new XSSFWorkbook(signal_label_file);
 
    //Get first sheet from the workbook
    XSSFSheet signal_label_sheet = signal_label_workbook.getSheetAt(0);
   System.out.println(signal_label_sheet.getPhysicalNumberOfRows());
    //Iterate through each rows from first sheet
//    Iterator<Row> signal_rowIterator = signal_label_workbook.get;
    XSSFRow row=signal_label_sheet.getRow(0);

   
    signal_label_file.close();
	
	if(stream==null)
	{
		stream=streamDao.basicDefaultDatastreamCreate("dongdong", 664);
		stream.setTitle("eeg");
		List<DatastreamUnits> unitList=new ArrayList<DatastreamUnits>();
		
		
	    for(int i=0;i<64;i++)
	    {
	    	DatastreamUnits unit=new DatastreamUnits();
	    	UUID uuid=UUID.randomUUID();
	    	unit.setUnitID(uuid.toString());
	    	unit.setStreamID(stream);
	    	unit.setUnitLabel(row.getCell(i).getStringCellValue());
	    	unit.setCreatedTime(new Date());
	    	unit.setUpdatedTime(new Date());
	    	unit.setUnitType("uV");
	    	unit.setValueType("double");
	    	System.out.println(row.getCell(i).getStringCellValue());
	    	unitList.add(unit);
	    }
	    System.out.println(stream.getStreamId());
	    stream.setDatastreamUnitsList(unitList);
	    streamDao.createDatastream(stream);
	}

//	if(unitList.size()<3)
//	{
//		streamDao.DeleteDatastreamUnitList(stream);
//	}
	}
	
	
}
