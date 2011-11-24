/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.RmiClient;
import database.entity.Group;
import database.entity.User;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.AdminAuthModuleTabPanel;
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
public class AdminAuthModuleController
{

    private EmaLaeDesktopView mainWindow;
    private AdminAuthModuleTabPanel view;

    public AdminAuthModuleController(EmaLaeDesktopView mainWindow, AdminAuthModuleTabPanel view)
    {
        this.mainWindow = mainWindow;
        this.view = view;

        // TODO: Add listeners to the view.
        // changeUsersButton
        view.addAddUsersButtonListener(new AddUsersListener());
        view.addChangeUsersButtonListener(new ChangeUsersListener());

        view.addAddGroupsButtonListener(new AddGroupsListener());
        view.addChangeGroupsButtonListener(new ChangeGroupsListener());
    }

    class AddGroupsListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            Group group = new Group();
            AdminEditPanel<Group> adminEditPanel =
                    new AdminEditPanel<Group>(group);
            mainWindow.setMiddleContentPanel(adminEditPanel);
            AdminEditController adminEditUserController =
                    new AdminEditController(
                    mainWindow, adminEditPanel, Group.class);
        }
    }

    class ChangeGroupsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Group[] groups = null;
            Object[] groups = null;
            AdminListChangePanel adminListChangePanel;
            AdminListChangeController adminListChangeController;
            try
            {
                groups = RmiClient.getController().getAllObjects(Group.class);
            } catch (RemoteException ex)
            {
                Logger.getLogger(AdminAuthModuleController.class.getName()).log(Level.SEVERE, null, ex);
            }

            adminListChangePanel = new AdminListChangePanel(groups);
            adminListChangeController =
                    new AdminListChangeController(
                    mainWindow, adminListChangePanel, Group.class);
            mainWindow.setMiddleContentPanel(adminListChangePanel);
        }
    }

    class AddUsersListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            User user = new User();
            AdminEditPanel<User> adminEditPanel =
                    new AdminEditPanel<User>(user);
            mainWindow.setMiddleContentPanel(adminEditPanel);
            AdminEditController adminEditUserController =
                    new AdminEditController(
                    mainWindow, adminEditPanel, User.class);
        }
    }

    class ChangeUsersListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            User[] users = null;
            AdminListChangePanel adminListChangePanel;
            AdminListChangeController adminListChangeController;
            try
            {
                users = RmiClient.getController().getAllUsers();
            } catch (RemoteException ex)
            {
                Logger.getLogger(AdminAuthModuleController.class.getName()).log(Level.SEVERE, null, ex);
            }

            adminListChangePanel = new AdminListChangePanel(users);
            adminListChangeController =
                    new AdminListChangeController(
                    mainWindow, adminListChangePanel, User.class);
            mainWindow.setMiddleContentPanel(adminListChangePanel);
        }
    }
}
