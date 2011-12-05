/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.core;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Runs the RMI Controller Factory
 */
public class RmiServerRunner
{

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