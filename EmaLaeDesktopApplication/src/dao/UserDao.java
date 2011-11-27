package dao;

import database.entity.Group;
import database.entity.User;
import database.entity.UserProfile;
import java.util.HashMap;
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
    @Override
    public void delete(User user)
    {
        Session session = getSession(); // sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserProfileDao userProfileDao = new UserProfileDao();
        UserProfile userProfile;

        for (Group group : user.getGroups())
        {
            group.getUsersId().remove(user);
            session.update(group);
        }

        try
        {
            userProfile = userProfileDao.get(user);
            session.delete(userProfile);
        }
        catch (Exception ex) // user not found
        {
            System.out.println("TODO: log this exception");
        }
        finally
        {
            // em.remove(user);
            session.delete(user);
            transaction.commit();
            // session.close();
        }
    }
}
