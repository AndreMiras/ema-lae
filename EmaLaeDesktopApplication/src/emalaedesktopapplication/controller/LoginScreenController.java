/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import database.entity.Users;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.utils.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                    List<String> entitiesAdmin = new ArrayList<String>();
                    if(user!=null)
                    {
                        if(user.isSuperUser())
                        {
                            entitiesAdmin = Arrays.asList(new String[]{
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
                            });
                        }
                        else
                        {
                            // FIXME: DRY
                                if(user.checkPermission("Users_read"))
                                {
                                    entitiesAdmin.add(Utils.getClassNameWithoutPackage(
                                            database.entity.Users.class));
                                }
                                if(user.checkPermission("UserGroup_read"))
                                {
                                    entitiesAdmin.add(Utils.getClassNameWithoutPackage(
                                            database.entity.UserGroup.class));
                                }
                                if(user.checkPermission("Permission_read"))
                                {
                                    entitiesAdmin.add(Utils.getClassNameWithoutPackage(
                                            database.entity.Permission.class));
                                }
                                if(user.checkPermission("UserProfile_read"))
                                {
                                    entitiesAdmin.add(Utils.getClassNameWithoutPackage(
                                            database.entity.UserProfile.class));
                                }
                                if(user.checkPermission("Formation_read"))
                                {
                                    entitiesAdmin.add(Utils.getClassNameWithoutPackage(
                                            database.entity.Formation.class));
                                }
                                if(user.checkPermission("Contract_read"))
                                {
                                    entitiesAdmin.add(Utils.getClassNameWithoutPackage(
                                            database.entity.Contract.class));
                                }
                                if(user.checkPermission("Promotion_read"))
                                {
                                    entitiesAdmin.add(Utils.getClassNameWithoutPackage(
                                            database.entity.Promotion.class));
                                }
                        }
                    }
                    mainWindow.refreshAdminMenu(
                            entitiesAdmin.toArray(new String[0]));
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