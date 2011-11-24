/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import client.RmiClient;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.admin.AdminEditPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class AdminEditController<T> {
    private EmaLaeDesktopView mainWindow;
    private AdminEditPanel<T> view;
    Class<T> type;

    public AdminEditController(
            EmaLaeDesktopView mainWindow, AdminEditPanel<T> view, Class<T> type)
    {
        this.mainWindow = mainWindow;
        this.view = view;
        this.type = type;

        view.addSaveButtonListener(new SaveListener());
    }

    class SaveListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            T editedObj = view.save();
            try
            {
                // hit the database back with the edited user
                RmiClient.getController().createOrUpdate(
                        type, editedObj);
            } catch (RemoteException ex)
            {
                Logger.getLogger(AdminEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
