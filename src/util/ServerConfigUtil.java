package util;

import health.database.models.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

public class ServerConfigUtil {
	public static HashMap<String, String> configsCache=new HashMap<>();
	public static String getConfigValue(String variable) {
		if(configsCache.get(variable)!=null&&configsCache.get(variable).length()>0)
		{
			return configsCache.get(variable);
		}else{
		Session session = HibernateUtil.beginTransaction();
		Config config = (Config) session.get(Config.class, variable);
		   session.getTransaction().commit();
		if (config != null) {
			configsCache.put(variable, config.getValue());
			return config.getValue();
		} else {
			return null;
		}
		}
	}

	public void installation() {
		List<Config> configList = new ArrayList<Config>();
		Config avatarLocation = new Config(
				AllConstants.ServerConfigs.UserAvatarLocation, "");
		configList.add(avatarLocation);
		Config avatarAccessPoint = new Config(
				AllConstants.ServerConfigs.AvatarAccessPoint, "");
		configList.add(avatarAccessPoint);
		Session session = HibernateUtil.beginTransaction();
		for (Config config : configList) {
			session.saveOrUpdate(config);
		}
		session.getTransaction().commit();
	}

	public static void main(String args[]) {
		ServerConfigUtil config = new ServerConfigUtil();
		config.installation();
	}
}
