/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.User;
import database.entity.UserProfile;
import java.util.Hashtable;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author andre
 */
public class UserProfileDao extends DaoHibernate<UserProfile> {

    public Integer create(UserProfile obj)
    {
        Session session = getSession();
        session.beginTransaction();
        Integer id = (Integer) getSession().save(obj);
        session.getTransaction().commit();

        return id;
    }

    public UserProfile read(Integer id)
    {
        Session session = getSession();
        session.beginTransaction();
        UserProfile userProfile = (UserProfile) getSession().get(UserProfile.class, id);
        session.getTransaction().commit();

        return userProfile;
    }

    public void update(UserProfile obj)
    {
        Session session = getSession();
        session.beginTransaction();
        getSession().update(obj);
        session.getTransaction().commit();
    }

    public void delete(UserProfile obj)
    {
        Session session = getSession();
        session.beginTransaction();
        getSession().delete(obj);
        session.getTransaction().commit();
    }

    public List<UserProfile> find(Hashtable<String, String> querySet)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public UserProfile get(Hashtable<String, String> querySet)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public UserProfile get(User user)
    {
        String hqlString = "from UserProfile as user_profile where ";
        String filterString = "";
        Session session = getSession();

        session.beginTransaction();
        // TODO[security]: is there a security risk, using plain HQL ?
        List objs = getSession().createQuery(hqlString + filterString).list();
        session.getTransaction().commit();
        return (UserProfile) objs.get(0);
    }

    public List<UserProfile> all()
    {
        Session session = getSession();

        session.beginTransaction();
        List userProfiles = getSession().createQuery("from "
                + UserProfile.class.getName()).list();
        session.getTransaction().commit();

        return userProfiles;
    }

}
