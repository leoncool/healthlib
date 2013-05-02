/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.DAO;

import com.lifestyle.models.Locationlogs;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author Leon
 */
public class LocationLogDAO {

    public void putLog(Locationlogs log) {
//        if (exist(log)) {
//            return;
//        }
        Session session = HibernateUtil.beginTransaction();
        session.saveOrUpdate(log);
        HibernateUtil.commitTransaction();
        if (session.isOpen()) {
            HibernateUtil.closeSession();
        }
    }

    public void putListLog(List<Locationlogs> logList) {
//        if (exist(log)) {
//            return;
//        }
        Session session = HibernateUtil.beginTransaction();
        int i = 0;
        for (Locationlogs log : logList) {
            session.saveOrUpdate(log);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
        HibernateUtil.commitTransaction();
        if (session.isOpen()) {
            HibernateUtil.closeSession();
        }
    }

    public boolean exist(Locationlogs log) {
        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(Locationlogs.class);
        criteria.add(Restrictions.eq("userID", log.getUserID()));
        criteria.add(Restrictions.eq("datetime", log.getDatetime()));
        Locationlogs retrievedLog = (Locationlogs) criteria.uniqueResult();
        if (session.isOpen()) {
            session.close();
        }
        if (retrievedLog == null) {
            return false;
        } else {
            return true;
        }
    }

    public List<Locationlogs> getLocatioonLogs(String userID, Date startTime, Date endTime) {
        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(Locationlogs.class);
        criteria.add(Restrictions.eq("userID", userID));
        if (startTime != null) {
            criteria.add(Restrictions.ge("datetime", startTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("datetime", endTime));
        }
        criteria.addOrder(Order.asc("datetime"));
        List<Locationlogs> list = criteria.list();
        if (session.isOpen()) {
            session.close();
        }
        return list;
    }

    public static void main(String args[]) {
        //  Locationlogs log = new Locationlogs("leoncool", new Date(), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//        Locationlogs log=new Locationlogs();
//        log.setUserID("leoncool");
//        log.setAccuracy(0.0);
//        log.setBearing(0.0);
//        log.setElevation(0.0);
//        log.setLat(0.0);
//        log.setLon(0.0);
//        log.setSpeed(0.0);
        LocationLogDAO dao = new LocationLogDAO();
//        dao.putLog(log);
    }
}
