package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import java.io.File;

public class ConfigManager {

	private final String fileCommon = "common.properties";
	private final String fileVersion = "version";
	private Properties prop = null;
	private static ConfigManager instance = new ConfigManager();

	public static ConfigManager getInstance() {
		return instance;
	}

	protected ConfigManager() {
		loadCommon();

		listAll();
	}

	private void loadCommon() {
		try {
			if (null == prop) {
				prop = new Properties();
			}
			File configFolder = new File(
					AllConstants.ServerConfigs.configsFolderPath);
			if (!configFolder.exists()) {
				// file = new
				// File("E:/IC_Dropbox/Dropbox/java/cacssLib/AppConfigs/" +
				// fileCommon);
				configFolder.mkdir();
			}
			InputStream is = new FileInputStream(configFolder + fileCommon);
			// InputStream is=new FileInputStream("StorageConf/"+fileCommon);
			// InputStream is = new
			// FileInputStream("C:/Program Files/Apache Software Foundation/Apache Tomcat 6.0.26/webapps/ROOT/WEB-INF/classes/com/zhumulangma/cloudstorage/server/conf/"
			// + fileCommon);
			prop.load(is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void listAll() {
		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println("\tkey=" + key + ", value="
					+ prop.getProperty(key));
		}
	}

	public String $NOSQL_DB() {
		return prop.getProperty("NODQL_DB");
	}

}
