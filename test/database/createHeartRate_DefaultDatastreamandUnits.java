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

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.AllConstants;

public class createHeartRate_DefaultDatastreamandUnits {
	public static void main(String args[]) throws IOException {
		DatastreamDAO streamDao = new DatastreamDAO();
		Datastream stream = streamDao.getHealthDatastreamByTitle(664,
				AllConstants.ProgramConts.defaultDS_Name_heart_rate, true,
				false);
		if (stream == null) {
			stream = streamDao.basicDefaultDatastreamCreate("dongdong", 664);
			stream.setTitle(AllConstants.ProgramConts.defaultDS_Name_heart_rate);
			List<DatastreamUnits> unitList = new ArrayList<DatastreamUnits>();

			DatastreamUnits unit = new DatastreamUnits();
			UUID uuid = UUID.randomUUID();
			unit.setUnitID(uuid.toString());
			unit.setStreamID(stream);
			unit.setUnitLabel("times");
			unit.setCreatedTime(new Date());
			unit.setUpdatedTime(new Date());
			unit.setUnitType("times");
			unit.setValueType("double");
			unit.setShortUnitID(RandomStringUtils.randomAlphanumeric(5));
			unitList.add(unit);
			System.out.println(stream.getStreamId());
			stream.setDatastreamUnitsList(unitList);
			streamDao.createDatastream(stream);
		}

		// if(unitList.size()<3)
		// {
		// streamDao.DeleteDatastreamUnitList(stream);
		// }
	}

}
