package util;

import health.database.models.Config;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class ServerConfigUtil {
	public static String getConfigValue(String variable) {
		Session session = HibernateUtil.beginTransaction();
		Config config = (Config) session.get(Config.class, variable);
		   session.getTransaction().commit();
		if (config != null) {
			return config.getValue();
		} else {
			return null;
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
