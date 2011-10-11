/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entappclient;

import ejb.MySessionRemote;
import javax.ejb.EJB;

/**
 *
 * @author amiras
 */
public class Main {
    @EJB
    private static MySessionRemote mySession;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("foo");
        System.err.println("result = " + mySession.getResult());
    }
    
    public void testObjectsPersitence()
    {
        // create some objects
        // http://java.boot.by/scbcd5-guide/ch06.html
    }

}
