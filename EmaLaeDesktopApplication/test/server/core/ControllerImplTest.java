/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import database.util.InitDatabase;
import dao.UserProfileDao;
import dao.UserDao;
import database.util.HibernateUtil;
import database.entity.Users;
import database.entity.UserProfile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import org.hibernate.HibernateException;

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
        HibernateUtil.getSessionFactoryForTests();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() {
        HibernateUtil.getSessionFactoryForTests();

        InitDatabase initDatabase = new InitDatabase();
        initDatabase.dropUsers();
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
        ControllerImpl controllerImpl = new ControllerImpl();
        UserProfileDao userProfileDao = new UserProfileDao();
        UserDao userDao = new UserDao();
        InitDatabase initDatabase = new InitDatabase();

        
        List<Users> myUsers = userDao.all();
        // The tables should be totally void at this point
        //initDatabase.dropUsers();
        assertTrue(userProfileDao.all().isEmpty());
        assertTrue(userDao.all().isEmpty());
        
        // This should create a set of users
        controllerImpl.initDatabaseForTests();
        assertTrue(userProfileDao.all().size() > 0);
    }

    /**
     * Test of login method, of class ControllerImpl.
     */
    @Test
    public void testLogin() throws Exception
    {
        System.out.println("login");
        Users user;
        ControllerImpl controllerImpl = new ControllerImpl();
        Boolean loginResult;
        String correctUsername = "username1";
        String correctPassword = "pwd1";
        String wrongUsername = "wrongUsername";
        String wrongPassword = "wrongPassword";
        controllerImpl.initDatabaseForTests();

        // Before we login the user object should be Null
        user = controllerImpl.getUser();
        assertNull(user);

        // After we login with a wrong password
        // the user object should still be Null
        loginResult = controllerImpl.login(correctUsername, wrongPassword);
        user = controllerImpl.getUser();
        assertFalse(loginResult);
        assertNull(user);

        // Same thing with wrong username
        loginResult = controllerImpl.login(wrongUsername, correctPassword);
        user = controllerImpl.getUser();
        assertFalse(loginResult);
        assertNull(user);

        // After we login with correct credential
        // the user object should NOT be Null
        loginResult = controllerImpl.login(correctUsername, correctPassword);
        user = controllerImpl.getUser();
        assertTrue(loginResult);
        assertNotNull(user);
    }

    /**
     * Test of getUser method, of class ControllerImpl.
     */
    @Test
    public void testGetUser() throws Exception
    {
        System.out.println("getUser");
        Users user;
        ControllerImpl controllerImpl = new ControllerImpl();
        String username = "username1";
        String password = "pwd1";
        controllerImpl.initDatabaseForTests();

        // Before we login the user object should be Null
        user = controllerImpl.getUser();
        assertNull(user);

        // After we login the user object should NOT be Null
        controllerImpl.login(username, password);
        user = controllerImpl.getUser();
        assertNotNull(user);
    }

    /**
     * Test of getUserProfile method, of class ControllerImpl.
     */
    @Test
    public void testGetUserProfile() throws Exception
    {
        System.out.println("getUserProfile");
        UserProfile userProfile;
        ControllerImpl controllerImpl = new ControllerImpl();
        String username = "username1";
        String password = "pwd1";
        controllerImpl.initDatabaseForTests();

        // Before we login the userProfile object should be Null
        userProfile = controllerImpl.getUserProfile();
        assertNull(userProfile);

        // After we login the userProfile object should NOT be Null
        controllerImpl.login(username, password);
        userProfile = controllerImpl.getUserProfile();
        assertNotNull(userProfile);
    }

}