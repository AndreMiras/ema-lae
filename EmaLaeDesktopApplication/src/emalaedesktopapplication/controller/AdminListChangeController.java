/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import database.entity.User;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.admin.AdminEditUserPanel;
import emalaedesktopapplication.forms.admin.AdminListChangePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author andre
 */
public class AdminListChangeController {

    private EmaLaeDesktopView mainWindow;
    private AdminListChangePanel view;

    public AdminListChangeController(EmaLaeDesktopView mainWindow, AdminListChangePanel view)
    {
        this.mainWindow = mainWindow;
        this.view = view;

        view.addEditButtonListener(new EditListener());
    }


    class EditListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            User user = new User("testusername");
            user.setUserId(1);
            user.setPassword("password foo");
            AdminEditUserPanel adminEditUserPanel =
                    new AdminEditUserPanel(user);
            mainWindow.setMiddleContentPanel(adminEditUserPanel);
        }
    }

}
