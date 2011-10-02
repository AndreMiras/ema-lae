/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.User;
import java.util.Hashtable;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andre
 */
public class UserDaoTest {

    public UserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class UserDao.
     */
    @Test
    public void testCreate()
    {
        System.out.println("create");
        User obj = new User("username1");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(obj.getUserId());

        UserDao instance = new UserDao();
        Integer result = instance.create(obj);
        // the object should now have an id given from the DAO
        assertNotNull(result);
    }

    /**
     * Test of read method, of class UserDao.
     */
    @Test
    public void testRead()
    {
        System.out.println("read");
        User newUser = new User("username2");

        UserDao instance = new UserDao();
        Integer newUserID = instance.create(newUser);
        User result = instance.read(newUserID);
        assertEquals("username2", result.getUsername());
    }

    /**
     * Test of update method, of class UserDao.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        User obj = null;
        UserDao instance = new UserDao();
        instance.update(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserDao.
     */
    @Test
    public void testDelete()
    {
        System.out.println("delete");
        User obj = null;
        UserDao instance = new UserDao();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class UserDao.
     */
    @Test
    public void testFind()
    {
        System.out.println("find");
        Hashtable<String, String> querySet = null;
        UserDao instance = new UserDao();
        List expResult = null;
        List result = instance.find(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of all method, of class UserDao.
     */
    @Test
    public void testAll()
    {
        System.out.println("all");
        UserDao instance = new UserDao();
        List result = instance.all();
        assertTrue(result.size() > 0);
    }

}