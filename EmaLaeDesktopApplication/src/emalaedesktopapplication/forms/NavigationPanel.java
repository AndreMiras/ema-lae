/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NavigationPanel.java
 *
 * Created on Dec 2, 2011, 12:47:34 PM
 */

package emalaedesktopapplication.forms;

import database.entity.Formation;
import database.entity.Promotion;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.List;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author andre
 */
public class NavigationPanel extends javax.swing.JPanel {

    private List<Promotion> promotions;

    /** Creates new form NavigationPanel */
    public NavigationPanel() {
        initComponents();
        customInitComponents();
    }

    /**
     * Removes the nodes background color
     */
    private void customInitComponents()
    {
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setBackgroundNonSelectionColor(null);
        jTree1.setCellRenderer(renderer);
        // setPromotionObject();
    }

    public void addTreeMouseListener(MouseListener l)
    {
        jTree1.addMouseListener(l);
    }

    public void addTreeWillExpandListener(TreeWillExpandListener tel)
    {
        jTree1.addTreeWillExpandListener(tel);
    }

    /**
     * replaces the "Promotion" string by a Promotion object
     */
    private void setPromotionObject()
    {
        DefaultMutableTreeNode promoRootNode = searchNode("Promotion");
        promoRootNode.setUserObject(new Promotion("Promotion"));
    }

    private DefaultMutableTreeNode searchNode(String nodeStr)
    {
        DefaultMutableTreeNode node = null;
        DefaultMutableTreeNode rootNode =
                (DefaultMutableTreeNode) jTree1.getModel().getRoot();
        Enumeration e = rootNode.breadthFirstEnumeration();
        while (e.hasMoreElements())
        {
            node = (DefaultMutableTreeNode) e.nextElement();
            if (nodeStr.equals(node.getUserObject().toString()))
            {
                return node;
            }
        }
        return null;
    }

    private <T> DefaultMutableTreeNode searchNode(Class<T> type)
    {
        DefaultMutableTreeNode node = null;
        DefaultMutableTreeNode rootNode =
                (DefaultMutableTreeNode) jTree1.getModel().getRoot();
        Enumeration e = rootNode.breadthFirstEnumeration();
        while (e.hasMoreElements())
        {
            node = (DefaultMutableTreeNode) e.nextElement();
            if (node.getUserObject().getClass().isAssignableFrom(type))
            {
                return node;
            }
        }
        return null;
    }

    /**
     * Updates the navigation panel with the new promotion list
     * If there's only one item in the list, the root promotion node
     * will be replaced by this item. Otherwise the list will appended
     * to the root promotion node
     * @param promotions
     */
    public void setPromotions(List<Promotion> promotions)
    {
        DefaultMutableTreeNode promoRootNode = searchNode("Promotion");
        // DefaultMutableTreeNode promoRootNode = searchNode(Promotion.class);

        DefaultMutableTreeNode promotionNode;
        // promoRootNode.removeAllChildren(); // Getting a bug with that one
        for (Promotion promotion: promotions)
        {
            promotionNode = new DefaultMutableTreeNode(promotion);
            setFormations(promotionNode);
            promoRootNode.add(promotionNode);
        }

        this.promotions = promotions;
    }

    public void setFormations(DefaultMutableTreeNode promotionNode)
    {
        Promotion promotion = (Promotion) promotionNode.getUserObject();
        DefaultMutableTreeNode formationNode;
        DefaultMutableTreeNode childrenFormationNode;
        for (Formation formation : promotion.getFormations())
        {
            formationNode = new DefaultMutableTreeNode(formation);
            // recursively sets child formations
            setChildrenFormations(formationNode);
            promotionNode.add(formationNode);
        }
    }

    /**
     * Recursively sets child formations
     * @param formationNode
     */
    public void setChildrenFormations(DefaultMutableTreeNode formationNode)
    {
        DefaultMutableTreeNode childrenFormationNode;
        Formation formation = (Formation) formationNode.getUserObject();
        for (Formation child: formation.getChildrenFormations())
        {
            childrenFormationNode = new DefaultMutableTreeNode(child);
            formationNode.add(childrenFormationNode);
            setChildrenFormations(childrenFormationNode);
        }
    }

    /**
     * @return the selected node
     */
    public DefaultMutableTreeNode getCurrentPath()
    {
        DefaultMutableTreeNode node;

        node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();

        return node;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setName("Form"); // NOI18N

        jScrollPane1.setBorder(null);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(NavigationPanel.class);
        jTree1.setBackground(resourceMap.getColor("jTree1.background")); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Home");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Login");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Profile");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("My profile");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Contract");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Promotion");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("/");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Agenda");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TODO");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Messaging");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("TODO");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setName("jTree1"); // NOI18N
        jTree1.setRootVisible(false);
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}
