/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.IControllerService;
import server.core.IControllerServiceFactory;
import java.util.Properties;

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
                Properties rmiProp = null;
                String rmiPropFilePath =
                        "emalaedesktopapplication.utils/RMI.properties";
                InputStream in = rmiPropFilePath.getClass().getResourceAsStream(
                        rmiPropFilePath);
                rmiProp.load(in);
                IControllerServiceFactory controllerServiceFactory =
                        (IControllerServiceFactory) Naming.lookup(rmiProp.getProperty("protocol")+
                        "://"+rmiProp.getProperty("ipaddress")+
                        ":"+rmiProp.getProperty("port")+
                        "/"+rmiProp.getProperty("directory"));
                controller = controllerServiceFactory.createControllerService();
            } catch (NotBoundException ex) {
                Logger.getLogger(ControllerServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControllerServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(ControllerServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(ControllerServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return controller;
    }
}
