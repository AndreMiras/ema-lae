package dao;

import database.entity.User;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author andre
 */
public class UserDao extends DaoHibernate<User> {

    public Integer create(User obj)
    {
        Session session = getSession();
        session.beginTransaction();
        Integer id = (Integer) getSession().save(obj);
        session.getTransaction().commit();

        return id;
    }

    public User read(Integer id)
    {
        Session session = getSession();
        session.beginTransaction();
        User user = (User) getSession().get(User.class, id);
        session.getTransaction().commit();

        return user;
    }

    public void update(User obj)
    {
        Session session = getSession();
        session.beginTransaction();
        getSession().update(obj);
        session.getTransaction().commit();
    }

    public void delete(User obj)
    {
        Session session = getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
    }

    public List<User> find(Hashtable<String, String> querySet)
    {
        String hqlString = "from User as user where ";
        String filterString = "";
        Session session = getSession();

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

        // TODO: should we use criteria rather than plain hql
        /*
         * Query query = session.createQuery("from Stock where stockCode = :code ");
         * query.setParameter("code", "7277");
         * List list = query.list();
         */
        // Hibernate.entity(User.class)

    session.beginTransaction();
    // TODO[security]: is there a security risk, using plain HQL ?
    List objs = getSession().createQuery(hqlString + filterString).list();
    session.getTransaction().commit();
    return objs;
    }

    /*
     * TODO: throw exception on multiple value found
     */
    public User get(Hashtable<String, String> querySet)
    {
        List objs = find(querySet);
        if (objs.size() != 1)
            throw new Error("Returned "
                    + objs.size()
                    + " object(s) when it should return one and only one.");
        return (User) objs.get(0);
    }

    public List<User> all()
    {
        Session session = getSession();

        session.beginTransaction();
        List users = getSession().createQuery("from "
                + User.class.getName()).list();
        session.getTransaction().commit();

        return users;
    }

}
