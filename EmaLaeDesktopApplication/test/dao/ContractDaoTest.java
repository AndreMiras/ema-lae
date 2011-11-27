/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Apprentice;
import database.entity.InternshipSupervisor;
import database.entity.SupervisingTeacher;
import database.entity.Contract;
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
    public void testCreate() {
        System.out.println("create");
        Apprentice apprenti = new Apprentice();
        InternshipSupervisor ma = new InternshipSupervisor();
        SupervisingTeacher tuteur = new SupervisingTeacher();
        Contract obj = new Contract(apprenti, ma, tuteur);

        // the object shouldn't have an id, until it gets one from the DAO
        assertNull(obj.getID());

        ContractDao instance = new ContractDao();
        Integer result = instance.create(obj);

        // the object should now have an id given from the DAO
        assertNotNull(result);
    }

    /**
     * Test of read method, of class GroupDao.
     * FIXME: update this test, wasn't the Apprentice, SupervisingTeacher and
     * InternshipSupervisor types supposed to be taken out?
     */
    // @Test
    public void testRead() {
        System.out.println("read");
        Apprentice apprenti = new Apprentice();
        InternshipSupervisor ma = new InternshipSupervisor();
        SupervisingTeacher tuteur = new SupervisingTeacher();
        Contract obj = new Contract(apprenti, ma, tuteur);

        ContractDao instance = new ContractDao();
        Integer contractID = instance.create(obj);
        Contract result = instance.read(contractID);
        assertTrue(result.getApprentice() == apprenti);
        assertTrue(result.getInternshipSupervisor() == ma);
        assertTrue(result.getSupervisingTeacher() == tuteur);
    }

    /**
     * Test of update method, of class GroupDao.
     * FIXME: update this test, wasn't the Apprentice, SupervisingTeacher and
     * InternshipSupervisor types supposed to be taken out?
     */
    // @Test
    public void testUpdate() {
        System.out.println("update");
        Apprentice apprenti = new Apprentice();
        InternshipSupervisor ma = new InternshipSupervisor();
        SupervisingTeacher tuteur = new SupervisingTeacher();
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