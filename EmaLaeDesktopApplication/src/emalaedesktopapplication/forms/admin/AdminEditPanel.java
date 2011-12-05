/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdminEditPanel.java
 *
 * Created on Nov 20, 2011, 2:36:54 PM
 */
package emalaedesktopapplication.forms.admin;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;

/**
 *
 * @author andre
 */
public class AdminEditPanel<T> extends javax.swing.JPanel
{
    private final T obj;
    private Class<T> type;
    private SwingMetawidget metawidget;
    // private PersonTest user;

    /** Creates new form AdminEditPanel */
    /*
    public AdminEditPanel()
    {
        initComponents();
    }
     */
    public AdminEditPanel(Class<T> type)
            throws InstantiationException, IllegalAccessException
    {
        this(type, type.newInstance());
    }

    public AdminEditPanel(T obj)
    {
        this(null, obj);
    }

    public AdminEditPanel(Class<T> type, T obj)
    {
        this.type = type;
        this.obj = obj;

        initComponents();
        initMetaWidget();
        initCustomWidgets();
    }

    public void addButtonsListener(ActionListener al)
    {
        saveButton.addActionListener(al);
        cancelButton.addActionListener(al);
        deleteButton.addActionListener(al);
    }


    public T getObj()
    {
        return obj;
    }

    /**
     * Saves all current widget values back to the object and returns it
     * TODO: If set to READ or READ_WRITE (the default is READ_ONCE),
     * the object being inspected must provide PropertyChangeSupport.
     * If set to READ_WRITE, updates to the UI are automatically sync'ed back
     * to the setToInspect, otherwise the client must manually call save.
     * @return updated object
     */
    public T save()
    {
        metawidget.getWidgetProcessor(
                BeansBindingProcessor.class ).save(metawidget);
        return obj;
    }

    /**
     * - sets some labels
     * - makes the return (Enter) key validate form, refs #30
     */
    private void initCustomWidgets()
    {
        middleContentPanel.setBorder(
                javax.swing.BorderFactory.createTitledBorder(type.toString()));
        KeyListener keyListener = new KeyListener()
        {

            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    saveButton.doClick();
                }
            }

            public void keyTyped(KeyEvent ke)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            public void keyReleased(KeyEvent ke)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        // loops over all the components and registers the enter key event
        for (int i=0; i<metawidget.getComponentCount(); i++)
        {
            metawidget.getComponent(i).addKeyListener(keyListener);
        }
    }

    private void initMetaWidget()
    {
        metawidget = new SwingMetawidget();
        metawidget.setConfig("emalaedesktopapplication/metawidget.xml");

        metawidget.setToInspect(obj);
        setMiddleContentPanel(metawidget);
    }

    /**
     * Replace the old middleContentPanel with the given one
     * @param panel to set in the middle
     */
    // public void setMiddleContentPanel(JPanel panel)
    public void setMiddleContentPanel(JComponent panel)
    {
        middleContentPanel.removeAll();
        panel.setVisible(true);
        middleContentPanel.add(panel,
                "FIXME: constraint must be a string, see #14");
        middleContentPanel.revalidate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        middleContentPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        middleContentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Object"));
        middleContentPanel.setName("middleContentPanel"); // NOI18N
        middleContentPanel.setLayout(new java.awt.CardLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(AdminEditPanel.class);
        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setName("saveButton"); // NOI18N

        deleteButton.setText(resourceMap.getString("deleteButton.text")); // NOI18N
        deleteButton.setName("deleteButton"); // NOI18N

        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(middleContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(middleContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel middleContentPanel;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
