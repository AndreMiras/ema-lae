/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import database.entity.Permission;
import database.entity.UserGroup;
import database.entity.Users;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.utils.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class LoginScreenController
{
    private EmaLaeDesktopView mainWindow;
    private LoginScreenPanel view;

    public LoginScreenController(EmaLaeDesktopView mainWindow, LoginScreenPanel view)
    {
        this.mainWindow = mainWindow;
        this.view = view;

        // Add listeners to the view.
        view.addConnectionButtonListener(new ConnectionListener());
    }

    /**
     * Tries to log the user in using given credential
     */
    class ConnectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String username;
            String password;
            boolean loginSuccess;

            username = view.getUsernameInput();
            password = view.getPasswordInput();

            try
            {
                loginSuccess = ControllerServiceClient.getController().login(username, password);
            } catch (RemoteException ex)
            {
                Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                loginSuccess = false;
            }

            // get the user to the home screen if success
            if (loginSuccess)
            {
                Users user;
                try
                {
                    user = ControllerServiceClient.getController().getUser();

                    // refresh admin menu
                    String[] entitiesAdmin = new String[user.getTotalNumberOfPermissions()];
                    boolean users = false;
                    boolean userGroups = false;
                    boolean formations = false;
                    boolean permissions = false;
                    boolean userProfiles = false;
                    boolean contracts = false;
                    if(user!=null)
                    {
                        int totalPermissionsSize = user.getTotalNumberOfPermissions();
                        if(user.isSuperUser())
                        {
                            entitiesAdmin = new String[]{
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
                                Utils.getClassNameWithoutPackage(
                                    database.entity.Promotion.class)
                            };
                        }
                        else
                        {
                            for(int i=0;i<totalPermissionsSize;i++)
                            {
                                if(user.checkPermission("Users_read") && !users)
                                {
                                    entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                            database.entity.Users.class);
                                    users = true;
                                }
                                else if(user.checkPermission("UserGroup_read") && !userGroups)
                                {
                                    entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                            database.entity.UserGroup.class);
                                    userGroups = true;
                                }
                                else if(user.checkPermission("Permission_read") && !permissions)
                                {
                                    entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                            database.entity.Permission.class);
                                    permissions = true;
                                }
                                else if(user.checkPermission("UserProfile_read") && !userGroups)
                                {
                                    entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                            database.entity.UserProfile.class);
                                    userProfiles = true;
                                }
                                else if(user.checkPermission("Formation_read") && !userGroups)
                                {
                                    entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                            database.entity.Formation.class);
                                    formations = true;
                                }
                                else if(user.checkPermission("Contract_read") && !userGroups)
                                {
                                    entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                            database.entity.Contract.class);
                                    contracts = true;
                                }
                                else if(user.checkPermission("Promotion_read") && !userGroups)
                                {
                                    entitiesAdmin[i] = Utils.getClassNameWithoutPackage(
                                            database.entity.Promotion.class);
                                    contracts = true;
                                }
                            }
                        }
                    }
                    mainWindow.refreshAdminMenu(entitiesAdmin);
                    mainWindow.setAdminVisible(user.isStaff()||user.isSuperUser());
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                

                UserProfileController userProfileController =
                        new UserProfileController(mainWindow);
                mainWindow.setMiddleContentPanel(
                        userProfileController.getView());
                }
            // otherwise popup an error message
            else
            {
                view.loginErrorMesage();
            }
        }
    }
}