/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Permission;
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
public class PermissionsDao extends DaoHibernate<Permission> {

    public Integer create(Permission obj) {
        Session session = getSession();
        session.beginTransaction();
        Integer id = (Integer) getSession().save(obj);
        session.getTransaction().commit();

        return id;
    }

    public Permission read(Integer id) {
        Session session = getSession();
        session.beginTransaction();
        Permission permission = (Permission) getSession().get(Permission.class, id);
        session.getTransaction().commit();

        return permission;
    }

    public void update(Permission obj) {
        Session session = getSession();
        session.beginTransaction();
        getSession().update(obj);
        session.getTransaction().commit();
    }

    public void delete(Permission obj) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
    }

    public List<Permission> find(HashMap<String, String> querySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Permission get(HashMap<String, String> querySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Permission> all() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
