package health.database.DAO;

import health.database.models.CreationTemplate;
import health.database.models.DatastreamUnits;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class creationTemplateDAO extends BaseDAO {
	public List<CreationTemplate> findall() {

		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(CreationTemplate.class);
		List<CreationTemplate> list = criteria.list();
		if (session.isOpen()) {
			session.close();
		}
		return list;
	}
}
