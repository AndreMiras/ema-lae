/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaeserverapplication;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
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
        try
        {
            LocateRegistry.createRegistry(1099);
            IControllerServiceFactory controller =
                    new ControllerServiceFactoryImpl();
            Naming.rebind("rmi://localhost:1099/controllerServiceFactory",
                    controller);
        } catch (IOException e)
        {
            System.err.println(e);
        }
    }

}
