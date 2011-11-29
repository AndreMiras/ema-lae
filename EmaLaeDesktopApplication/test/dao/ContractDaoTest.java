/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.UserProfile;
import database.entity.User;
import database.entity.Contract;
import exceptions.ContractException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author logar
 */
public class ContractDaoTest {

    public ContractDaoTest() {
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
     * Test of create method, of class GroupDao.
     * FIXME: update this test, wasn't the Apprentice, SupervisingTeacher and
     * InternshipSupervisor types supposed to be taken out?
     */
    @Test
    // can't create contract with userprofiles. First need to create the users.
    public void testCreate() {
        System.out.println("create");
        User appu = new User();
        User mau = new User();
        User tutu = new User();
        UserProfile apprenti = new UserProfile(appu, UserProfile.Type.Apprentice);
        UserProfile ma = new UserProfile(mau, UserProfile.Type.InternshipSupervisor);
        UserProfile tuteur = new UserProfile(tutu, UserProfile.Type.SupervisingTeacher);
        UserProfileDao u1 = new UserProfileDao();
        try{
            Contract obj = new Contract(apprenti, ma, tuteur);
            // the object shouldn't have an id, until it gets one from the DAO
            assertNull(obj.getID());
            u1.create(ma);
            u1.create(tuteur);
            u1.create(apprenti);
            ContractDao instance = new ContractDao();
            Integer result = instance.create(obj);

            // the object should now have an id given from the DAO
            assertNotNull(result);
        }
        catch (exceptions.ContractException e){
            fail("Failed to create contract");
        }
        


    }

    /**
     * Test of read method, of class GroupDao.
     * FIXME: update this test, wasn't the Apprentice, SupervisingTeacher and
     * InternshipSupervisor types supposed to be taken out?
     */
    // @Test
    public void testRead() {
        System.out.println("read");
        UserProfile apprenti = new UserProfile();
        UserProfile ma = new UserProfile();
        UserProfile tuteur = new UserProfile();
        try{
            Contract obj = new Contract(apprenti, ma, tuteur);

            ContractDao instance = new ContractDao();
            Integer contractID = instance.create(obj);
            Contract result = instance.read(contractID);
            assertTrue(result.getApprentice() == apprenti);
            assertTrue(result.getInternshipSupervisor() == ma);
            assertTrue(result.getSupervisingTeacher() == tuteur);
        }
        catch (exceptions.ContractException e){
            fail("Failed to create contract");
        }
    }

    /**
     * Test of update method, of class GroupDao.
     * FIXME: update this test, wasn't the Apprentice, SupervisingTeacher and
     * InternshipSupervisor types supposed to be taken out?
     */
    // @Test
    public void testUpdate() {
        System.out.println("update");
        UserProfile apprenti = new UserProfile();
        UserProfile ma = new UserProfile();
        UserProfile tuteur = new UserProfile();
        try {
            Contract newContract = new Contract(apprenti, ma, tuteur);
            ContractDao instance = new ContractDao();
            Integer contractID = instance.create(newContract);

            // re-read the saved newUser from the database
            newContract = instance.read(contractID);
            // update it locally
            newContract.setApprentice(apprenti);
            newContract.setInternshipSupervisor(ma);
            newContract.setSupervisingTeacher(tuteur);
            // re-hit the database
            instance.update(newContract);
            // re-read the updated newUser from the database
            newContract = instance.read(contractID);
            assertEquals(apprenti, newContract.getApprentice());
            assertEquals(tuteur, newContract.getSupervisingTeacher());
            assertEquals(ma, newContract.getInternshipSupervisor());
        }
        catch (exceptions.ContractException e){
            fail("Failed to create contract");
        }
    }

    /**
     * Test of delete method, of class GroupDao.
     * FIXME: update this test, wasn't the Apprentice, SupervisingTeacher and
     * InternshipSupervisor types supposed to be taken out?
     */
    // @Test
    public void testDelete() {
        System.out.println("delete");
        ContractDao contractDao = new ContractDao();
        List<Contract> allContractsBefore = contractDao.all();
        Contract contractToDelete = allContractsBefore.get(0);

        /*
         * it should be possible to read that user from the database before
         * it's been deleted
         */
        assertNotNull(contractDao.read(contractToDelete.getID()));
        // deleting the first record
        contractDao.delete(contractToDelete);
        List<Contract> allContractsAfter = contractDao.all();
        // not record should now be found
        assertNull(contractDao.read(contractToDelete.getID()));
        // only one record was deleted
        assertTrue(allContractsBefore.size() -1 == allContractsAfter.size());
    }

    /**
     * Test of find method, of class GroupDao.
     */
    //TODO @Test
//    public void testFind() {
//        System.out.println("find");
//        HashMap<String, String> querySet = null;
//        GroupDao instance = new GroupDao();
//        List expResult = null;
//        List result = instance.find(querySet);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of get method, of class GroupDao.
     */
    //TODO @Test
//    public void testGet() {
//        System.out.println("get");
//        HashMap<String, String> querySet = null;
//        GroupDao instance = new GroupDao();
//        Group expResult = null;
//        Group result = instance.get(querySet);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of all method, of class GroupDao.
     */
    //TODO @Test
//    public void testAll() {
//        System.out.println("all");
//        GroupDao instance = new GroupDao();
//        List expResult = null;
//        List result = instance.all();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}