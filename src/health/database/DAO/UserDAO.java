/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.database.DAO;

import health.database.models.LoginToken;
import health.database.models.UserAvatar;
import health.database.models.UserDetails;
import health.database.models.Users;
import health.database.models.merge.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import util.AllConstants;
import util.HibernateUtil;
import util.ServerConfigUtil;

/**
 * IC Cloud Billing System Author: Yang Li MSc Computing Science, Imperial
 * College London, 2010
 */
public class UserDAO extends BaseDAO {

    public static void main(String args[]) {
        UserDAO userDao = new UserDAO();
        System.out.println(userDao.getLogin("leoncool").getUserDetails().getHeight());
      //  System.out.println(userDao.searchUserInfo("li", 0).size());
    }

    public boolean existLogin(String idLogins) {
        Users user = null;
        try {
            Session session = HibernateUtil.beginTransaction();
            user = (Users) session.get(Users.class, idLogins);
            session.getTransaction().commit();
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public Users getLogin(String idLogins) {
        Users user = null;
        try {
            Session session = HibernateUtil.beginTransaction();
            user = (Users) session.get(Users.class, idLogins);
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserInfo getUserInfo(String loginID) {
        Session session = HibernateUtil.beginTransaction();
        Users user = (Users) session.get(Users.class, loginID);
        session.getTransaction().commit();
        UserInfo userinfo = new UserInfo();
        userinfo.setUser(user);
        session = HibernateUtil.beginTransaction();
        if(user.getUserAvatar()==null)
        {
        	UserAvatar avatar=new UserAvatar();
        	UUID uuid=UUID.randomUUID();
        	avatar.setId(uuid.toString());
        	avatar.setUrl(ServerConfigUtil.getConfigValue(AllConstants.ServerConfigs.UndefinedAvatarLocation));
        	avatar.setUsers(user);
        	user.setUserAvatar(avatar);        	
        	session.saveOrUpdate(avatar);
       	}
        if(user.getUserDetails()==null){
        	UserDetails detail=new UserDetails();
        	detail.setUsers(user);        	
        	user.setUserDetails(detail);
        	session.saveOrUpdate(detail);
        	
       	}        
    	session.getTransaction().commit();
        userinfo.setAvatar(user.getUserAvatar());
        return userinfo;
    }
    public List<UserInfo> ListUsers(int startFrom) {
        Session session = HibernateUtil.beginTransaction();
        Query query = session.createQuery("from Users u,UserAvatar a where u.loginID =a.loginID"
                + "");
       // query.setParameter("keywords", "%" + keywords + "%");
        query.setFirstResult(startFrom * AllConstants.HibernateConsts.UserList_maxPageSize);
        query.setMaxResults(AllConstants.HibernateConsts.UserList_maxPageSize);
        List<Object[]> list = query.list();
        session.getTransaction().commit();
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        if (list.isEmpty()) {
            return userInfoList;
        }
        for (Object[] result : list) {
            Users user = (Users) result[0];
            UserAvatar avatar = null;
            if (result[1] != null) {
                avatar = (UserAvatar) result[1];
            } else {
            }
            UserInfo userinfo = new UserInfo();
            userinfo.setUser(user);
            userinfo.setAvatar(avatar);
            userInfoList.add(userinfo);
        }

        return userInfoList;
    }

    public List<UserInfo> searchUserInfo(String keywords, int startFrom,Map<String,String> ignoreMap) {
        Session session = HibernateUtil.beginTransaction();
        Query query = session.createQuery("from Users u,UserAvatar a where u.loginID =a.loginID AND"
                + "(u.loginID like :keywords OR u.screenname like:keywords OR u.email like :keywords)");
        query.setParameter("keywords", "%" + keywords + "%");
        query.setFirstResult(startFrom * AllConstants.HibernateConsts.UserSearch_maxPageSize);
        query.setMaxResults(AllConstants.HibernateConsts.UserSearch_maxPageSize);
        List<Object[]> list = query.list();
        session.getTransaction().commit();
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        if (list.isEmpty()) {
            return userInfoList;
        }
        for (Object[] result : list) {
            Users user = (Users) result[0];
            if(!ignoreMap.containsKey(user.getLoginID()))
            {
            UserAvatar avatar = null;
            if (result[1] != null) {
                avatar = (UserAvatar) result[1];
            } else {
            }
            UserInfo userinfo = new UserInfo();
            userinfo.setUser(user);
            userinfo.setAvatar(avatar);
            userInfoList.add(userinfo);
            }
        }

        return userInfoList;
    }
public LoginToken requestNewLoginToken(String loginID,String ip,Date expireTime)
{
	LoginToken token=new LoginToken();
	UUID uuid=UUID.randomUUID();
	
	token.setLoginID(loginID);
	token.setTokenID(uuid.toString().replaceAll("-", ""));
	if(expireTime!=null)
	{
		token.setExpireTime(expireTime);
	}
	token.setIp(ip);
	token.setCreated(new Date());
	Session session=HibernateUtil.beginTransaction();
	session.save(token);
	session.getTransaction().commit();
	return token;
}
    public List<Users> searchLogin(String keywords, int startFrom) {
        List<Users> userList = new ArrayList<Users>();
        try {
            Session session = HibernateUtil.beginTransaction();
            Criteria criteria = session.createCriteria(Users.class, "users");
            Criterion loginCri = Restrictions.ilike("loginID", keywords, MatchMode.ANYWHERE);
            Criterion screennameCri = Restrictions.ilike("screenname", keywords, MatchMode.ANYWHERE);
            Criterion emailCri = Restrictions.ilike("email", keywords, MatchMode.ANYWHERE);

            criteria.setFirstResult(startFrom
                    * AllConstants.HibernateConsts.UserSearch_maxPageSize);
            criteria.setMaxResults(AllConstants.HibernateConsts.UserSearch_maxPageSize);
            LogicalExpression orExp = Restrictions.or(loginCri, screennameCri);
            LogicalExpression orExp2 = Restrictions.or(orExp, emailCri);

            criteria.add(orExp2);
            userList = criteria.list();

            session.getTransaction().commit();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkLogin(String loginID, String password) {
        Session session = HibernateUtil.beginTransaction();


        try {
            Users user = (Users) session.get(Users.class, new String(loginID));
            if (user.getPassword()
                    == null ? password == null : user.getPassword().equals(password)) {
            	   session.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;

    }

    public boolean EmailExists(String Email) {
        List<Users> objects = null;
        Session session = HibernateUtil.beginTransaction();
        Users user = new Users();
        Criteria criteria = session.createCriteria(Users.class);
        Example example = Example.create(user);

        criteria.add(Expression.eq("email", Email));
        example.enableLike(MatchMode.ANYWHERE);

        example.ignoreCase();

        example.excludeZeroes();

        criteria.add(example);


        try {
            objects = criteria.list();
        } catch (Exception ex) {
            System.out.println("checkEmailExists" + ex);
            ex.printStackTrace();
        }

        session.getTransaction().commit();

        if (!objects.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public LoginToken getLoginToken(String tokenID)
    {
    	Session session=HibernateUtil.beginTransaction();
    	  LoginToken token = (LoginToken) session.get(LoginToken.class, tokenID);
    	   session.getTransaction().commit();
          return token;
    }
//    public List<Logins> SearchLogin(String idLogins, boolean fuzzy) {
//        List objects = null;
//        Session session = HibernateUtil.beginTransaction();
//        Logins login = new Logins();
//        Criteria criteria = session.createCriteria(Logins.class);
//        if (idLogins != null) {
//            criteria.add(Expression.like("idLogins", idLogins, MatchMode.ANYWHERE));
//        }
//        Example example = Example.create(login);
//        if (fuzzy) {
//            example.enableLike(MatchMode.ANYWHERE);
//            example.ignoreCase();
//            example.excludeZeroes();
//        }
//        criteria.add(example);
//        try {
//            objects = criteria.list();
//        } catch (Exception ex) {
//            System.out.println("find by example in SoftwareDAO---- error here" + ex);
//            ex.printStackTrace();
//        }
//          if (session.isOpen()) {
//            session.close();
//        }
//        return objects;
//    }
}
