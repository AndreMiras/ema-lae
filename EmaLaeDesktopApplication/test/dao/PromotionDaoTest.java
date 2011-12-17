/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.util.HibernateUtil;
import database.entity.Promotion;
import exceptions.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PromotionDaoTest
{
    public PromotionDaoTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        HibernateUtil.getSessionFactoryForTests();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        HibernateUtil.getSessionFactoryForTests().close();
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }


    @Test
    public void testCreate()
    {
        System.out.println("create");
        Promotion promotion = new Promotion("promo1");
        GenericDao<Promotion> promotionDao =
                new GenericDao<Promotion>(Promotion.class);
        Integer promotionPk = null;
        try
        {
            promotionPk = promotionDao.create(promotion);
        } catch (DaoException ex)
        {
            Logger.getLogger(PromotionDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // the object should now have an id given from the DAO
        assertNotNull(promotionPk);
    }

    @Test
    public void testRead()
    {
        System.out.println("read");
        Promotion promotionFromDb;
        Promotion promotion = new Promotion("promo2");
        GenericDao<Promotion> promotionDao =
                new GenericDao<Promotion>(Promotion.class);
        Integer promotionPk = null;
        try
        {
            promotionPk = promotionDao.create(promotion);
        } catch (DaoException ex)
        {
            Logger.getLogger(PromotionDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // verifies we can retrieve the promotion from the database
        promotionFromDb = promotionDao.read(promotionPk);
        assertTrue(promotionFromDb.getName().equals(promotion.getName()));
    }

    @Test
    public void testUpdate()
    {
        System.out.println("update");
        fail("TODO");
    }

    @Test
    public void testDelete()
    {
        System.out.println("delete");
        fail("TODO");
    }
}