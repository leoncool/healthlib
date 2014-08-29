package health.database.DAO;

import java.util.HashMap;

import javax.persistence.NonUniqueResultException;

import health.database.DAO.nosql.HBaseDatapointDAO;
import health.database.models.Datastream;
import health.database.models.DatastreamUnits;
import health.database.models.JobsTable;
import health.hbase.models.HBaseDataImport;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.exception.ErrorCodeException;
import util.AllConstants;
import util.HibernateUtil;

public class JobDAO {
	public JobsTable createJob(JobsTable job) {
		try {
			Session session = HibernateUtil.beginTransaction();
			session.save(job);
			session.getTransaction().commit();
			
			if (job.getId() > 0) {
				return job;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
		}
	}

	public JobsTable getJobByID(int jobID) throws NonUniqueResultException {
		JobsTable job = null;
		// stream = (Datastream) session.get(Datastream.class, StreamID);
		Session session = HibernateUtil.beginTransaction();
		job = (JobsTable) session.get(JobsTable.class, jobID);

		session.getTransaction().commit();
		return job;
	}

	public static void main(String args[]) throws ErrorCodeException {
		JobDAO jobDao = new JobDAO();
		JobsTable job = jobDao.getJobByID(167);
		System.out.println(job.getMethod());
		HBaseDatapointDAO dpDap = new HBaseDatapointDAO();
		DatastreamDAO dsDao = new DatastreamDAO();
		Datastream ds = dsDao.getDatastream(job.getTargetObjectID(), true,
				true);
		HashMap<String, String> dsUnitsList = new HashMap<>();
		for (DatastreamUnits unit : ds.getDatastreamUnitsList()) {
			dsUnitsList.put(unit.getShortUnitID(), unit.getShortUnitID());
			System.out.println(unit.getShortUnitID());
		}
	}

}
