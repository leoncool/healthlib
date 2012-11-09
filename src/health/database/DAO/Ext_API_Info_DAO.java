package health.database.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import health.database.models.DataSummary;
import health.database.models.Datastream;
import health.database.models.ExternalApiInfo;
import health.database.models.Subject;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class Ext_API_Info_DAO extends BaseDAO {
	public List<ExternalApiInfo> getExt_API_INFO_List(String loginID, String device) {
		List<ExternalApiInfo> ext_API_List=new ArrayList<ExternalApiInfo>();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(ExternalApiInfo.class);
		criteria.add(Restrictions.eq("loginID", loginID));
		criteria.add(Restrictions.eq("device", device));
		ext_API_List=criteria.list();
		
		HibernateUtil.commitTransaction();
		if (session.isOpen()) {
			session.close();
		}
		return ext_API_List;
	}
	public ExternalApiInfo getExt_API_INFO(String loginID, String device,String ext_id) {
		ExternalApiInfo ext_API=new ExternalApiInfo();
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(ExternalApiInfo.class);
		criteria.add(Restrictions.eq("loginID", loginID));
		criteria.add(Restrictions.eq("device", device));
		criteria.add(Restrictions.eq("extId", ext_id));		
		ext_API=(ExternalApiInfo) criteria.uniqueResult();
		
		HibernateUtil.commitTransaction();
		if (session.isOpen()) {
			session.close();
		}
		return ext_API;
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
		Ext_API_Info_DAO dao=new Ext_API_Info_DAO();
		System.out.println(dao.getExt_API_INFO_List("leoncool", "fitbit").size());
	}
}
