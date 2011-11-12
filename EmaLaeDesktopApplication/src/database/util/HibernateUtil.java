/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author andre
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     *
     * @return the default configuration object
     */
    private static Configuration getDefaultConfiguration()
    {
        Configuration configuration = new AnnotationConfiguration();
        // configuration = configuration.configure("hibernate.cfg.xml");
        configuration = configuration.configure();

        return configuration;
    }

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try
            {
                // Create the SessionFactory from hibernate.cfg.xml
                Configuration configuration = getDefaultConfiguration();
                // configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
                sessionFactory = configuration.buildSessionFactory();
            }
            catch (Throwable ex)
            {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
}
