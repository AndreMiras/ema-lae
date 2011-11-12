/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Group;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author pc
 */
public class GroupDao extends DaoHibernate<Group> {

    public Integer create(Group obj) {
        Session session = getSession();
        session.beginTransaction();
        Integer id = (Integer) getSession().save(obj);
        session.getTransaction().commit();

        return id;
    }

    public Group read(Integer id) {
        Session session = getSession();
        session.beginTransaction();
        Group group = (Group) getSession().get(Group.class, id);
        session.getTransaction().commit();

        return group;
    }

    public void update(Group obj) {
        Session session = getSession();
        session.beginTransaction();
        getSession().update(obj);
        session.getTransaction().commit();
    }

    public void delete(Group obj) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
    }

    public List<Group> find(HashMap<String, String> querySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Group get(HashMap<String, String> querySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Group> all() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
