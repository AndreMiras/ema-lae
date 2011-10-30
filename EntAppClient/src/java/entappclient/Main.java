/*
 * TODO:
 * VM Options: -Djava.security.manager -Djava.security.policy=/home/andre/Progz/ema-lae/EntAppClient/src/client.policy
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
        System.setSecurityManager(new java.rmi.RMISecurityManager());

        System.out.println("foo");
        System.out.println("result1 = " + mySession.getResult());
        System.out.println("result1 = " + mySession.getResult());
        userManager.addUser("foo", "bar");
        System.out.println("result2 = " + userManager.getResultTest());
        userManager.getAllUsersTest();
        // System.out.println("Users = " + users);

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
