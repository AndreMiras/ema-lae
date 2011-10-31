/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.User;
import database.entity.UserProfile;
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
public class UserProfileDaoTest {

    public UserProfileDaoTest() {
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
     * Test of create method, of class UserProfileDao.
     */
    @Test
    public void testCreate()
    {
        System.out.println("create");
        User user = new User("username1");
        UserProfile userProfile = new UserProfile(user);
        userProfile.setFirstName("foo");
        userProfile.setLastName("bar");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(userProfile.getUserId());
        
        UserProfileDao userProfileDao = new UserProfileDao();
        Integer result = userProfileDao.create(userProfile);
        // the object should now have an id given from the DAO
        assertNotNull(result);
    }

    /**
     * Test of read method, of class UserProfileDao.
     */
    @Test
    public void testRead()
    {
        System.out.println("read");
        User user = new User("username2");
        UserProfile userProfile = new UserProfile(user);
        userProfile.setFirstName("foo");
        userProfile.setLastName("bar");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(userProfile.getUserId());

        UserProfileDao instance = new UserProfileDao();
        Integer newUserID = instance.create(userProfile);
        UserProfile result = instance.read(newUserID);

        assertEquals("foo", result.getFirstName());
        assertEquals("bar", result.getLastName());
        assertEquals("username2", result.getUser().getUsername());
    }

    /**
     * Test of update method, of class UserProfileDao.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        UserProfile obj = null;
        UserProfileDao instance = new UserProfileDao();
        instance.update(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserProfileDao.
     */
    @Test
    public void testDelete()
    {
        System.out.println("delete");
        UserProfile obj = null;
        UserProfileDao instance = new UserProfileDao();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class UserProfileDao.
     */
    @Test
    public void testFind()
    {
        System.out.println("find");
        Hashtable<String, String> querySet = null;
        UserProfileDao instance = new UserProfileDao();
        List expResult = null;
        List result = instance.find(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class UserProfileDao.
     */
    @Test
    public void testGet_Hashtable()
    {
        System.out.println("get");
        Hashtable<String, String> querySet = null;
        UserProfileDao instance = new UserProfileDao();
        UserProfile expResult = null;
        UserProfile result = instance.get(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class UserProfileDao.
     */
    @Test
    public void testGet_User()
    {
        System.out.println("get");
        User user = null;
        UserProfileDao instance = new UserProfileDao();
        UserProfile expResult = null;
        UserProfile result = instance.get(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of all method, of class UserProfileDao.
     */
    @Test
    public void testAll()
    {
        System.out.println("all");
        UserProfileDao instance = new UserProfileDao();
        List expResult = null;
        List result = instance.all();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}