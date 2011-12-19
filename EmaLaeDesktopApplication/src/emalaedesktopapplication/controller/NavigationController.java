/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import database.entity.Formation;
import database.entity.Promotion;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import emalaedesktopapplication.forms.NavigationPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
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
        navigationPanel.addTreeWillExpandListener(new NavigationTreeWillExpandListener());
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
            System.out.println("Node selected:" + node.toString());
            if (node != null)
            {
                if (node.isLeaf())
                {
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
                        UserProfileController userProfileController =
                                new UserProfileController(mainWindow);
                        mainWindow.setMiddleContentPanel(
                        userProfileController.getView());
                    }
                    else
                    {
                        // TODO: log this one
                        System.out.println("Unknown navigation item.");
                    }
                }

                // User clicked on Promotion
                /**
                 * TODO: this is not so reliable
                 * I wish we could use proper generics with
                 * DefaultMutableTreeNode/DefaultTreeModel
                 */
                if (node.getUserObject() instanceof Promotion)
                {
                    Promotion promotion = (Promotion)(node.getUserObject());

                    PromotionController promotionController =
                            new PromotionController(
                                mainWindow, promotion);
                    mainWindow.setMiddleContentPanel(
                            promotionController.getView());
                }
                // User clicked on Formation
                else if (node.getUserObject() instanceof Formation)
                {
                    Formation formation = (Formation)(node.getUserObject());

                    FormationController formationController =
                            new FormationController(
                                mainWindow, formation);
                    mainWindow.setMiddleContentPanel(
                            formationController.getView());
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

    /**
     * Refreshes the Promotion (and children) object just before expanding
     * the tree
     */
    class NavigationTreeWillExpandListener implements TreeWillExpandListener {

        public void treeWillExpand(TreeExpansionEvent evt) throws ExpandVetoException {
            /*
            JTree tree = (JTree) evt.getSource();
            TreePath path = evt.getPath();
             */
            DefaultMutableTreeNode node = navigationPanel.getCurrentPath();

            /*
             * refresh the promotion tree
             * TODO[perfs]: this shouldn't be done everytime we click on it
             * but only when required
             */
            if (node.toString().toLowerCase().equals("promotion"))
            {
                List<Promotion> promotions = null;
                try
                {
                    promotions = ControllerServiceClient.getController(
                            ).<Promotion>getAllObjects(Promotion.class);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(
                            NavigationController.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
                navigationPanel.setPromotions(promotions);
            }
        }

        public void treeWillCollapse(TreeExpansionEvent event)
                throws ExpandVetoException
        {
            // throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
