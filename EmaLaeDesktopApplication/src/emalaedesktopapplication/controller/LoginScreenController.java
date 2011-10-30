/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.RmiClient;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.forms.ViewProfile;
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
                loginSuccess = RmiClient.getController().login(username, password);
            } catch (RemoteException ex)
            {
                Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                loginSuccess = false;
            }

            // get the user to the home screen if success
            if (loginSuccess)
            {
                ViewProfile viewProfilePanel = new ViewProfile();
                mainWindow.setMiddleContentPanel(viewProfilePanel);
            }
            // otherwise popup an error message
            else
            {
                view.loginErrorMesage();
            }
        }
    }
}