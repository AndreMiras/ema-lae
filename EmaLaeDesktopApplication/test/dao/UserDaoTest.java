/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Group;
import database.util.HibernateUtil;
import database.entity.User;
import java.util.HashMap;
import java.util.List;
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
    
    @Test(expected=HibernateException.class)
    public void testAddGroupShouldFail()
    {
        System.out.println("addGroup");
        UserDao instance = new UserDao();
        User newUser = new User("user4addGroup");
        Group newGroup1 = new Group("Group4addGroup");
        instance.create(newUser);
        
        newUser.addToGroup(newGroup1);
        instance.update(newUser);
    }

    @Test
    public void testAddGroup()
    {
        System.out.println("addGroup");
        UserDao instance = new UserDao();
        GroupDao groupInstance = new GroupDao();
        User newUser = new User("user4addGroup");
        Group newGroup1 = new Group("Group4addGroup");
        Integer newUserId = instance.create(newUser);

        List<Group> myGroups = groupInstance.all();
        
        groupInstance.create(newGroup1);
        newUser.addToGroup(newGroup1);
        instance.update(newUser);
        newUser = instance.read(newUserId);
        Group newGroup2 = new Group("group4addGroup2");
        
        assertTrue(newUser.containsGroup(newGroup1));

        newUser.addToGroup(newGroup2);
        groupInstance.create(newGroup2);
        instance.update(newUser);
        newUser = instance.read(newUserId);
        
        myGroups = groupInstance.all();
        
        assertTrue("Group4addGroup".equals(myGroups.get(0).getName())&&"group4addGroup2".equals(myGroups.get(1).getName()));
        
        assertTrue(newUser.getGroups().contains(newGroup1)&&newUser.getGroups().contains(newGroup2));

    }

    public void checkPermission (){
        System.out.println("check permission");
        User user1 = new User("user4checkPermission");
        UserDao userInstance1 = new UserDao();
        Integer user1ID = userInstance1.create(user1);

        User user2 = new User("user24checkPermission");
        UserDao userInstance2 = new UserDao();
        Integer user2ID = userInstance2.create(user2);

        Permission permission = new Permission("permission4checkPermission");
        PermissionsDao permissionInstance = new PermissionsDao();
        Integer permissionID = permissionInstance.create(permission);
        
        Group group = new Group("Group4addGroup");
//        GroupDao groupInstance = new GroupDao();
//        Integer groupID = groupInstance.create(group);

        assertFalse(user1.checkPermission(permission));

        user1.addToGroup(group);
        permission.addToGroup(group);

        assertTrue(user1.checkPermission(permission));
        assertFalse(user2.checkPermission(permission));
    }

}