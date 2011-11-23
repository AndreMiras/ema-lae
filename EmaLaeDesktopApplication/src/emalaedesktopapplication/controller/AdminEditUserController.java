/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import client.RmiClient;
import database.entity.User;
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
public class AdminEditUserController {

    private EmaLaeDesktopView mainWindow;
    private AdminEditPanel<User> view;

    public AdminEditUserController(EmaLaeDesktopView mainWindow, AdminEditPanel<User> view)
    {
        this.mainWindow = mainWindow;
        this.view = view;

        view.addSaveButtonListener(new SaveListener());
    }

    class SaveListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            User editedUser = view.save();
            try
            {
                // hit the database back with the edited user
                RmiClient.getController().updateUser(editedUser);
            } catch (RemoteException ex)
            {
                Logger.getLogger(AdminEditUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
