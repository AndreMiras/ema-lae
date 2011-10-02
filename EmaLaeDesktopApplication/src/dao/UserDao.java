/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.User;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author andre
 */
public class UserDao extends DaoHibernate<User> {

    public Integer create(User obj)
    {
        return (Integer) getSession().save(obj);
    }

    public User read(Integer id)
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

    public List<User> find(Hashtable<String, String> querySet)
    {
        String hqlString = "from User as user where ";
        String filterString = "";
        Enumeration<String> e = querySet.keys();

        //iterate through Hashtable keys Enumeration
        while (e.hasMoreElements())
        {
            filterString += e.toString() + " = " + querySet.get(e.toString());
            if (e.hasMoreElements())
            {
                filterString += " and ";
            }
            e.nextElement();
        }
        // session.createQuery("from Student s where s.name like 'k%'");

        // Criteria criteria = session.createCriteria(User.class);

        /*
         * Query query = session.createQuery("from Stock where stockCode = :code ");
         * query.setParameter("code", "7277");
         * List list = query.list();
         */
        // Query query = session.createQuery("from Yser as user where");
        // objects = query.list();
        // TODO[security]: is there a security risk, using plain HQL ?
        // Hibernate.entity(User.class)

    List objs = getSession().createQuery(hqlString + filterString).list();
    return objs;
    }

    public List<User> all()
    {
        return getSession().createQuery("from "
                + User.class.getName()).list();
    }

}
