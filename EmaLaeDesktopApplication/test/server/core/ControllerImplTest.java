/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import database.entity.User;
import database.entity.UserProfile;
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
public class ControllerImplTest {

    public ControllerImplTest() {
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
     * Test of initDatabaseForTests method, of class ControllerImpl.
     */
    @Test
    public void testInitDatabaseForTests() throws Exception
    {
        System.out.println("initDatabaseForTests");
        ControllerImpl instance = new ControllerImpl();
        instance.initDatabaseForTests();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class ControllerImpl.
     */
    @Test
    public void testLogin() throws Exception
    {
        System.out.println("login");
        String username = "";
        String password = "";
        ControllerImpl instance = new ControllerImpl();
        boolean expResult = false;
        boolean result = instance.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class ControllerImpl.
     */
    @Test
    public void testGetUser() throws Exception
    {
        System.out.println("getUser");
        ControllerImpl instance = new ControllerImpl();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserProfile method, of class ControllerImpl.
     */
    @Test
    public void testGetUserProfile() throws Exception
    {
        System.out.println("getUserProfile");
        ControllerImpl instance = new ControllerImpl();
        UserProfile expResult = null;
        UserProfile result = instance.getUserProfile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}