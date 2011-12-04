/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.core;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class ControllerServiceFactoryImpl extends UnicastRemoteObject
        implements IControllerServiceFactory
{

    // private static final long serialVersionUID = 6625883990856972736L;

    protected ControllerServiceFactoryImpl() throws RemoteException
    {
        super();
        System.setProperty("java.rmi.server.codebase",
                IControllerServiceFactory.class.getProtectionDomain().getCodeSource().getLocation().toString());

        System.setProperty("java.security.policy", "/java.policy");

        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }

        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost");
            registry.rebind("EchoService", this);
            System.out.println("Echo service factory registered.");
        } catch (Exception e)
        {
            System.err.println("Error registering echo service factory: "
                    + e.getMessage());
        }
    }

    @Override
    public IControllerService createControllerService() throws RemoteException
    {
        IControllerService controllerService = null;
        try
        {
            controllerService = new ControllerServiceImpl();
        } catch (IOException ex)
        {
            Logger.getLogger(ControllerServiceFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return controllerService;
    }

    public static void main(String[] args)
    {
        try
        {
            new ControllerServiceFactoryImpl();
        } catch (RemoteException e)
        {
            System.err.println("Error creating echo service factory: "
                    + e.getMessage());
        }
    }
}
