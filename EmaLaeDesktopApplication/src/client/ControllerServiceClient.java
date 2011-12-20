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
                /*
                 * this is bugged when running the application on differents
                 * environements
                 */
                /*
                Properties rmiProp = Utils.readProperties(
                        "emalaedesktopapplication/utils/RMI.properties");
                IControllerServiceFactory controllerServiceFactory =
                        (IControllerServiceFactory) Naming.lookup(rmiProp.getProperty("protocol")+
                        "://"+rmiProp.getProperty("ipaddress")+
                        ":"+rmiProp.getProperty("port")+
                        "/"+rmiProp.getProperty("directory"));
                 */
                Integer port = 1099;
                String serverAddress = "localhost";
                IControllerServiceFactory controllerServiceFactory =
                        (IControllerServiceFactory) Naming.lookup(
                        "rmi://"
                        + serverAddress + ":" + port
                        + "/controllerServiceFactory");
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
