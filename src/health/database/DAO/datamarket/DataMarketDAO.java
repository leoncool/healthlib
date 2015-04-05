package health.database.DAO.datamarket;

import health.database.DAO.DatastreamDAO;
import health.database.datamarket.DataMarket;
import health.database.datamarket.DataSharing;
import health.database.models.Datastream;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class DataMarketDAO {
	public DataMarket getDataMarketByID(int id) {
		try {
			Session session = HibernateUtil.beginTransaction();
			DataMarket obj = (DataMarket) session.get(
					DataMarket.class, id);
			session.getTransaction().commit();
			if (obj != null) {
				return obj;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<DataMarket> getDataMarketListing(String streamTitle) {
		try {
			Session session = HibernateUtil.beginTransaction();
			Criteria criteria = session.createCriteria(DataMarket.class,"datamarket");
			if (streamTitle != null) {
				//join search test
				criteria.createAlias("datamarket.datastream", "stream");
				criteria.add(Restrictions.like("stream.title", "%" + streamTitle + "%"));
			}
			criteria.addOrder(Order.desc("createdTime"));
			List<DataMarket> entryList = criteria.list();
			session.getTransaction().commit();
			return entryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public DataMarket addToMarket(DataMarket object)
	{
		try {
			Session session = HibernateUtil.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			if (object != null) {
				return object;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<DataSharing> getDataSharingList(String loginID,String targetLoginID,String streamTitle) {
		try {
			Session session = HibernateUtil.beginTransaction();
			Criteria criteria = session.createCriteria(DataSharing.class,"datasharing");
			if (streamTitle != null) {
				//join search test
				criteria.createAlias("datasharing.datastream", "stream");
				criteria.add(Restrictions.like("stream.title", "%" + streamTitle + "%"));
			}
			if(loginID!=null)
			{
				criteria.add(Restrictions.eq("loginID", loginID));
			}
			if(targetLoginID!=null)
			{
				criteria.add(Restrictions.eq("targetLoginID", targetLoginID));
			}
			criteria.addOrder(Order.desc("createdTime"));
			List<DataSharing> entryList = criteria.list();
			session.getTransaction().commit();
			return entryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public DataSharing addDataSharing(DataSharing object)
	{
		try {
			Session session = HibernateUtil.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			if (object != null) {
				return object;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String args[])
	{
		DatastreamDAO dsDao=new DatastreamDAO();
		Datastream stream=dsDao.getHealthDatastreamByTitle("ecg", "testtest4", false, false);
//		DataMarket dm=new DataMarket();
//		dm.setLoginID("testtest");
//		dm.setStreamID(stream);
//		dm.setPrice(0);
//		dm.setCreatedTime(new Date());
		DataMarketDAO dmDao=new DataMarketDAO();
//		dmDao.addToMarket(dm);
//		DataSharing dsharing=new DataSharing();
//		dsharing.setLoginID("testtest");
//		dsharing.setTargetLoginID(stream.getOwner());
//		dsharing.setCreatedTime(new Date());
//		dsharing.setStreamID(stream);
//		dmDao.addDataSharing(dsharing);
//		System.out.println(dmDao.getDataSharingList(null,"testtest4","e").get(0).getLoginID());
//		System.out.println(dmDao.getDataMarketListing("e").get(0).getLoginID());
		
	}
}
