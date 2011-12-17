/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.forms.NavigationPanel;
import emalaedesktopapplication.utils.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import database.entity.Users;
import java.rmi.RemoteException;

/**
 *
 * @author andre
 */
public class MainWindowController
{

    private EmaLaeDesktopView view;
    private LoginScreenPanel loginScreenPanel;
    private LoginScreenController loginScreenController;
    private NavigationController navigationController;
    private String[] entitiesAdmin;


    public MainWindowController(EmaLaeDesktopView view,
            NavigationPanel navigationPanel)
    {
        try
        {
            Users user = ControllerServiceClient.getController().getUser();
            boolean users = false;
            boolean userGroups = false;
            boolean formations = false;
            boolean permissions = false;
            boolean userProfiles = false;
            boolean contracts = false;
            if(user!=null)
            {
                for(int i=0;i<user.getSpecialPermissions().size();i++)
                {
                    if(user.checkPermission("Users_read") && !users)
                    {
                        entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                database.entity.Users.class);
                        users = true;
                        break;
                    }
                    if(user.checkPermission("UserGroup_read") && !userGroups)
                    {
                        entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                database.entity.UserGroup.class);
                        userGroups = true;
                        break;
                    }
                    if(user.checkPermission("Permission_read") && !userGroups)
                    {
                        entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                database.entity.Permission.class);
                        permissions = true;
                        break;
                    }
                    if(user.checkPermission("UserProfile_read") && !userGroups)
                    {
                        entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                database.entity.UserProfile.class);
                        userProfiles = true;
                        break;
                    }
                    if(user.checkPermission("Formation_read") && !userGroups)
                    {
                        entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                database.entity.Formation.class);
                        formations = true;
                        break;
                    }
                    if(user.checkPermission("Contract_read") && !userGroups)
                    {
                        entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                database.entity.Contract.class);
                        contracts = true;
                        break;
                    }
                }
            }
            else
            {
                entitiesAdmin = new String[]
                {
                    Utils.getClassNameWithoutPackage(
                            database.entity.Users.class),
                    Utils.getClassNameWithoutPackage(
                            database.entity.UserGroup.class),
                    Utils.getClassNameWithoutPackage(
                            database.entity.Permission.class),
                    Utils.getClassNameWithoutPackage(
                            database.entity.UserProfile.class),
                    Utils.getClassNameWithoutPackage(
                            database.entity.Formation.class),
                    Utils.getClassNameWithoutPackage(
                            database.entity.Contract.class),
                };
            }
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.view = view;
        loginScreenPanel = new LoginScreenPanel();
        loginScreenController = new LoginScreenController(view, loginScreenPanel);
        setMiddleContent(loginScreenPanel);

        navigationController = new NavigationController(view, navigationPanel);

        initAdminMenu();
        addListeners();
    }

    private void setMiddleContent(JPanel panel)
    {
        view.setMiddleContentPanel(panel);
    }

    private void initAdminMenu()
    {
        view.populateAdminMenu(entitiesAdmin);
    }

    /**
     * registers all the listeners
     */
    private void addListeners()
    {
        view.addListChangeSubMenusListener(new AdminListChangeSubMenuListener());
    }

    /**
     * Listener for all Admin->"New"->SubMenuItems
     */
    class AdminListChangeSubMenuListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            Object obj;
            // AdminController adminController = null;
            JMenuItem target = (JMenuItem) e.getSource();
            String actionCommand = target.getActionCommand();

            if (actionCommand.equals(Utils.getClassNameWithoutPackage(
                    database.entity.Users.class)))
            {
                AdminController<database.entity.Users> adminController =
                        new AdminController<database.entity.Users>(
                            view, database.entity.Users.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals(Utils.getClassNameWithoutPackage(
                    database.entity.UserGroup.class)))
            {
               AdminController<database.entity.UserGroup> adminController =
                        new AdminController<database.entity.UserGroup>(
                            view, database.entity.UserGroup.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals(Utils.getClassNameWithoutPackage(
                    database.entity.Permission.class)))
            {
               AdminController<database.entity.Permission> adminController =
                        new AdminController<database.entity.Permission>(
                            view, database.entity.Permission.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals(Utils.getClassNameWithoutPackage(
                    database.entity.UserProfile.class)))
            {
               AdminController<database.entity.UserProfile> adminController =
                        new AdminController<database.entity.UserProfile>(
                            view, database.entity.UserProfile.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals(Utils.getClassNameWithoutPackage(
                    database.entity.Formation.class)))
            {
               AdminController<database.entity.Formation> adminController =
                        new AdminController<database.entity.Formation>(
                            view, database.entity.Formation.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals(Utils.getClassNameWithoutPackage(
                    database.entity.Contract.class)))
            {
               AdminController<database.entity.Contract> adminController =
                        new AdminController<database.entity.Contract>(
                            view, database.entity.Contract.class);
                adminController.adminListChange();
            }
            else
            {
                Logger.getLogger(MainWindowController.class.getName()).log(
                        Level.WARNING, null, "Unknown entity type");
            }
        }
    }
}
