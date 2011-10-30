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

    public void login(String username, String password){
        System.out.println("Trying to login with:"
                + "username:"
                + username
                + "\n"
                + "password:"
                + password
                );
    }
}
