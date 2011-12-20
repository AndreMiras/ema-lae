package dao;

import database.entity.UserGroup;
import database.entity.Users;
import database.entity.UserProfile;
import exceptions.DaoException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Explicitely deletes user related objects.
     * This is only required when the cascade delete isn't enabled.
     * Currently deletes the following objects:
     *  - group
     *  - userProfile
     * @param user
     */
    // @Override
    public void delete2(Users user)
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
        // transaction.commit();
        session.flush();

        // session.beginTransaction();
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
            session.flush();
            session.close();
        }
    }


    @Override
    public Integer create(Users u){
        Integer pk = null;
        try {
            pk = super.create(u);
        } catch (DaoException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pk;
    }

    /**
     * Explicitely updates the many-to-many "groups" field.
     * This is required because Users doesn't own the m2m relation.
     * This is mainly used when performing a user.setGroups(newGroupSet)
     * followed by a userDao.save(user)
     * @param user
     */
    @Override
    public void update(Users user)
    {
        GroupDao groupDao = new GroupDao();
        Users beforeUpdateUser = read(user.getUserId());
        Set<UserGroup> beforeUpdateGroups =
                beforeUpdateUser.getGroups();

        for (UserGroup beforeUpdateGroup: beforeUpdateGroups)
        {
            /*
             * if the old group isn't part of the new group set,
             * updates it by removing the previous associated user.
             */
            if(!user.containsGroup(beforeUpdateGroup))
            {
                beforeUpdateGroup.removeUser(user);
                groupDao.update(beforeUpdateGroup);
            }
        }
        super.update(user);
    }
}
