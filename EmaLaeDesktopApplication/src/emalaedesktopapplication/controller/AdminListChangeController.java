/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import client.RmiClient;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.admin.AdminEditPanel;
import emalaedesktopapplication.forms.admin.AdminListChangePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class AdminListChangeController<T> {

    private EmaLaeDesktopView mainWindow;
    private AdminListChangePanel view;
    private Class<T> type;

    public AdminListChangeController(
            EmaLaeDesktopView mainWindow,
            AdminListChangePanel view,
            Class<T> type)
    {
        this.mainWindow = mainWindow;
        this.view = view;
        this.type = type;

        view.addButtonsListener(new ButtonsListener());
    }


    class ButtonsListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();

            if (actionCommand.equals("Edit"))
            {
                T obj = (T) view.getSelectedItem();

                AdminEditPanel<T> adminEditPanel =
                        new AdminEditPanel<T>(type, obj);
                mainWindow.setMiddleContentPanel(adminEditPanel);
                AdminEditController adminEditController =
                        new AdminEditController(
                        mainWindow, adminEditPanel, type);
            }
            else if (actionCommand.equals("New"))
            {
                try
                {
                    AdminEditPanel<T> adminEditPanel = new AdminEditPanel<T>(type); // TODO: check this cast up
                    mainWindow.setMiddleContentPanel(adminEditPanel);
                    AdminEditController adminEditUserController = new AdminEditController(mainWindow, adminEditPanel, type);
                } catch (InstantiationException ex)
                {
                    Logger.getLogger(AdminListChangeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex)
                {
                    Logger.getLogger(AdminListChangeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (actionCommand.equals("Delete"))
            {
                T[] objs = null;
                T obj = (T) view.getSelectedItem();
                try
                {
                    RmiClient.getController().delete(type, obj);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(AdminListChangeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try
                {
                    objs = (T[]) RmiClient.getController().getAllObjects(type);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(AdminListChangeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                view.updateObjectList(objs);
            }
        }
    }

}
