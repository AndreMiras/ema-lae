/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
import database.entity.UserGroup;
import database.util.HibernateUtil;
import org.hibernate.HibernateException;

/**
 *
 * @author pc
 */
public class PermissionsDaoTest {

    public PermissionsDaoTest() {
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
     * Test of create method, of class PermissionsDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Permission obj = new Permission("Permission4Create");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(obj.getPermissionID());

        PermissionsDao instance = new PermissionsDao();
        Integer result = instance.create(obj);

        // the object should now have an id given from the DAO
        assertNotNull(result);
    }

    /**
     * Test of read method, of class PermissionsDao.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Permission permissionTest = new Permission("permission4read");

        PermissionsDao instance = new PermissionsDao();
        Integer permissionID = instance.create(permissionTest);
        Permission result = instance.read(permissionID);
        assertEquals("permission4read", result.getName());
    }

    /**
     * Test of update method, of class PermissionsDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Permission newPermission = new Permission("permission4update");
        PermissionsDao instance = new PermissionsDao();
        Integer newPermissionID = instance.create(newPermission);

        // re-read the saved newUser from the database
        newPermission = instance.read(newPermissionID);
        // update it locally
        newPermission.setName("permission4updateNew");
        // re-hit the database
        instance.update(newPermission);
        // re-read the updated newUser from the database
        newPermission = instance.read(newPermissionID);
        assertEquals("permission4updateNew", newPermission.getName());
    }

    /**
     * Test of delete method, of class PermissionsDao.
     */
    //@Test
    public void testDelete() {
        System.out.println("read");
        Permission newPermission = new Permission("permission4delete");
        PermissionsDao instance = new PermissionsDao();
        Integer permissionID = instance.create(newPermission);
        Permission result = instance.read(permissionID);
        assertEquals("permission4delete", result.getName());
    }

    /**
     * Test of find method, of class PermissionsDao.
     */
    //@Test
    public void testFind() {
        System.out.println("find");
        HashMap<String, String> querySet = null;
        PermissionsDao instance = new PermissionsDao();
        List expResult = null;
        List result = instance.find(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class PermissionsDao.
     */
    //@Test
    public void testGet() {
        System.out.println("get");
        HashMap<String, String> querySet = null;
        PermissionsDao instance = new PermissionsDao();
        Permission expResult = null;
        Permission result = null;
        try
        {
            result = instance.get(querySet);
        } catch (DaoException ex)
        {
            Logger.getLogger(PermissionsDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of all method, of class PermissionsDao.
     */
    //@Test
    public void testAll() {
        System.out.println("all");
        PermissionsDao instance = new PermissionsDao();
        List expResult = null;
        List result = instance.all();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Checks for "cascade = CascadeType.PERSIST"
     */
    // @Test(expected=HibernateException.class)
    public void testAddGroupShouldFail()
    {
        System.out.println("addGroup");
        PermissionsDao instance = new PermissionsDao();
        Permission p1 = new Permission("permission4addGroup");
        UserGroup newGroup1 = new UserGroup("Group4addGroup");
        instance.create(p1);
        
        p1.addToGroup(newGroup1);
        instance.update(p1);

    }

    /**
     * It should be possible to add a permission to a group
     */
    @Test
    public void testAddGroup()
    {
        System.out.println("addGroup");
        PermissionsDao permissionDao = new PermissionsDao();
        GroupDao groupInstance = new GroupDao();
        Permission p1 = new Permission("permission4addGroup");
        UserGroup newGroup1 = new UserGroup("Group4addGroup");
        p1.addToGroup(newGroup1);
        Integer permissionID = permissionDao.create(p1);
        
        permissionDao.update(p1);
        p1 = permissionDao.read(permissionID);
        assertTrue(p1.containsGroup(newGroup1));

        UserGroup newGroup2 = new UserGroup("group4addGroup2");
        p1.addToGroup(newGroup2);
        groupInstance.create(newGroup2);
        permissionDao.update(p1);
        p1 = permissionDao.read(permissionID);

        assertTrue(p1.containsGroup(newGroup1));
        assertTrue(p1.containsGroup(newGroup2));

    }
}