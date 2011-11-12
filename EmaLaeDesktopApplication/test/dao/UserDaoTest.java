/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.User;
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
        User newUser = new User("username3");
        UserDao instance = new UserDao();
        Integer newUserID = instance.create(newUser);

        // re-read the saved newUser from the database
        newUser = instance.read(newUserID);
        // update it locally
        newUser.setUsername("updatedUsername3");
        // re-hit the database
        instance.update(newUser);
        // re-read the updated newUser from the database
        newUser = instance.read(newUserID);
        assertEquals("updatedUsername3", newUser.getUsername());
    }

    /**
     * Test of delete method, of class UserDao.
     */
    @Test
    public void testDelete()
    {
        System.out.println("delete");
        UserDao userDao = new UserDao();
        List<User> allUsersBefore = userDao.all();
        User userToDelete = allUsersBefore.get(0);

        /*
         * it should be possible to read that user from the database before
         * it's been deleted
         */
        assertNotNull(userDao.read(userToDelete.getUserId()));
        // deleting the first record
        userDao.delete(userToDelete);
        List<User> allUsersAfter = userDao.all();
        // not record should now be found
        assertNull(userDao.read(userToDelete.getUserId()));
        // only one record was deleted
        assertTrue(allUsersBefore.size() -1 == allUsersAfter.size());
    }

    /**
     * Test of find method, of class UserDao.
     */
    @Test
    public void testFind()
    {
        System.out.println("find");
        User userToFind = new User("userToFind");
        UserDao userDao = new UserDao();
        Integer newUserID = userDao.create(userToFind);

        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", "userToFind");
        List<User> result = userDao.find(querySet);
        User firstUserFound = result.get(0);
        assertEquals("userToFind", firstUserFound.getUsername());
        assertEquals(newUserID, firstUserFound.getUserId());
    }

    /**
     * Test of get method, of class UserDao.
     */
    @Test
    public void testGet()
    {
        System.out.println("get");
        User userToFind = new User("userToGet");
        UserDao userDao = new UserDao();
        Integer newUserID = userDao.create(userToFind);

        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", "userToGet");
        User result = userDao.get(querySet);
        assertEquals("userToGet", result.getUsername());
        assertEquals(newUserID, result.getUserId());
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