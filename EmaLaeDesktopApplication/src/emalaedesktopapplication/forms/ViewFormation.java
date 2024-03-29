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

import database.entity.CourseSession;
import database.entity.Formation;
//import database.entity.Session;
import javax.swing.DefaultListModel;

/**
 *
 * @author pc
 */
public class ViewFormation extends javax.swing.JPanel {

    private DefaultListModel selectedFormationListModel;
    /** Creates new form ViewFormation */
    public ViewFormation()
    {
        initComponents();
    }


    public void setFormation(Formation formation)
    {
        formationNameValue.setText(formation.getName());
        setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                "Formation " + formation.toString()));
        
        ViewSession viewSession;
        for (CourseSession session: formation.getSessions())
        {
            viewSession = new ViewSession();
            viewSession.setSession(session);
            formationDetailsTabbedPane.addTab(
                    session.getType().name(), viewSession);

        }

        // only show the Sub formation tab if relevant
        if (!formation.getChildrenFormations().isEmpty())
        {
            selectedFormationListModel = new DefaultListModel();
            for (Formation childFormation : formation.getChildrenFormations())
            {
                selectedFormationListModel.addElement(childFormation.getName());
            }
            childrenFormationsList.setModel(selectedFormationListModel);
        }
        else // otherwise hide/remove it
        {
            formationDetailsTabbedPane.removeTabAt(0);

            // Do not show the tabPane at all if it has no tabs
            if (formationDetailsTabbedPane.getTabCount() == 0)
            {
                formationDetailsTabbedPane.setVisible(false);
            }
            subFormationPanel.setVisible(false);
        }

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


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formationNameLabel = new javax.swing.JLabel();
        formationNameValue = new javax.swing.JLabel();
        formationDetailsTabbedPane = new javax.swing.JTabbedPane();
        subFormationPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        childrenFormationsList = new javax.swing.JList();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(ViewFormation.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("Form.border.title"))); // NOI18N
        setAutoscrolls(true);
        setName("Form"); // NOI18N

        formationNameLabel.setText(resourceMap.getString("formationNameLabel.text")); // NOI18N
        formationNameLabel.setName("formationNameLabel"); // NOI18N

        formationNameValue.setText(resourceMap.getString("formationNameValue.text")); // NOI18N
        formationNameValue.setName("formationNameValue"); // NOI18N

        formationDetailsTabbedPane.setName("formationDetailsTabbedPane"); // NOI18N

        subFormationPanel.setName("subFormationPanel"); // NOI18N

        jScrollPane1.setBorder(null);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        childrenFormationsList.setBackground(resourceMap.getColor("childrenFormationsList.background")); // NOI18N
        childrenFormationsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        childrenFormationsList.setName("childrenFormationsList"); // NOI18N
        jScrollPane1.setViewportView(childrenFormationsList);

        javax.swing.GroupLayout subFormationPanelLayout = new javax.swing.GroupLayout(subFormationPanel);
        subFormationPanel.setLayout(subFormationPanelLayout);
        subFormationPanelLayout.setHorizontalGroup(
            subFormationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subFormationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );
        subFormationPanelLayout.setVerticalGroup(
            subFormationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subFormationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );

        formationDetailsTabbedPane.addTab(resourceMap.getString("subFormationPanel.TabConstraints.tabTitle"), subFormationPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(formationNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formationNameValue))
                    .addComponent(formationDetailsTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formationNameLabel)
                    .addComponent(formationNameValue))
                .addGap(18, 18, 18)
                .addComponent(formationDetailsTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        formationDetailsTabbedPane.getAccessibleContext().setAccessibleName(resourceMap.getString("jTabbedPane1.AccessibleContext.accessibleName")); // NOI18N

        getAccessibleContext().setAccessibleName(resourceMap.getString("Form.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList childrenFormationsList;
    private javax.swing.JTabbedPane formationDetailsTabbedPane;
    private javax.swing.JLabel formationNameLabel;
    private javax.swing.JLabel formationNameValue;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel subFormationPanel;
    // End of variables declaration//GEN-END:variables
}
