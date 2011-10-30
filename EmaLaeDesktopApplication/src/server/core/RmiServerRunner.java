/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.core;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author andre
 */
public class RmiServerRunner
{

    public static void main(String args[])
    {
        try
        {

            LocateRegistry.createRegistry(1099);
            ControllerImpl controller = new ControllerImpl();
            Naming.rebind("rmi://localhost:1099/controller", controller);
        } catch (IOException e)
        {
            System.err.println(e);
        }
    }
}