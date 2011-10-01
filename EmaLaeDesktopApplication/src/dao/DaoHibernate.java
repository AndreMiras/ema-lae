/*
 * Implementing DAO using Hibernate ORM
 */

package dao;

import database.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author andre
 */
public abstract class DaoHibernate<T> implements IDao<T> {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Session getSession()
    {
        return session;
    }

}
