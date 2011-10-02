package dao;

import database.entity.User;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        Set set = querySet.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());

            filterString += entry.getKey() + " = '" + entry.getValue() + "'";
            if (it.hasNext())
            {
                filterString += " and ";
            }
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
