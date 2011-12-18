/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.HashSet;
import database.entity.CourseSession.SessionType;
import database.entity.CourseSession;
import database.util.HibernateUtil;
import database.entity.Users;
import database.entity.UserProfile;
import database.util.InitDatabase;
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
 * @author andre
 */
public class UserProfileDaoTest {

    public UserProfileDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        // Forces the hibernate config for tests (will always drop/create schema)
        HibernateUtil.getSessionFactoryForTests();

        InitDatabase initDatabase = new InitDatabase();
        initDatabase.dropUsers();
        initDatabase.createProfiles();
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
     * Test of create method, of class UserProfileDao.
     */
    @Test
    public void testCreate() throws DaoException
    {
        System.out.println("create");
        Users user = new Users("username10");
        user.setPassword("pw1");
        UserProfile userProfile = new UserProfile(user);
        userProfile.setFirstName("foo");
        userProfile.setLastName("bar");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(userProfile.getUserId());
       
        UserProfileDao userProfileDao = new UserProfileDao();
        Integer result = userProfileDao.create(userProfile);
        // the object should now have an id given from the DAO
        assertNotNull(result);
        System.out.println(user.getUserId());

        //assertTrue(myUsers.contains(user));
    }

    /**
     * Test of read method, of class UserProfileDao.
     */
    @Test
    public void testRead()
    {
        System.out.println("read");
        Users user = new Users("username20");
        UserProfile userProfile = new UserProfile(user);
        userProfile.setFirstName("foo");
        userProfile.setLastName("bar");

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(userProfile.getUserId());

        UserProfileDao instance = new UserProfileDao();
        try{
            Integer newUserID = instance.create(userProfile);
            UserProfile result = instance.read(newUserID);

            assertEquals("foo", result.getFirstName());
            assertEquals("bar", result.getLastName());
            assertEquals("username20", result.getUser().getUsername());
        }
        catch(DaoException e){
            e.printStackTrace();
        }
    }

    /**
     * Test of update method, of class UserProfileDao.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Users user = new Users("username3");
        UserProfileDao instance = new UserProfileDao();
        UserProfile userProfile = new UserProfile(user);
        userProfile.setFirstName("firstname1");
        userProfile.setLastName("lastname1");
        try{
            Integer userProfileId = instance.create(userProfile);
            // re-read the saved profile from the database
            userProfile = instance.read(userProfileId);

            // update it locally
            userProfile.setFirstName("newFirstname1");
            userProfile.setLastName("newLastname1");
            // re-hit the database
            instance.update(userProfile);
            // re-read the updated profile from the database
            userProfile = instance.read(userProfileId);
            assertEquals("newFirstname1", userProfile.getFirstName());
            assertEquals("newLastname1", userProfile.getLastName());
        }
        catch(DaoException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Test of delete method, of class UserProfileDao.
     */
    @Test
    public void testDelete()
    {
        System.out.println("delete");
        UserProfileDao userProfileDao = new UserProfileDao();
        List<UserProfile> allUserProfilesBefore = userProfileDao.all();
        UserProfile userProfileToDelete = allUserProfilesBefore.get(0);

        /*
         * it should be possible to read that userprofile from the database before
         * it's been deleted
         */

        /*
         * it should be possible to read that userprofile from the database before
         * it's been deleted
         */
        assertNotNull(userProfileDao.read(userProfileToDelete.getUserId()));
        // deleting the first record
        userProfileDao.delete(userProfileToDelete);
        List<UserProfile> allUserProfilesAfter = userProfileDao.all();
        // not record should now be found
        assertNull(userProfileDao.read(userProfileToDelete.getUserId()));
        // only one record was deleted
        assertTrue(allUserProfilesBefore.size() -1 == allUserProfilesAfter.size());
    }

    /**
     * TODO
     */
    // @Test
    public void testFind()
    {
        System.out.println("find");
        HashMap<String, String> querySet = null;
        UserProfileDao instance = new UserProfileDao();
        List expResult = null;
        List result = instance.find(querySet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddSession() {
        try
        {
            System.out.println("add session");
            UserProfileDao userProfileDao = new UserProfileDao();
            // Instantiate the objects
            Users u = new Users("User4addSession");
            UserProfile user = new UserProfile(u);
            CourseSession cs1 = new CourseSession(SessionType.Course);
            // No parent and not children by default
            assertTrue(user.getCourseSessions().isEmpty());
            assertTrue(cs1.getFormation() == null);
            // Creating parent/children relation
            cs1.setTeacher(user);
            assertTrue(user.getCourseSessions().size() == 1);
            assertTrue(cs1.getTeacher() == user);
            // Insertion of the parent formation into the database
            Integer formationPk = userProfileDao.create(user);
            // Check that the child formation does appear in the parent's children
            assertTrue(user.ownsSession(cs1));
            // Check it's still true after a read from database
            user = userProfileDao.read(formationPk);
            assertTrue(user.getCourseSessions().size() == 1);
            assertTrue(user.ownsSession(cs1));
        }
        catch (DaoException ex)
        {
            Logger.getLogger(UserProfileDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test // TODO: finish this up
    public void testSetSessions() {
        try
        {
            System.out.println("create");
            UserProfileDao userProfileDao = new UserProfileDao();
            // Instantiate the objects
            Users u = new Users("User4SetSessions");
            UserProfile user = new UserProfile(u);
            CourseSession cs1 = new CourseSession(SessionType.Course);
            CourseSession cs2 = new CourseSession(SessionType.Pratictal);
            CourseSession cs3 = new CourseSession(SessionType.Test);
            // Nothing should be set by default
            assertTrue(user.getCourseSessions().isEmpty());
            assertTrue(cs1.getFormation() == null);
            // Creating relation
            HashSet<CourseSession> sessionsSet = new HashSet<CourseSession>();
            sessionsSet.add(cs1);
            sessionsSet.add(cs2);
            sessionsSet.add(cs3);
            user.setCourseSessions(sessionsSet);
            assertTrue(user.getCourseSessions().size() == 3);
            assertTrue(cs1.getTeacher() == user);
            assertTrue(cs2.getTeacher() == user);
            assertTrue(cs3.getTeacher() == user);
            // Check that the child formation does appear in the parent's children
            assertTrue(user.getCourseSessions().contains(cs1));
            assertTrue(user.getCourseSessions().contains(cs2));
            assertTrue(user.getCourseSessions().contains(cs3));
            // Insertion of the parent formation into the database
            Integer userPk = userProfileDao.create(user);
            // Check it's still true after a read from database
            user = userProfileDao.read(userPk);
            assertTrue(user.getCourseSessions().size() == 3);
            assertTrue(user.ownsSession(cs1));
            assertTrue(user.ownsSession(cs2));
            assertTrue(user.ownsSession(cs3));
            /*
             * Now lets try to set a formation HashSet a second time
             * to see if the previous one gets overridden.
             * So we set cf1 and cf3 but not cf2
             */
            sessionsSet.clear();
            sessionsSet.add(cs1);
            sessionsSet.add(cs3);
            user.setCourseSessions(sessionsSet);
            assertTrue(user.getCourseSessions().size() == 2);
            assertTrue(cs1.getTeacher() == user);
            // assertTrue(cf2.getParentFormation() == null);
            assertTrue(cs3.getTeacher() == user);
            userProfileDao.update(user); // updating the database
            // Check it's still true after a read from database
            user = userProfileDao.read(userPk);
            assertTrue(user.getCourseSessions().size() == 2);
            assertTrue(user.ownsSession(cs1));
            assertFalse(user.ownsSession(cs2));
            assertTrue(user.ownsSession(cs3));
        } catch (DaoException ex)
        {
            Logger.getLogger(UserProfileDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * TODO
     */
    // @Test
    public void testGet_Hashtable()
    {
        System.out.println("get");
        HashMap<String, String> querySet = null;
        UserProfileDao instance = new UserProfileDao();
        UserProfile expResult = null;
        UserProfile result = null;
        try
        {
            result = instance.get(querySet);
        } catch (DaoException ex)
        {
            Logger.getLogger(UserProfileDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * TODO
     */
    // @Test
    public void testGet_User()
    {
        System.out.println("get");
        UserDao userDao = new UserDao();

        // getting a previously created user
        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", "username3");
        Users user = null;
        try
        {
            user = userDao.get(querySet);
        } catch (DaoException ex)
        {
            Logger.getLogger(UserProfileDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(user);

        UserProfileDao instance = new UserProfileDao();
        UserProfile result = null;
        try
        {
            result = instance.get(user);
        } catch (DaoException ex)
        {
            Logger.getLogger(UserProfileDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(result);
    }

    /**
     * TODO
     */
    // @Test
    public void testAll()
    {
        System.out.println("all");
        UserProfileDao instance = new UserProfileDao();
        List expResult = null;
        List result = instance.all();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test(expected=DaoException.class)
    public void testCascadeUser() throws DaoException{
        System.out.println("testCascadeUser");
        UserProfileDao instance = new UserProfileDao();
        UserProfile p1 = new UserProfile();
        instance.create(p1);
    }

}