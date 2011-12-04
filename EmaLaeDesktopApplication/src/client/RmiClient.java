/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.IControllerService;

/**
 *
 * @author andre
 */
public class RmiClient {

    private static IControllerService controller;

    public void RmiClient() {
    }

    public static synchronized IControllerService getController() {
        if (controller == null) {
            try {
                controller = (IControllerService) Naming.lookup("rmi://localhost:1099/controller");
            } catch (NotBoundException ex) {
                Logger.getLogger(RmiClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(RmiClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(RmiClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return controller;
    }
}
