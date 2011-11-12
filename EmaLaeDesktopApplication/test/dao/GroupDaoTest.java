/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Group;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class GroupDaoTest {

    public GroupDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class GroupDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Group obj = new Group("username1");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(obj.getID());

        GroupDao instance = new GroupDao();
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
        Group testGroup2 = new Group("testGroup2");

        GroupDao instance = new GroupDao();
        Integer groupID = instance.create(testGroup2);
        Group result = instance.read(groupID);
        assertEquals("testGroup2", result.getName());
    }

    /**
     * Test of update method, of class GroupDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Group newGroup = new Group("testGroup3");
        GroupDao instance = new GroupDao();
        Integer newGroupID = instance.create(newGroup);

        // re-read the saved newUser from the database
        newGroup = instance.read(newGroupID);
        // update it locally
        newGroup.setName("newtestGroup3Name");
        // re-hit the database
        instance.update(newGroup);
        // re-read the updated newUser from the database
        newGroup = instance.read(newGroupID);
        assertEquals("newtestGroup3Name", newGroup.getName());
    }

    /**
     * Test of delete method, of class GroupDao.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Group obj = null;
        GroupDao instance = new GroupDao();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class GroupDao.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        HashMap<String, String> querySet = null;
        GroupDao instance = new GroupDao();
        List expResult = null;
        List result = instance.find(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class GroupDao.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        HashMap<String, String> querySet = null;
        GroupDao instance = new GroupDao();
        Group expResult = null;
        Group result = instance.get(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of all method, of class GroupDao.
     */
    @Test
    public void testAll() {
        System.out.println("all");
        GroupDao instance = new GroupDao();
        List expResult = null;
        List result = instance.all();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}