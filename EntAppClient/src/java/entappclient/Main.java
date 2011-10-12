/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entappclient;

import controller.MainWindowController;
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
        // System.err.println("result = " + mySession.getResult());

        // Creating the main view
        MainWindowFrame view = new MainWindowFrame();
        view.setVisible(true);
        // Creating the controller
        MainWindowController controller = new MainWindowController(view);
    }
    
    public void testObjectsPersitence()
    {
        // create some objects
        // http://java.boot.by/scbcd5-guide/ch06.html
    }

}
