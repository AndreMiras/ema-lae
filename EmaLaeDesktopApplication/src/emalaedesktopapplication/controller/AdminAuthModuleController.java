/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import database.entity.User;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.AdminAuthModuleTabPanel;
import emalaedesktopapplication.forms.admin.AdminEditUserPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.metawidget.swing.SwingMetawidget;

/**
 *
 * @author andre
 */
public class AdminAuthModuleController {
    
    private EmaLaeDesktopView mainWindow;
    private AdminAuthModuleTabPanel view;

    public AdminAuthModuleController(EmaLaeDesktopView mainWindow, AdminAuthModuleTabPanel view)
    {
        this.mainWindow = mainWindow;
        this.view = view;

        // TODO: Add listeners to the view.
        view.addAddUsersButtonListener(new AddUsersListener());
    }

    class AddUsersListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            User user = new User("testusername");
            user.setUserId(1);
            user.setPassword("password foo");
            // PersonTest user = new PersonTest("my name");
            // user.setPassword("test");
            // user.age = 26;
            AdminEditUserPanel adminEditUserPanel =
                    new AdminEditUserPanel(user);
            mainWindow.setMiddleContentPanel(adminEditUserPanel);
        }
    }
}
