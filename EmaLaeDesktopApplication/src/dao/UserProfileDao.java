/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.User;
import database.entity.UserProfile;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author andre
 */
public class UserProfileDao extends DaoHibernate<UserProfile, Integer> {

    public UserProfileDao()
    {
        super(UserProfile.class);
    }

    public UserProfileDao(Class<UserProfile> type)
    {
        super(type);
    }

    /**
     *
     * @param user
     * @return an userprofile object from its user object foreign key
     */
    public UserProfile get(User user)
    {
        String hqlString = "from UserProfile as userprofile where ";
        String filterString = "user_id = '" + user.getUserId() + "'";
        Session session = sessionFactory.openSession(); // getSession();
        Transaction transaction = session.beginTransaction();

        session.beginTransaction();
        // TODO[security]: is there a security risk, using plain HQL ?
        List objs = session.createQuery(hqlString + filterString).list();
        transaction.commit();
        session.close();
        return (UserProfile) objs.get(0);
    }

}
