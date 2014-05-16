package health.database.DAO;

import health.database.models.DataSummary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class DataSummaryDAO extends BaseDAO {
	public List<DataSummary> getDataSummaries(String StreamID, String unit_id,
			Date date) {
		List<DataSummary> dsummaryList = new ArrayList<DataSummary>();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(DataSummary.class);
		criteria.add(Restrictions.eq("dstreamID", StreamID));
		criteria.addOrder(Order.asc("date"));
//		System.out.println("debug date:"+date);
		if(date!=null){
		criteria.add(Restrictions.eq("date", date));
		}
		if (unit_id != null) {
			criteria.add(Restrictions.eq("unit_id", unit_id));
		}
		dsummaryList = criteria.list();
		   session.getTransaction().commit();
		return dsummaryList;
	}
	public List<DataSummary> getDataSummariesByStartAndEndTime(String StreamID, String unit_id,
			Long start,Long end) {
		List<DataSummary> dsummaryList = new ArrayList<DataSummary>();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(DataSummary.class);
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
		   session.getTransaction().commit();
		return dsummaryList;
	}
	public DataSummary create_A_DataSummary(DataSummary ds) {
		try {
			DataSummary DataSummaryToPut = null;
			List<DataSummary> existSummryList = getDataSummaries(
					ds.getDstreamID(), ds.getUnit_id(), ds.getDate());
			if (existSummryList.size() == 1) {
				DataSummaryToPut = existSummryList.get(0);
				DataSummaryToPut.setValue(ds.getValue());
				DataSummaryToPut.setUpdated(new Date());
				DataSummaryToPut.setGoal(ds.getGoal());
				DataSummaryToPut.setTitle(ds.getTitle());
			} else if (existSummryList.size() > 1) {
				return null;
			} else {
				DataSummaryToPut = ds;
			}
			if (DataSummaryToPut.getUpdated() == null) {
				DataSummaryToPut.setUpdated(new Date());
			}
			Session session = HibernateUtil.beginTransaction();
			if (existSummryList.size() == 1) {
				session.update(DataSummaryToPut);
			} else {
				session.save(DataSummaryToPut);
			}
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

	public static void main(String args[]) {
		DataSummaryDAO dao = new DataSummaryDAO();
		// dao.getA_DataSummary(StreamID, date)
	}
}
