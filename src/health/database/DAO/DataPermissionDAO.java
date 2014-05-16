package health.database.DAO;

import java.util.List;

import health.database.models.DataPermission;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class DataPermissionDAO extends BaseDAO {

	/**
	 * @param args
	 */
	public DataPermission createDataPermission(DataPermission permission) {
		try {
			Session session = HibernateUtil.beginTransaction();
			session.save(permission);
			session.getTransaction().commit();

			if (permission.getRecordId() > 0) {
				return permission;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
		}
	}

	public List<DataPermission> getDataPermission(String ownerLogin,
			String accessorLogin, String targetDataType, String targetDataId,
			String status) {
		try {
			Session session = HibernateUtil.beginTransaction();
			Criteria criteria = session.createCriteria(DataPermission.class);
			if (ownerLogin != null) {
				criteria.add(Restrictions.eq("owner", ownerLogin));
			}
			if (accessorLogin != null) {
				criteria.add(Restrictions.eq("givenLoginid", accessorLogin));
			}
			if (targetDataType != null) {
				criteria.add(Restrictions.eq("targetDataType", targetDataType));
			}
			if (targetDataId != null) {
				criteria.add(Restrictions.eq("targetDataId", targetDataId));
			}
			if (status != null) {
				criteria.add(Restrictions.eq("status", status));
			}

			List<DataPermission> perList = criteria.list();
			session.getTransaction().commit();
			return perList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
