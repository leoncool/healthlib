package health.database.DAO;

import health.database.models.SleepDataSummary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import server.exception.ErrorCodeException;
import util.AllConstants;
import util.HibernateUtil;

public class SleepDataDAO {
	public List<SleepDataSummary> getSleepDataSummaries(String StreamID,
			String unit_id, Date date) {
		List<SleepDataSummary> dsummaryList = new ArrayList<SleepDataSummary>();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(SleepDataSummary.class);
		criteria.add(Restrictions.eq("dstreamID", StreamID));
		criteria.addOrder(Order.asc("date"));
		// System.out.println("debug date:"+date);
		if (date != null) {
			criteria.add(Restrictions.eq("date", date));
		}
		if (unit_id != null) {
			criteria.add(Restrictions.eq("unit_id", unit_id));
		}
		dsummaryList = criteria.list();
		if (session.isOpen()) {
			session.close();
		}
		return dsummaryList;
	}

	public void create_SameDay_SleepSummaries(List<SleepDataSummary> dsList) {
		try {
			// SleepDataSummary DataSummaryToPut = null;
			Date tempDate = dsList.get(0).getDate();
			for (SleepDataSummary newSummary : dsList) {
				if (newSummary.getDate().getTime() != tempDate.getTime()) {
					throw new ErrorCodeException(
							AllConstants.ErrorDictionary.HBase_Internal_Error);
				}
			}
			List<SleepDataSummary> existSummryList = getSleepDataSummaries(
					dsList.get(0).getDstreamID(), null, dsList.get(0).getDate());
			Session session = HibernateUtil.beginTransaction();
			if (existSummryList.size() == 0) {
				// does not exist any record
				for (SleepDataSummary toSave : dsList) {
					session.save(toSave);
				}
			} else {
				HashMap<Long, SleepDataSummary> existSummaryMap = new HashMap<Long, SleepDataSummary>();

				for (SleepDataSummary existSummary : existSummryList) {
					existSummaryMap.put(existSummary.getStartTime().getTime(),
							existSummary);
				}
				for (SleepDataSummary newSummary : dsList) {
					if (!existSummaryMap.containsKey(newSummary.getStartTime()
							.getTime())) {
						session.save(newSummary);
					}
				}
			}
			HibernateUtil.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	public List<SleepDataSummary> getSleepDataSummariesByStartAndEndTime(String StreamID, String unit_id,
			Long start,Long end) {
		List<SleepDataSummary> dsummaryList = new ArrayList<SleepDataSummary>();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(SleepDataSummary.class);
		criteria.add(Restrictions.eq("dstreamID", StreamID));
		Date startDate=new Date();
		startDate.setTime(start);
		Date endDate=new Date();
		endDate.setTime(end);
		criteria.add(Restrictions.ge("date", startDate));
		criteria.add(Restrictions.le("date", endDate));
		criteria.addOrder(Order.asc("date"));
		if (unit_id != null) {
			criteria.add(Restrictions.eq("unit_id", unit_id));
		}
		dsummaryList = criteria.list();
		if (session.isOpen()) {
			session.close();
		}
		return dsummaryList;
	}
}
