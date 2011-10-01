/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.User;

/**
 *
 * @author andre
 */
public class UserDao extends DaoHibernate<User> {

    public User create(User obj)
    {
        return (User) getSession().save(obj);
    }

    public User read(long id)
    {
        return (User) getSession().get(User.class, id);
    }

    public void update(User obj)
    {
        getSession().update(obj);
    }

    public void delete(User obj)
    {
        getSession().delete(obj);
    }

}
