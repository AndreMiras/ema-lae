/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.util.HibernateUtil;
import database.entity.UserProfile;
import database.entity.Users;
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
     * FIXME: update this test, wasn't the Apprentice, SupervisingTeacher and
     * InternshipSupervisor types supposed to be taken out?
     */
    @Test
    // can't create contract with userprofiles. First need to create the users.
    public void testCreate() {
        System.out.println("create");
        // Objects instanciation
        Users appu = new Users();
        Users mau = new Users();
        Users tutu = new Users();
        UserProfile apprenti = new UserProfile(appu, UserProfile.Type.Apprentice);
        UserProfile ma = new UserProfile(mau, UserProfile.Type.InternshipSupervisor);
        UserProfile tuteur = new UserProfile(tutu, UserProfile.Type.SupervisingTeacher);

        //Contract creation
        try{
            Contract obj = new Contract(apprenti, ma, tuteur);
            // the object shouldn't have an id, until it gets one from the DAO
            assertNull(obj.getId());
            ContractDao instance = new ContractDao();
            Integer result = instance.create(obj);

            // the object should now have an id given from the DAO
            assertNotNull(result);
        }
        catch (exceptions.ContractException e){
            fail("Failed to create contract");
        }
        


    }

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
        assertNotNull(contractDao.read(contractToDelete.getId()));
        // deleting the first record
        contractDao.delete(contractToDelete);
        List<Contract> allContractsAfter = contractDao.all();
        // not record should now be found
        assertNull(contractDao.read(contractToDelete.getId()));
        // only one record was deleted
        assertTrue(allContractsBefore.size() -1 == allContractsAfter.size());
    }

    @Test(expected=Error.class)
    public void getUsersShouldFail()
    {
        //It should not be possible to create a contract without the three users
        System.out.println("getContract");
        // Instantiate the objects
        ContractDao instance = new ContractDao();
        UserProfileDao uinstance = new UserProfileDao();
        Users u1 = new Users("user4getContract");
        UserProfile p1 = new UserProfile(u1, UserProfile.Type.Apprentice);
        Contract emptyContract = new Contract();
        Contract pcontract = new Contract();
        // Insert into the database
        Integer profileId = uinstance.create(p1);
        pcontract.addUserProfile(p1);
        Integer contractId = instance.create(pcontract);

        emptyContract = instance.read(contractId);
        p1 = uinstance.read(profileId);

        assertTrue(emptyContract.getApprentice().equals(p1));
        assertTrue(p1.getContract().equals(emptyContract));


    }

    @Test
    public void getUsers()
    {
        //It should not be possible to create a contract without the three users
        System.out.println("getContract");
        // Instantiate the objects
        ContractDao instance = new ContractDao();
        Users u1 = new Users("user4ContractGetUser");
        Users u2 = new Users("user24ContractGetUser");
        Users u3 = new Users("user34ContractGetUser");
        UserProfile p1 = new UserProfile(u1, UserProfile.Type.Apprentice);
        UserProfile p2 = new UserProfile(u2, UserProfile.Type.InternshipSupervisor);
        UserProfile p3 = new UserProfile(u3, UserProfile.Type.SupervisingTeacher);
        try{
            Contract emptyContract = new Contract();
            Contract pcontract = new Contract(p1,p2,p3);
//            // Insert into the database
//            pcontract.addUserProfile(p1);
//            pcontract.addUserProfile(p2);
//            pcontract.addUserProfile(p3);
            Integer contractId = instance.create(pcontract);

            emptyContract = instance.read(contractId);

            assertTrue(emptyContract.getApprentice().getUserId().equals(p1.getUserId()));
            assertTrue(p1.getContract().getId().equals(emptyContract.getId()));
            assertTrue(p2.getContract().getId().equals(emptyContract.getId()));
            assertTrue(p3.getContract().getId().equals(emptyContract.getId()));
        }
        catch(ContractException e){
            System.out.println("Bad contract");
        }

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