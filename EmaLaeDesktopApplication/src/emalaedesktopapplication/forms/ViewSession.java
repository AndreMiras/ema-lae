/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewSession.java
 *
 * Created on 18 déc. 2011, 17:48:57
 */

package emalaedesktopapplication.forms;

import database.entity.CourseSession;

/**
 *
 * @author pc
 */
public class ViewSession extends javax.swing.JPanel {

    /** Creates new form ViewSession */
    public ViewSession() {
        initComponents();
    }


    public void setSession(CourseSession session)
    {
        // Default values
        String assignments = "none";
        String startDate = "none";
        String endDate = "none";
        String teacher = "none";

        if(session.getType() != null)
            typeValue.setText(session.getType().toString());
        /* // formation is redundant
        if(session.getFormation() != null)
            formationValue.setText(session.getFormation().getName());
         */
        if(session.getStartDate() != null)
            startDate = session.getStartDate().toString();
        if (session.getEndDate() != null)
            endDate = session.getEndDate().toString();
        if (session.getAssignmentsLink() != null)
            assignments = session.getAssignmentsLink();
        if (session.getTeacher() != null)
            teacher = session.getTeacher().getFullName();

       startDateValue.setText(startDate);
       endDateValue.setText(endDate);
       assignmentsValue.setText(assignments);
       teacherValue.setText(teacher);
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
        assignmentsValue = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        startDateLabel = new javax.swing.JLabel();
        startDateValue = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        endDateValue = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        typeLabel = new javax.swing.JLabel();
        teacherLabel = new javax.swing.JLabel();
        teacherValue = new javax.swing.JLabel();
        typeValue = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(ViewSession.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("Form.border.title"))); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        assignmentsValue.setText(resourceMap.getString("assignmentsValue.text")); // NOI18N
        assignmentsValue.setName("assignmentsValue"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assignmentsValue)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assignmentsValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        startDateLabel.setText(resourceMap.getString("startDateLabel.text")); // NOI18N
        startDateLabel.setName("startDateLabel"); // NOI18N

        startDateValue.setText(resourceMap.getString("startDateValue.text")); // NOI18N
        startDateValue.setName("startDateValue"); // NOI18N

        endDateLabel.setText(resourceMap.getString("endDateLabel.text")); // NOI18N
        endDateLabel.setName("endDateLabel"); // NOI18N

        endDateValue.setText(resourceMap.getString("endDateValue.text")); // NOI18N
        endDateValue.setName("endDateValue"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(startDateLabel)
                        .addGap(18, 18, 18)
                        .addComponent(startDateValue))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(endDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(endDateValue)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDateValue))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endDateValue)
                    .addComponent(endDateLabel))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        typeLabel.setText(resourceMap.getString("typeLabel.text")); // NOI18N
        typeLabel.setName("typeLabel"); // NOI18N

        teacherLabel.setText(resourceMap.getString("teacherLabel.text")); // NOI18N
        teacherLabel.setName("teacherLabel"); // NOI18N

        teacherValue.setText(resourceMap.getString("teacherValue.text")); // NOI18N
        teacherValue.setName("teacherValue"); // NOI18N

        typeValue.setText(resourceMap.getString("typeValue.text")); // NOI18N
        typeValue.setName("typeValue"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teacherLabel)
                    .addComponent(typeLabel))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeValue)
                    .addComponent(teacherValue))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherLabel)
                    .addComponent(teacherValue))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assignmentsValue;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JLabel endDateValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JLabel startDateValue;
    private javax.swing.JLabel teacherLabel;
    private javax.swing.JLabel teacherValue;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JLabel typeValue;
    // End of variables declaration//GEN-END:variables
}
