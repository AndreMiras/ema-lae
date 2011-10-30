/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import java.awt.Point;

/**
 *
 * @author andre
 */
public interface IController extends java.rmi.Remote {


    public void login(int pid)
            throws java.rmi.RemoteException, java.io.IOException;
}
