package health.database.DAO.as;

import health.database.models.as.AnalysisModel;
import health.database.models.as.AnalysisModelEntry;
import health.database.models.as.AnalysisModelMapping;
import health.database.models.as.AnalysisService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.AScontants;
import util.HibernateUtil;

public class AnalysisServiceDAO {
	public AnalysisModel getModelByID(String modelID) {
		try {
			Session session = HibernateUtil.beginTransaction();
			AnalysisModel obj = (AnalysisModel) session.get(
					AnalysisModel.class, modelID);
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

	public List<AnalysisModelEntry> getModelEntriesByModelID(String modelID,
			String entryType) {
		try {
			Session session = HibernateUtil.beginTransaction();
			Criteria criteria = session
					.createCriteria(AnalysisModelEntry.class);
			criteria.add(Restrictions.eq("model_id", modelID));
			if (entryType != null) {
				criteria.add(Restrictions.eq("entryType", entryType));
				criteria.addOrder(Order.asc("numOrder"));
			}
			List<AnalysisModelEntry> entryList = criteria.list();
			session.getTransaction().commit();
			return entryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<AnalysisModel> getModelList(String searchName) {
		try {
			Session session = HibernateUtil.beginTransaction();
			Criteria criteria = session.createCriteria(AnalysisModel.class);
			if (searchName != null) {
				criteria.add(Restrictions.like("name", "%" + searchName + "%"));
			}

			List<AnalysisModel> entryList = criteria.list();
			session.getTransaction().commit();
			return entryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<AnalysisModel> getModelListByModelName(String modelName) {
		try {
			Session session = HibernateUtil.beginTransaction();
			Criteria criteria = session.createCriteria(AnalysisModel.class);
			criteria.add(Restrictions.eq("name", modelName));
			List<AnalysisModel> entryList = criteria.list();
			session.getTransaction().commit();
			return entryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean deleteModelByID(String modelID) {
		try {
			Session session = HibernateUtil.beginTransaction();
			AnalysisModel obj = (AnalysisModel) session.get(
					AnalysisModel.class, modelID);
			session.delete(modelID);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public AnalysisModel createModel(AnalysisModel model) {
		try {
			Session session = HibernateUtil.beginTransaction();
			session.save(model);
			session.getTransaction().commit();
			if (model != null) {
				return model;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public AnalysisService createServiceBy(AnalysisService object) {
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

	public int createModelEntries(List<AnalysisModelEntry> entries) {
		try {
			int counter = 0;

			Session session = HibernateUtil.beginTransaction();
			for (AnalysisModelEntry entry : entries) {
				session.save(entry);
				counter++;
			}
			session.getTransaction().commit();
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int updateModel(AnalysisModel model) {
		try {
			Session session = HibernateUtil.beginTransaction();
			session.update(model);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int updateModelEntries(List<AnalysisModelEntry> entries) {
		try {
			int counter = 0;

			Session session = HibernateUtil.beginTransaction();
			for (AnalysisModelEntry entry : entries) {
				session.update(entry);
				counter++;
			}
			session.getTransaction().commit();
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int createModelMappings(List<AnalysisModelMapping> mappings) {
		try {
			int counter = 0;

			Session session = HibernateUtil.beginTransaction();
			for (AnalysisModelMapping entry : mappings) {
				session.save(entry);
				counter++;
			}
			session.getTransaction().commit();
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void main(String args[]) {
		AnalysisServiceDAO dao = new AnalysisServiceDAO();
		// AnalysisModel model = new AnalysisModel();
		// UUID uuid = UUID.randomUUID();
		// model.setCreatedTime(new Date());
		// model.setName("test model");
		// model.setId(uuid.toString());
		// AnalysisModelEntry input1 = new AnalysisModelEntry(model.getId(),
		// AScontants.as_input, AScontants.fileType, "", 1);
		// List<AnalysisModelEntry> entryList = new ArrayList<>();
		// entryList.add(input1);
		// model = dao.createModel(model);
		// if (model != null) {
		// dao.createModelEntries(entryList);
		// }
		List<AnalysisModelEntry> entryList = dao.getModelEntriesByModelID(
				"47b4a33a-14ec-4217-a802-49359486704b", "input");
		for (AnalysisModelEntry entry : entryList) {
			System.out.println(entry.getId() + "," + entry.getOrder());
		}
	}
}
