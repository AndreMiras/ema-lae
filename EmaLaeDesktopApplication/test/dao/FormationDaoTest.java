/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Formation;
import database.util.HibernateUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author logar
 */
public class FormationDaoTest {

    public FormationDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        HibernateUtil.getSessionFactoryForTests();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        HibernateUtil.getSessionFactoryForTests().close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

   @Test
    public void testCreate() {
        System.out.println("create");
        Formation obj = new Formation();

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(obj.getID());

        FormationDao instance = new FormationDao();
        Integer result = instance.create(obj);

        // the object should now have an id given from the DAO
        assertNotNull(result);
    }

    /**
     * Test of read method, of class GroupDao.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Formation formation = new Formation("formation4read");

        FormationDao instance = new FormationDao();
        Integer formationID = instance.create(formation);
        Formation result = instance.read(formationID);
        assertEquals("formation4read", result.getName());
    }

    /**
     * Test of update method, of class GroupDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Formation newFormation = new Formation("formation4Update");
        FormationDao instance = new FormationDao();
        Integer newFormationID = instance.create(newFormation);

        // re-read the saved newUser from the database
        newFormation = instance.read(newFormationID);
        // update it locally
        newFormation.setName("formation4UpdateNew");
        // re-hit the database
        instance.update(newFormation);
        // re-read the updated newUser from the database
        newFormation = instance.read(newFormationID);
        assertEquals("formation4UpdateNew", newFormation.getName());
    }

    /**
     * Test of delete method, of class GroupDao.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        FormationDao instance = new FormationDao();
        List<Formation> allFormationsBefore = instance.all();
        Formation formationToDelete = allFormationsBefore.get(0);

        /*
         * it should be possible to read that userprofile from the database before
         * it's been deleted
         */
        assertNotNull(instance.read(formationToDelete.getID()));
        // deleting the first record
        instance.delete(formationToDelete);
        List<Formation> allFormationsAfter = instance.all();
        // not record should now be found
        assertNull(instance.read(formationToDelete.getID()));
        // only one record was deleted
        assertTrue(allFormationsBefore.size() -1 == allFormationsAfter.size());
    }


    @Test(expected = HibernateException.class)
    public void testAddFormationShouldFail() {
        System.out.println("create");
        Formation f1 = new Formation("parent formation");
        FormationDao instance = new FormationDao();
        Integer result = instance.create(f1);

        Formation f2 = new Formation(f1,"child formation");
        assertFalse(f1.getChildrenFormations().contains(f2));

        f1.addFormation(f2);

        instance.update(f1);
    }
    
    @Test
    public void testAddFormation() {
        System.out.println("create");
        Formation f1 = new Formation("parent formation");
        FormationDao instance = new FormationDao();
        Integer result = instance.create(f1);

        Formation f2 = new Formation(f1,"child formation");
        assertFalse(f1.getChildrenFormations().contains(f2));

        f1.addFormation(f2);

        Integer result2 = instance.create(f2);
        instance.update(f1);


        Formation updatedf1 = instance.read(result);

        assertTrue(updatedf1.containsChild(f2));


    }

    /**
     * Test of find method, of class GroupDao.
     */
    //TODO @Test
//    public void testFind() {
//        System.out.println("find");
//        HashMap<String, String> querySet = null;
//        GroupDao instance = new GroupDao();
//        List expResult = null;
//        List result = instance.find(querySet);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of get method, of class GroupDao.
     */
    //TODO @Test
//    public void testGet() {
//        System.out.println("get");
//        HashMap<String, String> querySet = null;
//        GroupDao instance = new GroupDao();
//        Group expResult = null;
//        Group result = instance.get(querySet);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of all method, of class GroupDao.
     */
    //TODO @Test
//    public void testAll() {
//        System.out.println("all");
//        GroupDao instance = new GroupDao();
//        List expResult = null;
//        List result = instance.all();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}