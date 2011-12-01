package dao;

import database.entity.UserGroup;
import database.entity.User;
import database.entity.UserProfile;
import exceptions.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author andre
 */
public class UserDao extends DaoHibernate<User, Integer>
{
    public UserDao()
    {
        super(User.class);
    }

    public UserDao(Class<User> type)
    {
        super(type);
    }

    /**
     * Explicitely deletes user related object
     *  - group
     *  - userProfile
     * @param user
     */
    // @Override
    public void delete2(User user)
    {
        Session session = sessionFactory.openSession(); // getSession();
        Transaction transaction = session.beginTransaction();
        UserProfileDao userProfileDao = new UserProfileDao();
        UserProfile userProfile;

        for (UserGroup group : user.getGroups())
        {
            group.getUsers().remove(user);
            session.update(group);
        }

        try
        {
            userProfile = userProfileDao.get(user);
            session.delete(userProfile);
        }
        catch (DaoException ex) // user not found
        {
            Logger.getLogger(UserDao.class.getName()).log(
                    Level.INFO, null, ex);
        }
        finally
        {
            // em.remove(user);
            session.delete(user);
            transaction.commit();
            session.close();
        }
    }
}
