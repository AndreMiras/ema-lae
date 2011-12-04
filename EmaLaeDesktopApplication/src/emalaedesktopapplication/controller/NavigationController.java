/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.forms.NavigationPanel;
import emalaedesktopapplication.forms.ViewProfile;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author andre
 */
public class NavigationController
{

    private EmaLaeDesktopView mainWindow;
    private NavigationPanel navigationPanel;

    public NavigationController(EmaLaeDesktopView mainWindow,
            NavigationPanel navigationPanel)
    {
        this.mainWindow = mainWindow;
        this.navigationPanel = navigationPanel;

        // Add listeners to the view.
        navigationPanel.addTreeMouseListener(new TreeMouseListener());
    }

    /**
     * Tries to log the user in using given credential
     */
    class TreeMouseListener implements MouseListener
    {

        public void mouseClicked(MouseEvent me)
        {
            // TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
            DefaultMutableTreeNode node = navigationPanel.getCurrentPath();
            System.out.println("Node selected:");
            if (node != null)
            {
                System.out.println(node.toString());

                if (node.toString().toLowerCase().equals("home") ||
                    node.toString().toLowerCase().equals("login"))
                {
                    LoginScreenPanel loginScreenPanel = new LoginScreenPanel();
                    LoginScreenController loginScreenController =
                            new LoginScreenController(
                                mainWindow, loginScreenPanel);
                    mainWindow.setMiddleContentPanel(loginScreenPanel);
                }
                else if (node.toString().toLowerCase().equals("my profile"))
                {
                    ViewProfile viewProfilePanel = new ViewProfile();
                    mainWindow.setMiddleContentPanel(viewProfilePanel);
                }
                else
                {
                    // TODO: log this one
                    System.out.println("Unknown navigation item.");
                }
            }
        }

        public void mousePressed(MouseEvent me)
        {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent me)
        {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseEntered(MouseEvent me)
        {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseExited(MouseEvent me)
        {
            // throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
