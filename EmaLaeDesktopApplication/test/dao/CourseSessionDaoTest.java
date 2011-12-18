/*
This file is part of ema-lae.

Ema-Lae is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Ema-Lae is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Jpcsp.  If not, see <http://www.gnu.org/licenses/>.
 */

package dao;

//import database.entity.Formation;
import database.entity.CourseSession;
import database.entity.CourseSession.SessionType;
import database.entity.Formation;
import database.util.HibernateUtil;
import database.util.InitDatabase;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class CourseSessionDaoTest {
    public CourseSessionDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        HibernateUtil.getSessionFactoryForTests();

        InitDatabase initDatabase = new InitDatabase();
        initDatabase.dropFormations();
        initDatabase.createFormations();
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

   @Test
    public void testCreate() {
        System.out.println("create");
        CourseSession obj = new CourseSession();

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(obj.getSessionId());

        CourseSessionDao courseSessionDao = new CourseSessionDao();
        Integer result = courseSessionDao.create(obj);

        // the object should now have an id given from the DAO
        assertNotNull(result);
    }

    /**
     * Test of read method, of class GroupDao.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        CourseSession courseSession = new CourseSession(SessionType.Course);

        CourseSessionDao instance = new CourseSessionDao();
        Integer courseId = instance.create(courseSession);
        CourseSession result = instance.read(courseId);
        assertEquals(SessionType.Course, result.getType());
    }

    /**
     * Test of update method, of class GroupDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        CourseSession newSession = new CourseSession(SessionType.Course);
        CourseSessionDao instance = new CourseSessionDao();
        Integer newSessionId = instance.create(newSession);

        // re-read the saved newUser from the database
        newSession = instance.read(newSessionId);
        // update it locally
        newSession.setSessionType(SessionType.Course);
        // re-hit the database
        instance.update(newSession);
        // re-read the updated newUser from the database
        newSession = instance.read(newSessionId);
        assertEquals(SessionType.Course, newSession.getType());
    }

    /**
     * Test of delete method, of class CourseSessionDao.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        CourseSessionDao instance = new CourseSessionDao();
        List<CourseSession> allSessionsBefore = instance.all();
        CourseSession sessionToDelete = allSessionsBefore.get(0);

        // asserts that the object still exists
        assertNotNull(instance.read(sessionToDelete.getSessionId()));
        // deletes the first record
        instance.delete(sessionToDelete);
        List<CourseSession> allSessionsAfter = instance.all();
        // not record should now be found
        assertNull(instance.read(sessionToDelete.getSessionId()));
        // only one record was deleted
        assertTrue(allSessionsBefore.size() - 1 ==
                allSessionsAfter.size());
    }

    @Test
    public void testSetFormation()
    {
        System.out.println("set Promotion");
        CourseSession session = new CourseSession(SessionType.Course);
        CourseSessionDao csInstance = new CourseSessionDao();
        Formation formation = new Formation("formation4SetFormation");

        Integer sessionId = csInstance.create(session);
        session = csInstance.read(sessionId);

        session.setFormation(formation);

        csInstance.update(session);
        session = csInstance.read(sessionId);

        assertTrue(session.getFormation().getFormationId().equals(formation.getFormationId()));
    }
}
