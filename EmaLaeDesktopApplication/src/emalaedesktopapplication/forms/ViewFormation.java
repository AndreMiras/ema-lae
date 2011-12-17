/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewFormation.java
 *
 * Created on 17 déc. 2011, 20:07:49
 */

package emalaedesktopapplication.forms;

import database.entity.Formation;
//import database.entity.Session;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 *
 * @author pc
 */
public class ViewFormation extends javax.swing.JPanel {

    private DefaultListModel selectedFormationListModel;
    /** Creates new form ViewFormation */
    public ViewFormation() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parentFormationPanel = new javax.swing.JPanel();
        parentFormationParentLabel = new javax.swing.JLabel();
        parentFormationNameLabel = new javax.swing.JLabel();
        parentFormationNameTextField = new javax.swing.JTextField();
        parentFormationParentTextField = new javax.swing.JTextField();
        childrenFormationsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        childrenFormationsList = new javax.swing.JList();
        sessionsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sessionsList = new javax.swing.JList();
        formationNameLabel = new javax.swing.JLabel();
        formationNameTextField = new javax.swing.JTextField();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(ViewFormation.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("Form.border.title"))); // NOI18N
        setAutoscrolls(true);
        setName("Form"); // NOI18N

        parentFormationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("parentFormationPanel.border.title"))); // NOI18N
        parentFormationPanel.setName("parentFormationPanel"); // NOI18N

        parentFormationParentLabel.setText(resourceMap.getString("parentFormationParentLabel.text")); // NOI18N
        parentFormationParentLabel.setName("parentFormationParentLabel"); // NOI18N

        parentFormationNameLabel.setText(resourceMap.getString("parentFormationNameLabel.text")); // NOI18N
        parentFormationNameLabel.setName("parentFormationNameLabel"); // NOI18N

        parentFormationNameTextField.setText(resourceMap.getString("parentFormationNameTextField.text")); // NOI18N
        parentFormationNameTextField.setEnabled(false);
        parentFormationNameTextField.setName("parentFormationNameTextField"); // NOI18N
        parentFormationNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentFormationNameTextFieldActionPerformed(evt);
            }
        });

        parentFormationParentTextField.setText(resourceMap.getString("parentFormationParentTextField.text")); // NOI18N
        parentFormationParentTextField.setEnabled(false);
        parentFormationParentTextField.setName("parentFormationParentTextField"); // NOI18N

        javax.swing.GroupLayout parentFormationPanelLayout = new javax.swing.GroupLayout(parentFormationPanel);
        parentFormationPanel.setLayout(parentFormationPanelLayout);
        parentFormationPanelLayout.setHorizontalGroup(
            parentFormationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parentFormationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(parentFormationNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(parentFormationNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(parentFormationParentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(parentFormationParentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        parentFormationPanelLayout.setVerticalGroup(
            parentFormationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parentFormationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parentFormationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parentFormationParentLabel)
                    .addComponent(parentFormationNameLabel)
                    .addComponent(parentFormationParentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentFormationNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        childrenFormationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("childrenFormationsPanel.border.title"))); // NOI18N
        childrenFormationsPanel.setName("childrenFormationsPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        childrenFormationsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        childrenFormationsList.setName("childrenFormationsList"); // NOI18N
        jScrollPane1.setViewportView(childrenFormationsList);

        javax.swing.GroupLayout childrenFormationsPanelLayout = new javax.swing.GroupLayout(childrenFormationsPanel);
        childrenFormationsPanel.setLayout(childrenFormationsPanelLayout);
        childrenFormationsPanelLayout.setHorizontalGroup(
            childrenFormationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(childrenFormationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        childrenFormationsPanelLayout.setVerticalGroup(
            childrenFormationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(childrenFormationsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        sessionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("sessionsPanel.border.title"))); // NOI18N
        sessionsPanel.setName("sessionsPanel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        sessionsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        sessionsList.setName("sessionsList"); // NOI18N
        jScrollPane2.setViewportView(sessionsList);

        javax.swing.GroupLayout sessionsPanelLayout = new javax.swing.GroupLayout(sessionsPanel);
        sessionsPanel.setLayout(sessionsPanelLayout);
        sessionsPanelLayout.setHorizontalGroup(
            sessionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sessionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        sessionsPanelLayout.setVerticalGroup(
            sessionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sessionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
        );

        formationNameLabel.setText(resourceMap.getString("formationNameLabel.text")); // NOI18N
        formationNameLabel.setName("formationNameLabel"); // NOI18N

        formationNameTextField.setText(resourceMap.getString("formationNameTextField.text")); // NOI18N
        formationNameTextField.setEnabled(false);
        formationNameTextField.setName("formationNameTextField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(parentFormationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(childrenFormationsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sessionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(formationNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formationNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formationNameLabel)
                    .addComponent(formationNameTextField, 0, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(parentFormationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sessionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(childrenFormationsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        parentFormationPanel.getAccessibleContext().setAccessibleName(resourceMap.getString("parentFormationPanel.AccessibleContext.accessibleName")); // NOI18N

        getAccessibleContext().setAccessibleName(resourceMap.getString("Form.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void parentFormationNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentFormationNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_parentFormationNameTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList childrenFormationsList;
    private javax.swing.JPanel childrenFormationsPanel;
    private javax.swing.JLabel formationNameLabel;
    private javax.swing.JTextField formationNameTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel parentFormationNameLabel;
    private javax.swing.JTextField parentFormationNameTextField;
    private javax.swing.JPanel parentFormationPanel;
    private javax.swing.JLabel parentFormationParentLabel;
    private javax.swing.JTextField parentFormationParentTextField;
    private javax.swing.JList sessionsList;
    private javax.swing.JPanel sessionsPanel;
    // End of variables declaration//GEN-END:variables

    public void setFormation(Formation formation) {
        formationNameTextField.setText(formation.getName());
        parentFormationNameTextField.setText(formation.getParentFormation().getName());
        parentFormationParentTextField.setText(formation.getParentFormation().getParentFormation().getName());
        selectedFormationListModel = new DefaultListModel();
        for (Formation childFormation : formation.getChildrenFormations())
        {
            selectedFormationListModel.addElement(childFormation.getName());
        }
        childrenFormationsList.setModel(selectedFormationListModel);

        /*
         * FIXME : Sessions need to be implemented in business class
        selectedFormationListModel = new DefaultListModel();
        for (Session session : formation.getSessions())
        {
            selectedFormationListModel.addElement(session.getName());
        }
        sessionsList.setModel(selectedFormationListModel);
         * 
         */
    }

}
