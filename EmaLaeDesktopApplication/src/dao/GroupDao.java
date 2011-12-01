/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.UserGroup;
import database.entity.Permission;
import database.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pc
 */
public class GroupDao extends DaoHibernate<UserGroup, Integer> {

    public GroupDao()
    {
        super(UserGroup.class);
    }

    public GroupDao(Class<UserGroup> type)
    {
        super(type);
    }

    /**
     * Explicitely deletes group related object
     *  - user
     *  - permission
     * @param group
     */
    // @Override
    public void delete2(UserGroup group)
    {
        Session session = sessionFactory.openSession(); // getSession();
        Transaction transaction = session.beginTransaction();

        // deletes associated users
        for (Users user : group.getUsers())
        {
            user.getGroups().remove(group);
            session.update(user);
        }

        // deletes associated permissions
        for (Permission permission : group.getPermissions())
        {
            permission.getGroups().remove(group);
            session.update(permission);
        }

        session.delete(group);
        transaction.commit();
        session.flush();
        session.close();
    }
}
