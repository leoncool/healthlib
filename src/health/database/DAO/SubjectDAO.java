/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.database.DAO;

import health.database.models.Datastream;
import health.database.models.JobsTable;
import health.database.models.Subject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.AllConstants;
import util.HibernateUtil;

/**
 *
 * @author Leon
 */
public class SubjectDAO extends BaseDAO {
//
    public Subject getSubjectByID(int subjectid) {
        try {
            Session session = HibernateUtil.beginTransaction();
            Subject obj = (Subject) session.get(Subject.class, subjectid);
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

    public static void main(String args[]) throws InstantiationException, IllegalAccessException, IllegalAccessException {
        SubjectDAO dao = new SubjectDAO();
        Subject subject = (Subject) dao.getObjectByID(Subject.class, 572);
        Calendar cal = Calendar.getInstance();
        cal.setTime(subject.getCreatedTime());
        System.out.println(cal.getTimeZone().getID());
    }

    public Subject createSubject(Subject subject) {
        try {
            Session session = HibernateUtil.beginTransaction();
            session.save(subject);
        	session.getTransaction().commit();
            if (subject.getId() != null) {
                return subject;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
    }
    public Subject findHealthSubject(String loginID) {
        try {
            Session session = HibernateUtil.beginTransaction();
            Criteria criteria = session.createCriteria(Subject.class)
                    .add(Restrictions.eq("loginID", loginID))
                    .add(Restrictions.eq("purpose", AllConstants.HealthConts.default_health_subject_purpose));
            Subject subject= (Subject) criteria.uniqueResult();
            session.getTransaction().commit();
            return subject;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
    }
    public Subject findSystem_Default_Subject(String loginID) {
        try {
            Session session = HibernateUtil.beginTransaction();
            Criteria criteria = session.createCriteria(Subject.class)
                    .add(Restrictions.eq("loginID", loginID))
                    .add(Restrictions.eq("purpose", AllConstants.HealthConts.System_Default_subject_Name));
            Subject subject= (Subject) criteria.uniqueResult();
            session.getTransaction().commit();
            return subject;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
    }
    public List<Subject> findSubjectsByLoginID(String loginID) {
        try {
            Session session = HibernateUtil.beginTransaction();
            Criteria criteria = session.createCriteria(Subject.class)
                    .add(Restrictions.eq("loginID", loginID));
            List<Subject> list = criteria.list();
            session.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
    }
    public void deleteSubjectByID(Subject subject)
    {
    	try{    	
    	DatastreamDAO dsDao=new DatastreamDAO();
        List<Datastream> dsList=dsDao.getDatastreamList(subject.getId(), false, false);
        Session session = HibernateUtil.beginTransaction();
        Date now=new Date();
        session.delete(subject);
        for(Datastream ds:dsList)
        {
        	session.delete(ds);
        	JobsTable job=new JobsTable();
        	job.setCreatedDate(now);
        	job.setUpdatedDate(now);
        	job.setStatus(AllConstants.ProgramConts.job_status_pending);
        	job.setMethod(AllConstants.ProgramConts.job_method_delete);
        	job.setTargetObject(AllConstants.ProgramConts.job_targetObject_datastream);
        	job.setTargetObjectID(ds.getStreamId());
        	session.save(job);
        }
    	session.getTransaction().commit();
        
    } catch (Exception e) {
    	HibernateUtil.rollBackTransaction();
        e.printStackTrace();
    }
    }

    public Subject findDevicePurposeSubject(String loginID) {
        try {
            Session session = HibernateUtil.beginTransaction();
            Criteria criteria = session.createCriteria(Subject.class)
                    .add(Restrictions.eq("loginID", loginID))
                    .add(Restrictions.eq("purpose", AllConstants.ProgramConts.subject_medical_device_purpose));
            Subject subject = (Subject) criteria.uniqueResult();
            session.getTransaction().commit();
            return subject;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
    }

    public List<Subject> findOnlyParentSubjectsByLoginID(String loginID) {
        try {
            Session session = HibernateUtil.beginTransaction();
            Criteria criteria = session.createCriteria(Subject.class)
                    .add(Restrictions.eq("loginID", loginID))
                    .add(Restrictions.isNull("parentSub"));
            List<Subject> list = criteria.list();
            session.getTransaction().commit();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
    }
    public Subject createDefaultHealthSubject(String loginID)
    {
    	
    	   Session session = HibernateUtil.beginTransaction();
    	   Subject subject=new Subject();
    	   subject.setLoginID(loginID);
    	   subject.setPurpose(AllConstants.HealthConts.default_health_subject_purpose);
    	   subject.setTitle(AllConstants.HealthConts.default_health_SubjectName);
    	   subject.setVisibleSet(AllConstants.ProgramConts.visibleSet_PUBLIC);
    	   Date now=new Date();
    	   subject.setCreatedTime(now);
    	   subject.setUpdated(now);
    	   session.save(subject);
    		session.getTransaction().commit();
    	    return subject;
    }
}
