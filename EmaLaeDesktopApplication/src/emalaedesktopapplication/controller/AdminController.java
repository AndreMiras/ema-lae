/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.admin.AdminListChangePanel;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class AdminController<T> {

    private EmaLaeDesktopView mainWindow;
    private Class<T> type;

    public AdminController(
            EmaLaeDesktopView mainWindow,
            Class<T> type)
    {
        this.mainWindow = mainWindow;
        this.type = type;
    }

    public void adminListChange()
    {
        List<T> objs = null;
        AdminListChangePanel adminListChangePanel;
        AdminListChangeController adminListChangeController;

        try
        {
            objs = ControllerServiceClient.getController().getAllObjects(type);
        } catch (RemoteException ex)
        {
            Logger.getLogger(AdminController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        adminListChangePanel = new AdminListChangePanel(objs.toArray());
        adminListChangeController =
                new AdminListChangeController(
                mainWindow, adminListChangePanel, type);

        mainWindow.setMiddleContentPanel(adminListChangePanel);
    }

}
