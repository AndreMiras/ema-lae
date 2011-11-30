/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.forms.admin.AdminListChangePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        "database.entity.UserProfile",
        "database.entity.Formation"
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
            // AdminController adminController = null;
            JMenuItem target = (JMenuItem) e.getSource();
            String actionCommand = target.getActionCommand();
            System.out.println("action performed: " + actionCommand);
            AdminListChangePanel adminListChangePanel = null;
            AdminListChangeController adminListChangeController;

            if (actionCommand.equals("database.entity.User"))
            {
                AdminController<database.entity.Users> adminController =
                        new AdminController<database.entity.Users>(
                            view, database.entity.Users.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals("database.entity.Group"))
            {
               AdminController<database.entity.UserGroup> adminController =
                        new AdminController<database.entity.UserGroup>(
                            view, database.entity.UserGroup.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals("database.entity.Permission"))
            {
               AdminController<database.entity.Permission> adminController =
                        new AdminController<database.entity.Permission>(
                            view, database.entity.Permission.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals("database.entity.UserProfile"))
            {
               AdminController<database.entity.UserProfile> adminController =
                        new AdminController<database.entity.UserProfile>(
                            view, database.entity.UserProfile.class);
                adminController.adminListChange();
            }
            else if (actionCommand.equals("database.entity.Formation"))
            {
               AdminController<database.entity.Formation> adminController =
                        new AdminController<database.entity.Formation>(
                            view, database.entity.Formation.class);
                adminController.adminListChange();
            }
            else
            {
                Logger.getLogger(AdminController.class.getName()).log(
                        Level.WARNING, null, "Unknown entity type");
            }
        }
    }
}
