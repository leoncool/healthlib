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
        HibernateUtil.commitTransaction();
        if (session.isOpen()) {
            session.close();
        }
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
