/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Permission;
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
public class PermissionsDaoTest {

    public PermissionsDaoTest() {
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
    //@Test
    public void testUpdate() {
        System.out.println("update");
        Permission obj = null;
        PermissionsDao instance = new PermissionsDao();
        instance.update(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PermissionsDao.
     */
    //@Test
    public void testDelete() {
        System.out.println("delete");
        Permission obj = null;
        PermissionsDao instance = new PermissionsDao();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        Permission result = instance.get(querySet);
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

}