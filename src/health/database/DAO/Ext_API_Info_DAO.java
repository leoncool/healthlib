package health.database.DAO;

import health.database.models.DataSummary;
import health.database.models.ExternalApiInfo;
import health.database.models.FitbitLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class Ext_API_Info_DAO extends BaseDAO {
	public List<ExternalApiInfo> getExt_API_INFO_List(String loginID,
			String device) {
		List<ExternalApiInfo> ext_API_List = new ArrayList<ExternalApiInfo>();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(ExternalApiInfo.class);
		criteria.add(Restrictions.eq("loginID", loginID));
		criteria.add(Restrictions.eq("device", device));
		ext_API_List = criteria.list();

		session.getTransaction().commit();

		return ext_API_List;
	}

	public List<ExternalApiInfo> getExt_API_INFO_List(String device) {
		List<ExternalApiInfo> ext_API_List = new ArrayList<ExternalApiInfo>();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(ExternalApiInfo.class);
		criteria.add(Restrictions.eq("device", device));
		ext_API_List = criteria.list();
		session.getTransaction().commit();
		return ext_API_List;
	}

	public ExternalApiInfo getExt_API_INFO(String loginID, String device,
			String ext_id) {
		ExternalApiInfo ext_API = new ExternalApiInfo();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(ExternalApiInfo.class);
		criteria.add(Restrictions.eq("loginID", loginID));
		criteria.add(Restrictions.eq("device", device));
		// criteria.add(Restrictions.eq("extId", ext_id));
		ext_API = (ExternalApiInfo) criteria.uniqueResult();

		session.getTransaction().commit();
		return ext_API;
	}

	public void Update_A_ExtAPI(ExternalApiInfo apiinfo) {
		try {
			Session session = HibernateUtil.beginTransaction();
			session.update(apiinfo);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}

	public DataSummary create_A_DataSummary(DataSummary ds) {
		try {
			Session session = HibernateUtil.beginTransaction();
			session.save(ds);
			session.getTransaction().commit();
			if (ds.getId() == 0) {
				return null;
			} else {
				return ds;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
		}
	}

	public ExternalApiInfo create_A_ExternalAPIInfo(ExternalApiInfo apiinfo) {
		try {
			apiinfo.setApiCounter(0);
			Session session = HibernateUtil.beginTransaction();
			session.save(apiinfo);
			session.getTransaction().commit();
			if (apiinfo.getId() == 0) {
				return null;
			} else {
				return apiinfo;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
		}
	}

	public FitbitLog create_FitbitLog(FitbitLog log) {
		Session session = HibernateUtil.beginTransaction();
		session.save(log);
		session.getTransaction().commit();
		if (log.getId() == 0) {
			return null;
		} else {
			return log;
		}
	}

	public FitbitLog update_FitbitLog(FitbitLog log) {
		Session session = HibernateUtil.beginTransaction();
		session.update(log);
		session.getTransaction().commit();
		if (log.getId() == 0) {
			return null;
		} else {
			return log;
		}
	}

	public FitbitLog getFitbitFetch(String loginID, Date date) {
		FitbitLog log = null;
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(FitbitLog.class);
		criteria.add(Restrictions.eq("loginID", loginID));
		criteria.add(Restrictions.eq("date", date));
		// criteria.add(Restrictions.eq("extId", ext_id));
		log = (FitbitLog) criteria.uniqueResult();
		session.getTransaction().commit();
		return log;
	}

	public FitbitLog getLastFetchFitbitLog(String loginID) {
		FitbitLog log = null;
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(FitbitLog.class);
		criteria.add(Restrictions.eq("loginID", loginID));
		criteria.addOrder(Order.desc("date"));
		criteria.setMaxResults(1);
		// criteria.add(Restrictions.eq("date", date));
		// criteria.add(Restrictions.eq("extId", ext_id));
		log = (FitbitLog) criteria.uniqueResult();
		session.getTransaction().commit();
		return log;
	}

	public static void main(String args[]) {
		Ext_API_Info_DAO dao = new Ext_API_Info_DAO();
		// System.out.println(dao.getExt_API_INFO_List("leoncool",
		// "fitbit").size());
		FitbitLog log = dao.getLastFetchFitbitLog("leoncool");
		if (log == null) {
			System.out.println("null");
		} else {
			System.out.println(log.getId());
			if (log.isFinished) {
				System.out.println("finished");
			} else {
				System.out.println("not finished");
			}
		}
	}
}
