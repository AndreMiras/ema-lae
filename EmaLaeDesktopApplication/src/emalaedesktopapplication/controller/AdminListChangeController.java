/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import database.entity.User;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.admin.AdminEditPanel;
import emalaedesktopapplication.forms.admin.AdminListChangePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author andre
 */
public class AdminListChangeController<T> {

    private EmaLaeDesktopView mainWindow;
    private AdminListChangePanel view;
    private Class<T> type;

    public AdminListChangeController(
            EmaLaeDesktopView mainWindow,
            AdminListChangePanel view,
            Class<T> type)
    {
        this.mainWindow = mainWindow;
        this.view = view;
        this.type = type;

        view.addEditButtonListener(new EditListener());
    }


    class EditListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            T obj = (T) view.getSelectedItem();

            AdminEditPanel<T> adminEditPanel =
                    new AdminEditPanel<T>(obj);
            mainWindow.setMiddleContentPanel(adminEditPanel);
            AdminEditController adminEditController =
                    new AdminEditController(
                    mainWindow, adminEditPanel, type);
        }
    }

}
