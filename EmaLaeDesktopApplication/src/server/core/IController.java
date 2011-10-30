/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;


/**
 *
 * @author andre
 */
public interface IController extends java.rmi.Remote {


    public void login(String username, String password)
            throws java.rmi.RemoteException, java.io.IOException;
}
