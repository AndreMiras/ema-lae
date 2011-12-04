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
        // configuration = configuration.configure();
        
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
                configuration = configuration.configure("hibernate-prod.cfg.xml");
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

    /**
     * Forces the hibernate config for tests (always create-destroy the schema).
     * Gets the default configuration object and overrides some configurations
     * for the unit tests.
     * @return a session factory customized for unit tests
     */
    public static SessionFactory getSessionFactoryForTests()
    {
        if (sessionFactory == null)
        {
            try
            {
                // Create the SessionFactory from hibernate.cfg.xml
                Configuration configuration = getDefaultConfiguration();
                configuration = configuration.configure("hibernate-test.cfg.xml");
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
