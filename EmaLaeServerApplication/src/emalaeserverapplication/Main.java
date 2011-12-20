/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaeserverapplication;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ControllerServiceFactoryImpl;
import server.core.IControllerServiceFactory;

/**
 * Runs the RMI Controller Factory
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        Integer port = 1099;
        try
        {
            LocateRegistry.createRegistry(port);
            IControllerServiceFactory controller =
                    new ControllerServiceFactoryImpl();
            Naming.rebind("rmi://localhost:"
                    + port
                    + "/controllerServiceFactory",
                    controller);
            String loggerMessage = "Server listening on port: " + port;
            Logger.getLogger(Main.class.getName()).log(
                    Level.INFO, loggerMessage);

        } catch (IOException e)
        {
            System.err.println(e);
        }
    }

}
