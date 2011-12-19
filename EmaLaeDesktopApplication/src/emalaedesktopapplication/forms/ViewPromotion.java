/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewPromotion.java
 *
 * Created on 18 déc. 2011, 00:56:51
 */

package emalaedesktopapplication.forms;

import database.entity.Formation;
import database.entity.Promotion;
import database.entity.UserProfile;
import javax.swing.DefaultListModel;

/**
 *
 * @author pc
 */
public class ViewPromotion extends javax.swing.JPanel {

    private DefaultListModel selectedFormationListModel;

    /** Creates new form ViewPromotion */
    public ViewPromotion() {
        initComponents();
    }

    public void setPromotion(Promotion promotion)
    {
        String promoName = promotion.getName();
        UserProfile responsible = promotion.getResponsible();
        Integer promoYear = promotion.getPromotionYear();
        promotionNameValue.setText(promoName);
        if (responsible != null)
        {
            responsibleNameValue.setText(responsible.getFullName());
        }
        if (promoYear != null)
        {
            yearValue.setText(promoYear.toString());
        }

        selectedFormationListModel = new DefaultListModel();
        for (Formation formation : promotion.getFormations())
        {
            selectedFormationListModel.addElement(formation.getName());
        }
        formationList.setModel(selectedFormationListModel);

        selectedFormationListModel = new DefaultListModel();
        for (UserProfile user : promotion.getApprentices())
        {
            selectedFormationListModel.addElement(user.getFullName());
        }
        apprenticesList.setModel(selectedFormationListModel);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        yearLabel = new javax.swing.JLabel();
        responsibleNameLabel = new javax.swing.JLabel();
        yearValue = new javax.swing.JLabel();
        promotionNameLabel = new javax.swing.JLabel();
        promotionNameValue = new javax.swing.JLabel();
        responsibleNameValue = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        formationList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        apprenticesList = new javax.swing.JList();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(ViewPromotion.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("Form.border.title"))); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        yearLabel.setText(resourceMap.getString("yearLabel.text")); // NOI18N
        yearLabel.setName("yearLabel"); // NOI18N

        responsibleNameLabel.setText(resourceMap.getString("responsibleNameLabel.text")); // NOI18N
        responsibleNameLabel.setName("responsibleNameLabel"); // NOI18N

        yearValue.setText(resourceMap.getString("yearValue.text")); // NOI18N
        yearValue.setName("yearValue"); // NOI18N

        promotionNameLabel.setText(resourceMap.getString("promotionNameLabel.text")); // NOI18N
        promotionNameLabel.setName("promotionNameLabel"); // NOI18N

        promotionNameValue.setText(resourceMap.getString("promotionNameValue.text")); // NOI18N
        promotionNameValue.setName("promotionNameValue"); // NOI18N

        responsibleNameValue.setText(resourceMap.getString("responsibleNameValue.text")); // NOI18N
        responsibleNameValue.setName("responsibleNameValue"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(promotionNameLabel)
                    .addComponent(responsibleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(responsibleNameValue)
                    .addComponent(promotionNameValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(yearLabel)
                .addGap(45, 45, 45)
                .addComponent(yearValue)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(promotionNameLabel)
                    .addComponent(promotionNameValue)
                    .addComponent(yearLabel)
                    .addComponent(yearValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsibleNameLabel)
                    .addComponent(responsibleNameValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        formationList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        formationList.setName("formationList"); // NOI18N
        jScrollPane2.setViewportView(formationList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(22, 22, 22))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        apprenticesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        apprenticesList.setName("apprenticesList"); // NOI18N
        jScrollPane1.setViewportView(apprenticesList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList apprenticesList;
    private javax.swing.JList formationList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel promotionNameLabel;
    private javax.swing.JLabel promotionNameValue;
    private javax.swing.JLabel responsibleNameLabel;
    private javax.swing.JLabel responsibleNameValue;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JLabel yearValue;
    // End of variables declaration//GEN-END:variables

}
