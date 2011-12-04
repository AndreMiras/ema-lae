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
import server.core.IControllerServiceFactory;

/**
 *
 * @author andre
 */
public class ControllerServiceClient {

    private static IControllerService controller;

    public void RmiClient() {
    }

    public static synchronized IControllerService getController() {
        if (controller == null) {
            try {
                IControllerServiceFactory controllerServiceFactory =
                        (IControllerServiceFactory) Naming.lookup("rmi://localhost:1099/controllerServiceFactory");
                controller = controllerServiceFactory.createControllerService();
            } catch (NotBoundException ex) {
                Logger.getLogger(ControllerServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControllerServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(ControllerServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return controller;
    }
}
