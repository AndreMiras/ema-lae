/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.RmiClient;
import database.entity.Group;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.AdminMainPanel;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.forms.admin.AdminEditPanel;
import emalaedesktopapplication.forms.admin.AdminListChangePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class MainWindowController
{

    private EmaLaeDesktopView view;
    private LoginScreenPanel loginScreenPanel;
    private LoginScreenController loginScreenController;
    private String[] entitiesAdmin = new String[]
    {
        "database.entity.User",
        "database.entity.Group"
    };

    public MainWindowController(EmaLaeDesktopView view)
    {
        this.view = view;
        loginScreenPanel = new LoginScreenPanel();
        loginScreenController = new LoginScreenController(view, loginScreenPanel);
        setMiddleContent(loginScreenPanel);

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
        view.addMenuListener(new MenuListener());
        view.addAddNewSubMenusListener(new AdminAddNewSubMenuListener());
        view.addListChangeSubMenusListener(new AdminListChangeSubMenuListener());
    }

    /**
     * FIXME: deprecated, to be removed
     * Listens and handles all menu actions
     */
    class MenuListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            System.out.println("action performed");
            JMenuItem target = (JMenuItem) e.getSource();
            String actionCommand = target.getActionCommand();
            if (actionCommand.equals("AdminHome"))
            {
                AdminMainPanel adminMainPanel = new AdminMainPanel();
                view.setMiddleContentPanel(adminMainPanel);
                AdminMainController adminMainController = new AdminMainController(view, adminMainPanel);
            }
        }
    }

    /**
     * Listener for all Admin->"New"->SubMenuItems
     */
    class AdminAddNewSubMenuListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            Object obj;
            JMenuItem target = (JMenuItem) e.getSource();
            String actionCommand = target.getActionCommand();
            System.out.println("action performed: " + actionCommand);

            // FIXME: DRY
            if (actionCommand.equals("database.entity.User"))
            {
                AdminEditPanel<database.entity.User> adminEditPanel =
                        new AdminEditPanel<database.entity.User>(new database.entity.User());
                view.setMiddleContentPanel(adminEditPanel);
                AdminEditController adminEditUserController =
                        new AdminEditController(
                        view, adminEditPanel, database.entity.User.class);
            }
            else if (actionCommand.equals("database.entity.Group"))
            {
                AdminEditPanel<database.entity.Group> adminEditPanel =
                        new AdminEditPanel<database.entity.Group>(new database.entity.Group());
                view.setMiddleContentPanel(adminEditPanel);
                AdminEditController adminEditUserController =
                        new AdminEditController(
                        view, adminEditPanel, database.entity.Group.class);
            }
        }
    }

    /**
     * Listener for all Admin->"New"->SubMenuItems
     */
    class AdminListChangeSubMenuListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            Object obj;
            JMenuItem target = (JMenuItem) e.getSource();
            String actionCommand = target.getActionCommand();
            System.out.println("action performed: " + actionCommand);
            AdminListChangePanel adminListChangePanel = null;
            AdminListChangeController adminListChangeController;

            // FIXME: DRY
            if (actionCommand.equals("database.entity.User"))
            {
                Object[] objs = null;
                try
                {
                    objs = RmiClient.getController().getAllObjects(
                            database.entity.User.class);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(AdminAuthModuleController.class.getName()).log(Level.SEVERE, null, ex);
                }

                adminListChangePanel = new AdminListChangePanel(objs);
                adminListChangeController =
                        new AdminListChangeController(
                        view, adminListChangePanel, database.entity.User.class);
            }
            else if (actionCommand.equals("database.entity.Group"))
            {
                Object[] objs = null;
                try
                {
                    objs = RmiClient.getController().getAllObjects(
                            database.entity.Group.class);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(AdminAuthModuleController.class.getName()).log(Level.SEVERE, null, ex);
                }

                adminListChangePanel = new AdminListChangePanel(objs);
                adminListChangeController =
                        new AdminListChangeController(
                        view, adminListChangePanel, database.entity.Group.class);
            }
            view.setMiddleContentPanel(adminListChangePanel);
        }
    }
}
