/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.util.HibernateUtil;
import database.entity.UserGroup;
import database.entity.Users;
import database.entity.Permission;
import exceptions.DaoException;
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

/**
 *
 * @author pc
 */
public class GroupDaoTest {

    public GroupDaoTest() {
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

    /**
     * Test of create method, of class GroupDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        UserGroup obj = new UserGroup("group4Create");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(obj.getGroupId());

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
        UserGroup testGroup = new UserGroup("group4Read");

        GroupDao instance = new GroupDao();
        Integer groupID = instance.create(testGroup);
        UserGroup result = instance.read(groupID);
        assertEquals("group4Read", result.getName());
    }

    /**
     * Test of update method, of class GroupDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        UserGroup newGroup = new UserGroup("group4Update");
        GroupDao instance = new GroupDao();
        Integer newGroupID = instance.create(newGroup);

        // re-read the saved newUser from the database
        newGroup = instance.read(newGroupID);
        // update it locally
        newGroup.setName("group4UpdateNew");
        // re-hit the database
        instance.update(newGroup);
        // re-read the updated newUser from the database
        newGroup = instance.read(newGroupID);
        assertEquals("group4UpdateNew", newGroup.getName());
    }

    /**
     * Test of delete method, of class GroupDao.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        GroupDao instance = new GroupDao();
        List<UserGroup> allGroupsBefore = instance.all();
        UserGroup groupToDelete = allGroupsBefore.get(0);

        /*
         * it should be possible to read that userprofile from the database before
         * it's been deleted
         */
        assertNotNull(instance.read(groupToDelete.getGroupId()));
        // deleting the first record
        instance.delete(groupToDelete);
        List<UserGroup> allGroupsAfter = instance.all();
        // not record should now be found
        assertNull(instance.read(groupToDelete.getGroupId()));
        // only one record was deleted
        assertTrue(allGroupsBefore.size() -1 == allGroupsAfter.size());
    }

    /**
     * Test of find method, of class GroupDao.
     */
    //TODO @Test
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
    //TODO @Test
    public void testGet() {
        System.out.println("get");
        HashMap<String, String> querySet = null;
        GroupDao instance = new GroupDao();
        UserGroup expResult = null;
        UserGroup result = null;
        try
        {
            result = instance.get(querySet);
        } catch (DaoException ex)
        {
            Logger.getLogger(GroupDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of all method, of class GroupDao.
     */
    //TODO @Test
    public void testAll() {
        System.out.println("all");
        GroupDao instance = new GroupDao();
        List expResult = null;
        List result = instance.all();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        GroupDao groupInstance = new GroupDao();
        UserDao userInstance = new UserDao();

        UserGroup g1 = new UserGroup("group4addUser");
        Users u1 = new Users("user4addUserToGroup");

        userInstance.create(u1);

        Integer groupID = groupInstance.create(g1);

        g1.addUser(u1);
        groupInstance.update(g1);

        g1 = groupInstance.read(groupID);

        assertTrue(g1.containsUser(u1));

    }

    @Test
    public void testAddPermission() {
        System.out.println("addPermission");
        GroupDao groupInstance = new GroupDao();
        PermissionsDao permissionInstance = new PermissionsDao();

        UserGroup g1 = new UserGroup("group4addUser");
        Permission p1 = new Permission("permission4addPermissionToGroup");

        permissionInstance.create(p1);

        Integer groupID = groupInstance.create(g1);

        g1.addPermission(p1);
        groupInstance.update(g1);

        g1 = groupInstance.read(groupID);

        assertTrue(g1.containsPermission(p1));

    }
}