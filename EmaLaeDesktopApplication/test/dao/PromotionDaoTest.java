/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Formation;
import java.util.List;
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

    /**
     * Tries to change a promotion name
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        String newPromotionName = "NewPromotionName";
        GenericDao<Promotion> promotionDao =
                new GenericDao<Promotion>(Promotion.class);
        Promotion promotion = promotionDao.all().get(0);

        assertFalse(promotion.getName().equals(newPromotionName));
        promotion.setName(newPromotionName);
        assertTrue(promotion.getName().equals(newPromotionName));

        // Lets now hit the database with the change
        promotionDao.update(promotion);
        // and re-read from it
        promotion = promotionDao.read(promotion.getPromotionId());
        assertTrue(promotion.getName().equals(newPromotionName));
    }

    @Test
    public void testDelete()
    {
        System.out.println("delete");
        GenericDao<Promotion> promotionDao =
                new GenericDao<Promotion>(Promotion.class);
        List<Promotion> promotions = promotionDao.all();
        Promotion promotionToDelete = promotions.get(0);
        Integer countBeforeDelete = promotions.size();

        // deleting the selected promotion from the database
        promotionDao.delete(promotionToDelete);

        // the number of total promotions should have decreased
        assertTrue(promotionDao.all().size() == countBeforeDelete -1);
    }

    @Test
    public void testAddFormation()
    {
        System.out.println("testAddFormation");
        Promotion promotion = new Promotion("promotion4addFormation");
        GenericDao<Promotion> promotionDao =
                new GenericDao<Promotion>(Promotion.class);
        Formation formation = new Formation("formation4addFormation");
        Integer promotionPk = null;
        try
        {
            promotionPk = promotionDao.create(promotion);
            promotion.addFormation(formation);
            promotionDao.update(promotion);
            promotion = promotionDao.read(promotionPk);
            assertTrue(promotion.containsFormation(formation));
        } catch (DaoException ex)
        {
            Logger.getLogger(PromotionDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSetFormations()
    {
        fail("not implemented yet");
    }
}