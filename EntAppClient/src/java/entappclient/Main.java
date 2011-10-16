/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entappclient;

import controller.MainWindowController;
import ejb.MySessionRemote;
import ejb.UserManagerSessionBeanRemote;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author amiras
 */
public class Main {
    @EJB
    private static MySessionRemote mySession;
    @EJB
    private static UserManagerSessionBeanRemote userManager;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("foo");
        System.out.println("result = " + mySession.getResult());
        userManager.addUser("foo", "bar");
        List users = userManager.getAllUsers();
        System.out.println("Users = " + users);

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
