package health.database.DAO;

import java.util.Date;

import health.database.models.DataSummary;
import health.database.models.Datastream;
import health.database.models.Subject;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class DataSummaryDAO extends BaseDAO {
	public DataSummary getA_DataSummary(String StreamID, Date date) {
		DataSummary dsummary = null;
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(DataSummary.class);
		criteria.add(Restrictions.eq("dstreamID", StreamID));
		criteria.add(Restrictions.eq("date", date));
		dsummary=(DataSummary) criteria.uniqueResult();
		
		HibernateUtil.commitTransaction();
		if (session.isOpen()) {
			session.close();
		}
		return dsummary;
	}
	public DataSummary create_A_DataSummary(DataSummary ds) {
        try {
            Session session = HibernateUtil.beginTransaction();
            session.save(ds);
            HibernateUtil.commitTransaction();
            if(ds.getId()==0)
            {
            	return null;
            }
            else{
            	return ds;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
    }	
	public static void main(String args[])
	{
		DataSummaryDAO dao=new DataSummaryDAO();
	//	dao.getA_DataSummary(StreamID, date)
	}
}
