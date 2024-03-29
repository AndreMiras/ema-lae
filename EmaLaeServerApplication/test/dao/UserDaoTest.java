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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import database.entity.Permission;
import java.util.HashSet;
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
     * Verifies the setGroups method works correctly
     */
    @Test
    public void testSetGroups()
    {
        System.out.println("setGroups");
        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();
        HashSet<UserGroup> groupHashSet = new HashSet<UserGroup>();

        Users user = new Users("usernameSetGroups");
        UserGroup userGroup = new UserGroup("GroupSetGroups");

        Integer userPk = userDao.create(user);
        user = userDao.read(userPk);
        Integer groupPk = groupDao.create(userGroup);
        userGroup = groupDao.read(groupPk);
        groupHashSet.add(userGroup);

        /*
         * The group shouldn't be in the user groups set before it
         * was actually set manually
         */
        //assertFalse(user.containsGroup(userGroup));
        user.setGroups(groupHashSet);

        // now the group should be part of the user object
        assertTrue(user.containsGroup(userGroup));

        /*
         * the group should still be part of the user object after
         * reading from the database
         */
        userDao.update(user);
        user = userDao.read(userPk);
        assertTrue(user.containsGroup(userGroup));

        /*
         * Now lets try to set another group list
         * Normally the previous group list should be taken out
         * and be replaced by the new one
         */
        UserGroup userGroup2 = new UserGroup("AnotherGroup2");
        Integer group2Pk = groupDao.create(userGroup2);
        userGroup2 = groupDao.read(group2Pk);
        userGroup = groupDao.read(groupPk);
        groupHashSet.clear();
        groupHashSet.add(userGroup2);
        user.setGroups(groupHashSet);

        // we have set only one group
        assertTrue(user.getGroups().size() == 1);
        // hitting the database with the new groupSet
        userDao.update(user);
        user = userDao.read(userPk);
        userGroup2 = groupDao.read(group2Pk);
        userGroup = groupDao.read(groupPk);

        assertTrue(user.getGroups().size() == 1);
        assertFalse(userGroup.containsUser(user));
        assertTrue(userGroup2.containsUser(user));
        assertTrue(user.containsGroup(userGroup2));
        assertFalse(user.containsGroup(userGroup));
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
    public void testGet() throws DaoException
    {
        System.out.println("get");
        Users userToFind = new Users("userToGet");
        UserDao userDao = new UserDao();
        Integer newUserID = userDao.create(userToFind);

        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", "userToGet");
        Users result = null;
        result = userDao.get(querySet);
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
        Users newUser = new Users("user4addGroup");
        UserGroup newGroup1 = new UserGroup("Group4addGroup");

        // Insertion into the database
        Integer newUserId = instance.create(newUser);

        // Add a group to the user
        newUser.addToGroup(newGroup1);

        // Update the database
        instance.update(newUser);

        // Read the object from the database
        newUser = instance.read(newUserId);

        // Check that the user really owns the group
        assertTrue(newUser.containsGroup(newGroup1));

    }

    @Test
    public void checkPermission (){
        System.out.println("check permission");
        // Instantiate the objects
        Users user1 = new Users("user4checkPermission");
        Users user2 = new Users("user24checkPermission");
        Permission permission = new Permission("permission4checkPermission");
        UserGroup group = new UserGroup("Group4addGroup");
        UserDao userInstance = new UserDao();

        GroupDao groupInstance = new GroupDao();

        // Insert the empty users into the database
        Integer user1ID = userInstance.create(user1);
        Integer user2ID = userInstance.create(user2);

        // Check that the user does not have the permission yet
        assertFalse(user1.checkPermission(permission));

        // Adds the group to the user and the permission to the group
        user1.addToGroup(group);
        permission.addToGroup(group);

        // Update the users into the database
        userInstance.update(user1);
        userInstance.update(user2);

        // Check that user 1 now has the permission, but that user 2 still does not have it
        user1 = userInstance.read(user1ID);
        user2 = userInstance.read(user2ID);
        assertTrue(user1.checkPermission(permission));
        assertFalse(user2.checkPermission(permission));
    }

    // Checks the unique constraints on user name
    @Test(expected = HibernateException.class)
    public void testUniqueConstraintOnUsername(){
        UserDao instance = new UserDao();
        Users u1 = new Users("user4TestUnique");
        Users u2 = new Users("user4TestUnique");
        instance.create(u1);
        instance.create(u2);
    }

    @Test
    public void testAddSpecialPermission(){
        System.out.println("addSpecialPerm");
        // Creation of instances and objects
        UserDao instance = new UserDao();
        Users newUser = new Users("user4addPermission");
        Permission p1 = new Permission("Permissiion4addPermission");

        // Insertion into the database
        Integer newUserId = instance.create(newUser);

        // Add a permission to the user
        newUser.addSpecialPermission(p1);

        // Update the database
        instance.update(newUser);

        // Read the object from the database
        newUser = instance.read(newUserId);

        // Check that the user really owns the permission
        assertTrue(newUser.checkPermission(p1));
    }
}