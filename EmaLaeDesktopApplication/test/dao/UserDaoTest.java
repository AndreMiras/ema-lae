/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.UserGroup;
import database.util.HibernateUtil;
import exceptions.DaoException;
import database.entity.Users;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import database.entity.Permission;
import org.hibernate.HibernateException;

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
        // Forces the hibernate config for tests (will always drop/create schema)
        HibernateUtil.getSessionFactoryForTests();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        HibernateUtil.getSessionFactoryForTests().close();
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
        Users obj = new Users("username1");

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
        Users newUser = new Users("username2");

        UserDao instance = new UserDao();
        Integer newUserID = instance.create(newUser);
        Users result = instance.read(newUserID);
        assertEquals("username2", result.getUsername());
    }

    /**
     * Test of update method, of class UserDao.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Users newUser = new Users("username3");
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
        List<Users> allUsersBefore = userDao.all();
        Users userToDelete = allUsersBefore.get(0);

        /*
         * it should be possible to read that user from the database before
         * it's been deleted
         */
        assertNotNull(userDao.read(userToDelete.getUserId()));
        // deleting the first record
        userDao.delete(userToDelete);
        List<Users> allUsersAfter = userDao.all();
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
        Users userToFind = new Users("userToFind");
        UserDao userDao = new UserDao();
        Integer newUserID = userDao.create(userToFind);

        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", "userToFind");
        List<Users> result = userDao.find(querySet);
        Users firstUserFound = result.get(0);

        // username should be unique
        assertEquals(1, result.size());

        // verifies the correct username was found
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
        Users userToFind = new Users("userToGet");
        UserDao userDao = new UserDao();
        Integer newUserID = userDao.create(userToFind);

        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", "userToGet");
        Users result = null;
        try
        {
            result = userDao.get(querySet);
        } catch (DaoException ex)
        {
            Logger.getLogger(UserDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * Checks for "cascade = CascadeType.PERSIST"
     */
    // @Test(expected=HibernateException.class)
    public void testAddGroupShouldFail()
    {
        System.out.println("addGroup");
        UserDao instance = new UserDao();
        Users newUser = new Users("user4addGroup");
        UserGroup newGroup1 = new UserGroup("Group4addGroup");
        instance.create(newUser);
        
        newUser.addToGroup(newGroup1);
        instance.update(newUser);
    }

    @Test
    public void testAddGroup()
    {
        System.out.println("addGroup");
        // Creation of instances and objects
        UserDao instance = new UserDao();
        GroupDao groupInstance = new GroupDao();
        Users newUser = new Users("user4addGroup");
        UserGroup newGroup1 = new UserGroup("Group4addGroup");

        // Insertion into the database
        Integer newUserId = instance.create(newUser);
        groupInstance.create(newGroup1);

        // Add a group to the user
        newUser.addToGroup(newGroup1);

        // Update the database
        instance.update(newUser);

        // Read the object from the database
        newUser = instance.read(newUserId);

        // Check that the user really owns the group
        assertTrue(newUser.containsGroup(newGroup1));

    }

    public void checkPermission (){
        System.out.println("check permission");
        Users user1 = new Users("user4checkPermission");
        UserDao userInstance1 = new UserDao();
        Integer user1ID = userInstance1.create(user1);

        Users user2 = new Users("user24checkPermission");
        UserDao userInstance2 = new UserDao();
        Integer user2ID = userInstance2.create(user2);

        Permission permission = new Permission("permission4checkPermission");
        PermissionsDao permissionInstance = new PermissionsDao();
        Integer permissionID = permissionInstance.create(permission);
        
        UserGroup group = new UserGroup("Group4addGroup");
//        GroupDao groupInstance = new GroupDao();
//        Integer groupID = groupInstance.create(group);

        assertFalse(user1.checkPermission(permission));

        user1.addToGroup(group);
        permission.addToGroup(group);

        assertTrue(user1.checkPermission(permission));
        assertFalse(user2.checkPermission(permission));
    }

    @Test(expected = HibernateException.class)
    public void testUniqueConstraintOnUsername(){
        UserDao instance = new UserDao();
        Users u1 = new Users("user4TestUnique");
        Users u2 = new Users("user4TestUnique");
        instance.create(u1);
        instance.create(u2);
    }

}