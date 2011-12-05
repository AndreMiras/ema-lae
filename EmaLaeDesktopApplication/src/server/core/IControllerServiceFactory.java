/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is used to create a per client Controller Service object
 */
public interface IControllerServiceFactory extends Remote
{
    /**
     * @return the created ControllerService object
     * @throws RemoteException
     */
    IControllerService createControllerService() throws RemoteException;
}