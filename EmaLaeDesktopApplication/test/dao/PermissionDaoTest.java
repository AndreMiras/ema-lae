/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Permission;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class PermissionDaoTest {

    public PermissionDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class PermissionDao.
     */
    @Test
    public void testCreate()
    {
        System.out.println("create");
        Permission obj = null;
        PermissionDao instance = new PermissionDao();
        Integer expResult = null;
        Integer result = instance.create(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class PermissionDao.
     */
    @Test
    public void testRead()
    {
        System.out.println("read");
        Integer id = null;
        PermissionDao instance = new PermissionDao();
        Permission expResult = null;
        Permission result = instance.read(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PermissionDao.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Permission obj = null;
        PermissionDao instance = new PermissionDao();
        instance.update(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PermissionDao.
     */
    @Test
    public void testDelete()
    {
        System.out.println("delete");
        Permission obj = null;
        PermissionDao instance = new PermissionDao();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class PermissionDao.
     */
    @Test
    public void testFind()
    {
        System.out.println("find");
        HashMap<String, String> querySet = null;
        PermissionDao instance = new PermissionDao();
        List expResult = null;
        List result = instance.find(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class PermissionDao.
     */
    @Test
    public void testGet()
    {
        System.out.println("get");
        HashMap<String, String> querySet = null;
        PermissionDao instance = new PermissionDao();
        Permission expResult = null;
        Permission result = instance.get(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of all method, of class PermissionDao.
     */
    @Test
    public void testAll()
    {
        System.out.println("all");
        PermissionDao instance = new PermissionDao();
        List expResult = null;
        List result = instance.all();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}