package dao;

import database.entity.Group;
import database.entity.User;
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

    @Override
    public void delete(User user)
    {
        Session session = sessionFactory.openSession(); // = getSession();
        Transaction transaction = session.beginTransaction();
        for (Group group : user.getGroups())
        {
            group.getUsersId().remove(user);
            session.update(group);
        }
        // em.remove(user);
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
