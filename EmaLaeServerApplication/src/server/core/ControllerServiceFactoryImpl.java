/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.core;

import java.io.IOException;
import java.rmi.RemoteException;
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

    protected ControllerServiceFactoryImpl()
            throws java.rmi.RemoteException, java.io.IOException
    {
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
}
