/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.AdminMainPanel;
import emalaedesktopapplication.forms.LoginScreenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class MainWindowController {

    private EmaLaeDesktopView view;
    private LoginScreenPanel loginScreenPanel;
    private LoginScreenController loginScreenController;

    public MainWindowController(EmaLaeDesktopView view) {
        this.view = view;
        loginScreenPanel = new LoginScreenPanel();
        loginScreenController = new LoginScreenController(view, loginScreenPanel);
        setMiddleContent(loginScreenPanel);

        addListeners();
    }

    private void setMiddleContent(JPanel panel)
    {
        view.setMiddleContentPanel(panel);
    }

    /**
     * registers all the listeners
     */
    private void addListeners()
    {
        view.addMenuListener(new MenuListener());
    }


    /**
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

}
