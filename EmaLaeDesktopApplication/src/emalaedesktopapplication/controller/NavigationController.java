/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.NavigationPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.tree.TreePath;

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
            TreePath tp = navigationPanel.getCurrentPath();
            System.out.println("Mouse clicked:");
            if (tp != null)
            {
                System.out.println(tp.toString());
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
