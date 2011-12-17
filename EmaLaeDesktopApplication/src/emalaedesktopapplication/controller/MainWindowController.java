/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

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
    private String[] entitiesAdmin = new String[]
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
        Utils.getClassNameWithoutPackage(
                database.entity.Promotion.class),
    };

    public MainWindowController(EmaLaeDesktopView view,
            NavigationPanel navigationPanel)
    {
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
            else if (actionCommand.equals(Utils.getClassNameWithoutPackage(
                    database.entity.Promotion.class)))
            {
               AdminController<database.entity.Promotion> adminController =
                        new AdminController<database.entity.Promotion>(
                            view, database.entity.Promotion.class);
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
