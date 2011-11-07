/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.util;


import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author andre
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
  try {
  // Create the SessionFactory from hibernate.cfg.xml
  sessionFactory = new
  AnnotationConfiguration().configure().buildSessionFactory();
  } catch (Throwable ex) {
  // Make sure you log the exception, as it might be swallowed
  System.err.println("Initial SessionFactory creation failed." + ex);
  throw new ExceptionInInitializerError(ex);
  }
  }

    /*static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            // sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }*/

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
