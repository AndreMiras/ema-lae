/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.RmiClient;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
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
        "database.entity.Group",
        "database.entity.Permission",
        "database.entity.UserProfile"
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
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }

                adminListChangePanel = new AdminListChangePanel(objs);
                adminListChangeController =
                        new AdminListChangeController(
                        view, adminListChangePanel, database.entity.Group.class);
            }
            else if (actionCommand.equals("database.entity.Permission"))
            {
                Object[] objs = null;
                try
                {
                    objs = RmiClient.getController().getAllObjects(
                            database.entity.Permission.class);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }

                adminListChangePanel = new AdminListChangePanel(objs);
                adminListChangeController =
                        new AdminListChangeController(
                        view, adminListChangePanel, database.entity.Permission.class);
            }
            else if (actionCommand.equals("database.entity.UserProfile"))
            {
                Object[] objs = null;
                try
                {
                    objs = RmiClient.getController().getAllObjects(
                            database.entity.UserProfile.class);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }

                adminListChangePanel = new AdminListChangePanel(objs);
                adminListChangeController =
                        new AdminListChangeController(
                        view, adminListChangePanel, database.entity.UserProfile.class);
            }
            view.setMiddleContentPanel(adminListChangePanel);
        }
    }
}
