package health.database.DAO;

import health.database.models.Datastream;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.AllConstants;
import util.HibernateUtil;

public class HealthDataStreamDAO extends DatastreamDAO {
	public Datastream getDefaultDatastreamOfType(String loginID,String default_stream_type)
	{
		Datastream ds=null;
		Session session = HibernateUtil.beginTransaction();
		try{
	
		Criteria criteria = session.createCriteria(Datastream.class);
		criteria.add(Restrictions.eq("owner", loginID));
		criteria.add(Restrictions.eq("purpose", AllConstants.HealthConts.default_health_subject_purpose));
		criteria.add(Restrictions.eq("title", default_stream_type));
		ds=(Datastream) criteria.uniqueResult();		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if(session.isOpen())
			{
				session.close();				
			}
		}
	
		return ds;
	}
	
}
