/*
 * Implementing DAO using Hibernate ORM
 */

package dao;

import database.util.HibernateUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author andre
 */
public class DaoHibernate<T, PK extends Serializable>
        implements IDao<T, PK> {
    
    private Class<T> type;
    private Session _session = HibernateUtil.getSessionFactory().openSession();

    
    public DaoHibernate(Class<T> type) {
        this.type = type;
    }

    public Session getSession()
    {
        return _session;
    }

    public PK create(T obj)
    {
        // return (PK) getSession().save(obj);
        Session session = getSession();
        session.beginTransaction();
        PK id = (PK) session.save(obj);
        session.getTransaction().commit();

        return id;
    }

    public T read(PK id)
    {
        // return (T) getSession().get(type, id);
        Session session = getSession();
        session.beginTransaction();
        T obj = (T) session.get(type, id);
        session.getTransaction().commit();

        return obj;
    }

    public void update(T obj)
    {
        // getSession().update(o);
        Session session = getSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
    }

    public void delete(T obj)
    {
        // getSession().delete(o);
        Session session = getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
    }

    public List<T> find(HashMap<String, String> querySet)
    {
        String hqlString = "from " + type.getName() +
                " as " + type.getName().toLowerCase() + " where ";
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
        List objs = session.createQuery(hqlString + filterString).list();
        session.getTransaction().commit();
        return objs;
    }

    public T get(HashMap<String, String> querySet)
    {
        List objs = find(querySet);
        if (objs.size() != 1)
            throw new Error("Returned "
                    + objs.size()
                    + " object(s) when it should return one and only one.");
        return (T) objs.get(0);
    }

    public List<T> all()
    {
        /*
        Criteria crit = getSession().createCriteria(type);
        return crit.list();
         */
        Session session = getSession();

        session.beginTransaction();
        List objs = session.createQuery("from "
                + type.getName()).list();
        session.getTransaction().commit();

        return objs;
    }

}
