/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdminMainPanel.java
 *
 * Created on Nov 13, 2011, 5:36:36 PM
 */

package emalaedesktopapplication.forms;

/**
 *
 * @author andre
 */
public class AdminMainPanel extends javax.swing.JPanel {

    /** Creates new form AdminMainPanel */
    public AdminMainPanel() {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        adminAuthModuleTabPanel1 = new emalaedesktopapplication.forms.AdminAuthModuleTabPanel();

        setName("Form"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        adminAuthModuleTabPanel1.setName("adminAuthModuleTabPanel1"); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(AdminMainPanel.class);
        jTabbedPane1.addTab(resourceMap.getString("adminAuthModuleTabPanel1.TabConstraints.tabTitle"), adminAuthModuleTabPanel1); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private emalaedesktopapplication.forms.AdminAuthModuleTabPanel adminAuthModuleTabPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}