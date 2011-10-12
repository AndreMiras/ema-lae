/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entappclient.MainWindowFrame;
import panel.NewUserPanel;

/**
 *
 * @author amiras
 */
public class MainWindowController
{
    private MainWindowFrame mainWindowFrame;
    private NewUserPanel newUserPanel; // TODO: this is only for testing purpose

    public MainWindowController(MainWindowFrame mainWindowFrame)
    {
        this.mainWindowFrame = mainWindowFrame;
        mainWindowFrameSetup();
    }
    
    public final void mainWindowFrameSetup()
    {
        // mainWindowFrame.initMainWindow();
        UserController userController = new UserController();
        newUserPanel = new NewUserPanel(userController);
        mainWindowFrame.setContentPanel(newUserPanel);
    }

}
