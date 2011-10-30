/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

/**
 *
 * @author laurent
 */
public class ControllerImpl extends java.rmi.server.UnicastRemoteObject
        implements IController
    {

    public ControllerImpl() throws java.rmi.RemoteException, java.io.IOException {
        System.out.println("Constructor");
    }

    public void login(int pid){
        System.out.println("TODO");
    }
}
