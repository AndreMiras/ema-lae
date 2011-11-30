package dao;

import database.entity.UserGroup;
import database.entity.Users;
import database.entity.UserProfile;
import java.util.HashMap;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author andre
 */
public class UserDao extends DaoHibernate<Users, Integer>
{
    public UserDao()
    {
        super(Users.class);
    }

    public UserDao(Class<Users> type)
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
    public void delete2(Users user)
    {
        Session session = getSession(); // sessionFactory.openSession();
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
