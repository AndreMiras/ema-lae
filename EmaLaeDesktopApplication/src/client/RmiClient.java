/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import server.core.IController;

/**
 *
 * @author andre
 */
public class RmiClient
{

    public static void main(String args[])
    {
        try
        {
            IController controller =
                    (IController) Naming.lookup("rmi://localhost:1099/controller");
            System.out.println("Le client est connecte au serveur RMI");
            controller.login("username1", "password");
        } catch (NotBoundException e)
        {
            System.err.println(e.getMessage());
        } catch (MalformedURLException e)
        {
            System.err.println(e.getMessage());
        } catch (RemoteException e)
        {
            System.err.println(e.getMessage());
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
