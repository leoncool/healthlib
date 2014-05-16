package health.database.DAO;

import health.database.models.JobsTable;

import org.hibernate.Session;

import util.HibernateUtil;

public class JobDAO {
public JobsTable createJob(JobsTable job)
{
    try {
        Session session = HibernateUtil.beginTransaction();
        session.save(job);
    	session.getTransaction().commit();
        if (job.getId()>0) {
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
}
