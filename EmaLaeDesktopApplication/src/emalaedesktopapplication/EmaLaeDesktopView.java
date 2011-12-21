/*
 * EmaLaeDesktopView.java
 */

package emalaedesktopapplication;

import client.ControllerServiceClient;
import database.entity.Users;
import emalaedesktopapplication.controller.MainWindowController;
import emalaecommon.Utils;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * The application's main frame.
 */
public class EmaLaeDesktopView extends FrameView {

    private String[] entitiesAdmin;

    public EmaLaeDesktopView(SingleFrameApplication app) {
        super(app);

        initComponents();
        customInitComponents();
        setMainWindowController();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = EmaLaeDesktopApplication.getApplication().getMainFrame();
            aboutBox = new EmaLaeDesktopAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        EmaLaeDesktopApplication.getApplication().show(aboutBox);
    }

    /**
     * Replace the old middleContentPanel with the given one
     * @param panel to set in the middle
     */
    // public void setMiddleContentPanel(JPanel panel)
    public void setMiddleContentPanel(JComponent panel)
    {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(null);
        middleContentPanel.removeAll();
        panel.setVisible(true);
        // java.awt.CardLayout cl = (java.awt.CardLayout)(panel.getLayout());
        // cl.show(panel, null);
        // panel.getName();
        middleContentPanel.add(scrollPane,
                "FIXME: constraint must be a string, see #14");
        middleContentPanel.revalidate();
    }

    /**
     * Fills the admin menu with all possible entities
     * @param entitiesAdmin
     */
    public void populateAdminMenu(String[] entitiesAdmin)
    {
        this.entitiesAdmin = entitiesAdmin;
        javax.swing.JMenuItem menuItemChange;
        for (String entity : entitiesAdmin)
        {
            menuItemChange = new javax.swing.JMenuItem(entity);
            menuItemChange.setActionCommand(entity);
            menuItemChange.setName(entity);
            adminListChangeMenu.add(menuItemChange);
        }
        Component[] components = adminListChangeMenu.getMenuComponents();
        for (Component component: components)
        {
            component.setVisible(false);
        }
    }

    /**
     * Refreshes the admin menu, showing or hiding relevant items
     * @param visibleEntities
     */
    public void visibleAdminMenues(String[] visibleEntities)
    {
        for (String entity : visibleEntities)
        {
            Component[] components = adminListChangeMenu.getMenuComponents();
            for (Component component: components)
            {
                if (component.getName().equals(entity))
                {
                    component.setVisible(true);
                }
            }
        }
    }

    /**
     * Also refreshes the admin menu based on given user permissions
     * @param user
     */
    public void visibleAdminMenues(Users user)
    {
        List<String> visibleAdminEntities = new ArrayList<String>();
        if (user != null)
        {
            // if super user, just display them all
            if (user.isSuperUser())
            {
                visibleAdminEntities = Arrays.asList(entitiesAdmin);
            } else
            {
                // FIXME: DRY
                if (user.checkPermission("Users_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.Users.class));
                }
                if (user.checkPermission("UserGroup_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.UserGroup.class));
                }
                if (user.checkPermission("Permission_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.Permission.class));
                }
                if (user.checkPermission("UserProfile_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.UserProfile.class));
                }
                if (user.checkPermission("Formation_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.Formation.class));
                }
                if (user.checkPermission("Contract_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.Contract.class));
                }
                if (user.checkPermission("Promotion_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.Promotion.class));
                }
                if (user.checkPermission("CourseSession_read"))
                {
                    visibleAdminEntities.add(Utils.getClassNameWithoutPackage(
                            database.entity.CourseSession.class));
                }
            }

        visibleAdminMenues(
                visibleAdminEntities.toArray(new String[0]));
        setAdminVisible(user.isStaff() || user.isSuperUser());
        }
    }

    public void addListChangeSubMenusListener(ActionListener al)
    {
        javax.swing.JMenuItem menuItem;
        for (int i=0; i < adminListChangeMenu.getItemCount(); i++)
        {
            menuItem = adminListChangeMenu.getItem(i);
            menuItem.addActionListener(al);
        }
    }

    /**
     * Performs some additional component init
     *  - set main widow minimum size (from its main component min size)
     *  - Hide the admin panel by default
     */
    private void customInitComponents()
    {
        JFrame mainFrame = this.getFrame();
        JPanel componentPanel = this.mainPanel;

        adminMenu.setVisible(false);

        mainFrame.pack();
        mainFrame.setMinimumSize(componentPanel.getMinimumSize());
        // mainFrame.setMinimumSize(new Dimension(800, 500));
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // mainFrame.setResizable(false);
        mainFrame.validate();
    }

    public void setAdminVisible(boolean visible){
        adminMenu.setVisible(visible);
    }
    /**
     * Constructs default controllers
     */
    private void setMainWindowController()
    {
        MainWindowController mainWindowController;
        mainWindowController =
                new MainWindowController(this, navigationPanel1);
    }

    public void loginRequiredErrorMessage()
    {
        JOptionPane.showMessageDialog(mainPanel, "Login required.");
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

        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        recreateDataSetMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        adminMenu = new javax.swing.JMenu();
        editViewMenuItem = new javax.swing.JMenuItem();
        adminListChangeMenu = new javax.swing.JMenu();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        mainPanel = new javax.swing.JPanel();
        middleContentPanel = new javax.swing.JPanel();
        navigationPanel1 = new emalaedesktopapplication.forms.NavigationPanel();

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(EmaLaeDesktopView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        recreateDataSetMenuItem.setText(resourceMap.getString("recreateDataSetMenuItem.text")); // NOI18N
        recreateDataSetMenuItem.setName("recreateDataSetMenuItem"); // NOI18N
        recreateDataSetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recreateDataSetMenuItemClicked(evt);
            }
        });
        fileMenu.add(recreateDataSetMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getActionMap(EmaLaeDesktopView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        adminMenu.setText(resourceMap.getString("adminMenu.text")); // NOI18N
        adminMenu.setName("adminMenu"); // NOI18N

        editViewMenuItem.setText(resourceMap.getString("editViewMenuItem.text")); // NOI18N
        editViewMenuItem.setName("editViewMenuItem"); // NOI18N
        adminMenu.add(editViewMenuItem);

        adminListChangeMenu.setText(resourceMap.getString("adminListChangeMenu.text")); // NOI18N
        adminListChangeMenu.setName("adminListChangeMenu"); // NOI18N
        adminMenu.add(adminListChangeMenu);

        menuBar.add(adminMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 566, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("mainPanel.border.title"))); // NOI18N
        mainPanel.setMinimumSize(new java.awt.Dimension(750, 550));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.GridBagLayout());

        middleContentPanel.setName("middleContentPanel"); // NOI18N
        middleContentPanel.setPreferredSize(new java.awt.Dimension(400, 200));
        middleContentPanel.setLayout(new java.awt.CardLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(middleContentPanel, gridBagConstraints);

        navigationPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("navigationPanel1.border.title"))); // NOI18N
        navigationPanel1.setName("navigationPanel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(navigationPanel1, gridBagConstraints);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void recreateDataSetMenuItemClicked(java.awt.event.ActionEvent evt)//GEN-FIRST:event_recreateDataSetMenuItemClicked
    {//GEN-HEADEREND:event_recreateDataSetMenuItemClicked
        try
        {
            ControllerServiceClient.getController().initDatabaseForTests();
        } catch (RemoteException ex)
        {
            Logger.getLogger(EmaLaeDesktopView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_recreateDataSetMenuItemClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu adminListChangeMenu;
    private javax.swing.JMenu adminMenu;
    private javax.swing.JMenuItem editViewMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel middleContentPanel;
    private emalaedesktopapplication.forms.NavigationPanel navigationPanel1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenuItem recreateDataSetMenuItem;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
