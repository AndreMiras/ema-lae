/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.CourseSession;
import database.entity.CourseSession.SessionType;
import database.entity.Formation;
import database.entity.Promotion;
import database.util.HibernateUtil;
import database.util.InitDatabase;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

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

        InitDatabase initDatabase = new InitDatabase();
        initDatabase.dropFormations();
        initDatabase.createFormations();
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
        assertNull(obj.getFormationId());

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
        Integer nbToBeDeleted;
        FormationDao instance = new FormationDao();
        List<Formation> allFormationsBefore = instance.all();
        Formation formationToDelete = allFormationsBefore.get(0);

        // cascade deletion
        nbToBeDeleted =
                formationToDelete.getChildrenFormations().size() + 1;

        // asserts that the object still exists
        assertNotNull(instance.read(formationToDelete.getFormationId()));
        // deletes the first record
        instance.delete(formationToDelete);
        List<Formation> allFormationsAfter = instance.all();
        // not record should now be found
        assertNull(instance.read(formationToDelete.getFormationId()));
        // only one record was deleted
        assertTrue(allFormationsBefore.size() - nbToBeDeleted ==
                allFormationsAfter.size());
    }


    //@Test(expected = HibernateException.class)
    public void testAddFormationShouldFail() {
        System.out.println("create");
        Formation f1 = new Formation("parent formation");
        FormationDao instance = new FormationDao();
        Integer result = instance.create(f1);

        Formation f2 = new Formation("child formation");
        f2.setParentFormation(f1);
        assertFalse(f1.getChildrenFormations().contains(f2));

        f1.addFormation(f2);

        instance.update(f1);
    }
    
    @Test
    public void testAddFormation() {
        System.out.println("create");
        FormationDao formationDao = new FormationDao();
        // Instantiate the objects
        Formation f1 = new Formation("parent formation");
        Formation f2 = new Formation("child formation");

        // No parent and not children by default
        assertTrue(f1.getChildrenFormations().isEmpty());
        assertTrue(f2.getChildrenFormations().isEmpty());
        assertTrue(f1.getParentFormation() == null);
        assertTrue(f2.getParentFormation() == null);

        // Creating parent/children relation
        f2.setParentFormation(f1);
        assertTrue(f1.getChildrenFormations().size() == 1);
        assertTrue(f2.getChildrenFormations().isEmpty());
        assertTrue(f1.getParentFormation() == null);
        assertTrue(f2.getParentFormation() == f1);

        // Insertion of the parent formation into the database
        Integer parentPk = formationDao.create(f1);

        // Check that the child formation does appear in the parent's children
        assertTrue(f1.containsChild(f2));

        // Check it's still true after a read from database
        f1 = formationDao.read(parentPk);
        assertTrue(f1.getChildrenFormations().size() == 1);
        assertTrue(f1.containsChild(f2));
    }

    @Test // TODO: finish this up
    public void testSetChildrenFormations() {
        System.out.println("create");
        FormationDao formationDao = new FormationDao();
        // Instantiate the objects
        Formation pf1 = new Formation("ParentFormation1");
        Formation cf1 = new Formation("ChildFormation1");
        Formation cf2 = new Formation("ChildFormation2");
        Formation cf3 = new Formation("ChildFormation3");

        // No parent and not children by default
        assertTrue(pf1.getChildrenFormations().isEmpty());
        assertTrue(cf1.getChildrenFormations().isEmpty());
        assertTrue(pf1.getParentFormation() == null);
        assertTrue(cf1.getParentFormation() == null);

        // Creating parent/children relation
        HashSet<Formation> formationSet = new HashSet<Formation>();
        formationSet.add(cf1);
        formationSet.add(cf2);
        formationSet.add(cf3);
        pf1.setChildrenFormations(formationSet);
        assertTrue(pf1.getChildrenFormations().size() == 3);
        assertTrue(cf2.getChildrenFormations().isEmpty());
        assertTrue(cf1.getParentFormation() == pf1);
        assertTrue(cf2.getParentFormation() == pf1);
        assertTrue(cf3.getParentFormation() == pf1);

        // Check that the child formation does appear in the parent's children
        assertTrue(pf1.getChildrenFormations().contains(cf1));
        assertTrue(pf1.getChildrenFormations().contains(cf2));
        assertTrue(pf1.getChildrenFormations().contains(cf3));

        // Insertion of the parent formation into the database
        Integer parentPk = formationDao.create(pf1);


        // Check it's still true after a read from database
        pf1 = formationDao.read(parentPk);
        assertTrue(pf1.getChildrenFormations().size() == 3);
        assertTrue(pf1.containsChild(cf1));
        assertTrue(pf1.containsChild(cf2));
        assertTrue(pf1.containsChild(cf3));

        /*
         * Now lets try to set a formation HashSet a second time
         * to see if the previous one gets overridden.
         * So we set cf1 and cf3 but not cf2
         */
        formationSet.clear();
        formationSet.add(cf1);
        formationSet.add(cf3);

        pf1.setChildrenFormations(formationSet);
        assertTrue(pf1.getChildrenFormations().size() == 2);
        assertTrue(cf1.getParentFormation() == pf1);
        // assertTrue(cf2.getParentFormation() == null);
        assertTrue(cf3.getParentFormation() == pf1);

        formationDao.update(pf1); // updating the database
        // Check it's still true after a read from database
        pf1 = formationDao.read(parentPk);
        assertTrue(pf1.getChildrenFormations().size() == 2);
        assertTrue(pf1.containsChild(cf1));
        assertFalse(pf1.containsChild(cf2));
        assertTrue(pf1.containsChild(cf3));
    }

    @Test
    public void testSetPromotion()
    {
        System.out.println("set Promotion");
        Formation formation = new Formation("formation4setPromotion");
        FormationDao fInstance = new FormationDao();
        Promotion promotion = new Promotion("promotion4setPromotion");

        Integer formationID = fInstance.create(formation);
        formation = fInstance.read(formationID);

        formation.setPromotion(promotion);

        fInstance.update(formation);
        formation = fInstance.read(formationID);

        assertTrue(formation.getPromotion().getName().equals(promotion.getName()));
    }

    @Test
    public void testAddSession() {
        System.out.println("add session");
        FormationDao formationDao = new FormationDao();
        // Instantiate the objects
        Formation f1 = new Formation("parent formation");
        CourseSession cs1 = new CourseSession(SessionType.Course);

        // No parent and not children by default
        assertTrue(f1.getSessions().isEmpty());
        assertTrue(cs1.getFormation() == null);

        // Creating parent/children relation
        cs1.setFormation(f1);
        assertTrue(f1.getSessions().size() == 1);
        assertTrue(cs1.getFormation() == f1);

        // Insertion of the parent formation into the database
        Integer formationPk = formationDao.create(f1);

        // Check that the child formation does appear in the parent's children
        assertTrue(f1.containsSession(cs1));

        // Check it's still true after a read from database
        f1 = formationDao.read(formationPk);
        assertTrue(f1.getSessions().size() == 1);
        assertTrue(f1.containsSession(cs1));
    }

    @Test // TODO: finish this up
    public void testSetSessions() {
        System.out.println("create");
        FormationDao formationDao = new FormationDao();
        // Instantiate the objects
        Formation f1 = new Formation("ParentFormation1");
        CourseSession cs1 = new CourseSession(SessionType.Course);
        CourseSession cs2 = new CourseSession(SessionType.Pratictal);
        CourseSession cs3 = new CourseSession(SessionType.Test);

        // Nothing should be set by default
        assertTrue(f1.getSessions().isEmpty());
        assertTrue(cs1.getFormation() == null);

        // Creating relation
        HashSet<CourseSession> sessionsSet = new HashSet<CourseSession>();
        sessionsSet.add(cs1);
        sessionsSet.add(cs2);
        sessionsSet.add(cs3);
        f1.setSessions(sessionsSet);
        assertTrue(f1.getSessions().size() == 3);
        assertTrue(cs1.getFormation() == f1);
        assertTrue(cs2.getFormation() == f1);
        assertTrue(cs3.getFormation() == f1);

        // Check that the child formation does appear in the parent's children
        assertTrue(f1.getSessions().contains(cs1));
        assertTrue(f1.getSessions().contains(cs2));
        assertTrue(f1.getSessions().contains(cs3));

        // Insertion of the parent formation into the database
        Integer formationPk = formationDao.create(f1);


        // Check it's still true after a read from database
        f1 = formationDao.read(formationPk);
        assertTrue(f1.getSessions().size() == 3);
        assertTrue(f1.containsSession(cs1));
        assertTrue(f1.containsSession(cs2));
        assertTrue(f1.containsSession(cs3));

        /*
         * Now lets try to set a formation HashSet a second time
         * to see if the previous one gets overridden.
         * So we set cf1 and cf3 but not cf2
         */
        sessionsSet.clear();
        sessionsSet.add(cs1);
        sessionsSet.add(cs3);

        f1.setSessions(sessionsSet);
        assertTrue(f1.getSessions().size() == 2);
        assertTrue(cs1.getFormation() == f1);
        // assertTrue(cf2.getParentFormation() == null);
        assertTrue(cs3.getFormation() == f1);

        formationDao.update(f1); // updating the database
        // Check it's still true after a read from database
        f1 = formationDao.read(formationPk);
        assertTrue(f1.getSessions().size() == 2);
        assertTrue(f1.containsSession(cs1));
        assertFalse(f1.containsSession(cs2));
        assertTrue(f1.containsSession(cs3));
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