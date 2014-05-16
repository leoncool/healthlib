/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.database.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

/**
 * IC Cloud Billing System Author: Yang Li MSc Computing Science, Imperial
 * College London, 2010
 */
public class BaseDAO {

    public Object getObjectByID(Class<?> clazz, Serializable id) throws InstantiationException, IllegalAccessException {

        try {
            Object obj = new Object();
            Session session = HibernateUtil.beginTransaction();
            obj = (Object) session.get(clazz, id);
            session.getTransaction().commit();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<?> findAll(Class<?> clazz) {
        List<?> objects = null;
        try {
            Session session = HibernateUtil.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            session.getTransaction().commit();
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public List<?> findAllOrderby(Class<?> clazz, String propertyName, String orderType) {
        List<?> objects = null;
        try {
            String ordertype;
            if (orderType.equals("desc")) {
                ordertype = "desc";
            } else if (orderType.equals("asc")) {
                ordertype = "asc";
            } else {
                ordertype = "desc";
            }
            Session session = HibernateUtil.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName() + " order by " + propertyName + " " + ordertype);
            objects = query.list();
        	session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            HibernateUtil.closeSession();
        }
        return objects;
    }

    public List<?> findAllByProperty(Class<?> clazz, String propertyName, String propertyValue, String orderBy, String orderType) {
        List<?> objects = null;
        try {
            String ordertype;
            if (orderType.equals("desc")) {
                ordertype = "desc";
            } else if (orderType.equals("asc")) {
                ordertype = "asc";
            } else {
                ordertype = "desc";
            }
            Session session = HibernateUtil.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName() + " where " + propertyName + " = " + propertyValue + " order by " + orderBy + " " + ordertype);
            objects = query.list();
        	session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in BaseDAO!---   " + e);
            e.printStackTrace();
        } finally {
//            HibernateUtil.closeSession();
        }
        return objects;
    }

    public List<?> findAllByPropertyOrderBy(Class<?> clazz, String propertyName, String propertyValue) {
        List<?> objects = null;
        try {
            Session session = HibernateUtil.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName() + " where " + propertyName + " = " + propertyValue);
            objects = query.list();
        	session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            HibernateUtil.closeSession();
        }
        return objects;
    }
//    public static void main(String args[]) throws InstantiationException, IllegalAccessException
//    {
//        BaseDAO dao=new BaseDAO();
//        dao.getObjectByID(Users.class, "leoncool");
//    }
}
