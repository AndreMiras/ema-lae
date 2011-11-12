package dao;

import database.entity.User;

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
}
