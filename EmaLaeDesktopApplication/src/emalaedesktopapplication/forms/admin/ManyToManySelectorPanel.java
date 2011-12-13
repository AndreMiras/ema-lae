/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ManyToManySelectorPanel.java
 *
 * Created on Dec 8, 2011, 8:53:03 AM
 */

package emalaedesktopapplication.forms.admin;

import database.entity.WithPrimaryKey;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author andre
 */
public class ManyToManySelectorPanel<T extends WithPrimaryKey>
        extends javax.swing.JPanel {

    private Class<T> type;
    private List<T> allObjects;
    private List<T> selectedObjects;
    private DefaultListModel allObjectsListModel;
    private DefaultListModel selectedObjectsListModel;

    public ManyToManySelectorPanel(
            Class<T> type,
            List<T> allObjects,
            List<T> selectedObjects)
    {
        initComponents();
        this.type = type;
        this.allObjects = allObjects;
        this.selectedObjects = selectedObjects;
        customInitComponents();
    }

    /**
     * @return the list of selected objects
     */
    public List getSelectedObjects()
    {
        // FIXME
        Object[] toArray = selectedObjectsListModel.toArray();
        return Arrays.asList(toArray);
    }

    public void addWidgetUpdatedListener(ListDataListener ldl)
    {
        selectedObjectsListModel.addListDataListener(ldl);
    }

    private Boolean containsObject(List<WithPrimaryKey> objects, WithPrimaryKey obj)
    {
        Boolean contains = false;
        int count = objects.size() -1;
        while(!contains && count >= 0)
        {
            contains = objects.get(count).getPrimaryKey().equals(
                    obj.getPrimaryKey());
            count++;
        }

        return contains;
    }

    /**
     * Changes the JList model
     */
    private void customInitComponents()
    {
        T obj;
        allObjectsListModel = new DefaultListModel();
        selectedObjectsListModel = new DefaultListModel();
        for (int i = 0; i < allObjects.size(); i++)
        {
            obj = allObjects.get(i);
            if (containsObject((List<WithPrimaryKey>) selectedObjects, obj))
            {
                selectedObjectsListModel.addElement(obj);
            }
            else
            {
                allObjectsListModel.addElement(obj);
            }
        }

        allObjectsListWidget.setModel(allObjectsListModel);
        selectedObjectsListWidget.setModel(selectedObjectsListModel);
    }

    /**
     * Moves an item from the all list box to the selected list box
     * @param index
     */
    private void moveToSelected(int index)
    {
        // getSelectedSelectedObjectList
        Object obj = allObjectsListModel.remove(index);
        selectedObjectsListModel.addElement(obj);
    }

    /**
     * Removes an item from the selected list box back to the all list box
     * @param index
     */
    private void removeFromSelected(int index)
    {
        Object obj = selectedObjectsListModel.remove(index);
        allObjectsListModel.addElement(obj);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        selectedObjectsListWidget = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        allObjectsListWidget = new javax.swing.JList();

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        selectedObjectsListWidget.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        selectedObjectsListWidget.setName("selectedObjectsListWidget"); // NOI18N
        jScrollPane2.setViewportView(selectedObjectsListWidget);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(ManyToManySelectorPanel.class);
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        add(jButton1, gridBagConstraints);

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jButton2, gridBagConstraints);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        allObjectsListWidget.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        allObjectsListWidget.setName("allObjectsListWidget"); // NOI18N
        jScrollPane1.setViewportView(allObjectsListWidget);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        moveToSelected(allObjectsListWidget.getSelectedIndex());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        removeFromSelected(selectedObjectsListWidget.getSelectedIndex());
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList allObjectsListWidget;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList selectedObjectsListWidget;
    // End of variables declaration//GEN-END:variables

}
