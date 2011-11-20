/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.AdminMainPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author andre
 */
public class AdminMainController
{
    private EmaLaeDesktopView mainWindow;
    private AdminMainPanel view;

    public AdminMainController(EmaLaeDesktopView mainWindow, AdminMainPanel view)
    {
        this.mainWindow = mainWindow;
        this.view = view;

        registerModulesControllers();

        // TODO: Add listeners to the view.
        // view.addConnectionButtonListener(new TodoButtonListener());
    }

    private void registerModulesControllers()
    {
        AdminAuthModuleController adminAuthModuleController =
                new AdminAuthModuleController(
                mainWindow, view.getadminAuthModuleTabPanel1());

    }

    /**
     * Tries to log the user in using given credential
     */
    class TodoButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("action performed");
        }
    }
}
