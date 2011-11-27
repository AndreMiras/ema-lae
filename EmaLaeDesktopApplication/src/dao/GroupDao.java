/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Group;
import database.entity.Permission;
import database.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pc
 */
public class GroupDao extends DaoHibernate<Group, Integer> {

    public GroupDao()
    {
        super(Group.class);
    }

    public GroupDao(Class<Group> type)
    {
        super(type);
    }

    /**
     * Explicitely deletes group related object
     *  - user
     *  - permission
     * @param group
     */
    @Override
    public void delete(Group group)
    {
        Session session = getSession(); // sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // deletes associated users
        for (User user : group.getUsers())
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
        // session.close();
    }
}
