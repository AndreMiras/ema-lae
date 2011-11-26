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

import java.awt.Component;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import org.metawidget.swing.SwingMetawidget;

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
        populateMetaWidgetValuesFromObject();
    }

    public void addButtonsListener(ActionListener al)
    {
        saveButton.addActionListener(al);
        cancelButton.addActionListener(al);
        deleteButton.addActionListener(al);
    }

    /*
     * FIXME: I though metawidget could do that automagically via automatic binding
     */
    private void populateMetaWidgetValuesFromObject()
    {
        // FIXME: all this code below is terribly ugly.
        //  it has to be a better way of doing it!
        // FIXME: it has to be a way for looping in all attributes/properties
        // from metawidget.xml for a given entity
        List<String> componentNames = new ArrayList<String>();
        int componentCount = metawidget.getComponentCount();
        Component component;
        for (int i=0; i<componentCount; i++)
        {
            component = metawidget.getComponent(i);
            componentNames.add(component.getName());
        }
        String methodName;
        Method tmpMethod;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            methodName = "get"
                    + Character.toUpperCase(field.getName().charAt(0))
                    + field.getName().substring(1);
            try
            {
                tmpMethod = obj.getClass().getDeclaredMethod(methodName, null);
                try
                {
                    Object invoke = tmpMethod.invoke(obj, null);
                     // FIXME: stupid way of checking it
                    if (componentNames.contains(field.getName()))
                    {
                        metawidget.setValue(invoke, new String[] {field.getName()} );
                    }
                } catch (IllegalAccessException ex)
                {
                    Logger.getLogger(AdminEditPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex)
                {
                    Logger.getLogger(AdminEditPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex)
                {
                    Logger.getLogger(AdminEditPanel.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex)
            {
                Logger.getLogger(AdminEditPanel.class.getName()).log(
                        Level.SEVERE, null, ex);
            } catch (SecurityException ex)
            {
                Logger.getLogger(AdminEditPanel.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }

    private Method[] getGetters(Class aClass)
    {
        List<Method> methods = new ArrayList();

        for (Method method : obj.getClass().getMethods())
        {
            if (isGetter(method))
            {
                methods.add(method);
            }
        }

        return methods.toArray(new Method[methods.size()]);
    }

    private Method[] getSetters(Class aClass)
    {
        List<Method> methods = new ArrayList();

        for (Method method : obj.getClass().getMethods())
        {
            if (isSetter(method))
            {
                methods.add(method);
            }
        }

        return methods.toArray(new Method[methods.size()]);
    }

    /**
     * TODO[cleaning: this should actually be part of a custom MapWidgetProcessor
     * A getter method have its name start with "get",
     * take 0 parameters, and returns a value.
     * @param method
     * @return
     */
    private boolean isGetter(Method method)
    {
        if (!method.getName().startsWith("get"))
        {
            return false;
        }
        if (method.getParameterTypes().length != 0)
        {
            return false;
        }
        if (void.class.equals(method.getReturnType()))
        {
            return false;
        }
        return true;
    }

    /**
     * A setter method have its name start with "set", and takes 1 parameter.
     * @param method
     * @return
     */
    private boolean isSetter(Method method)
    {
        if (!method.getName().startsWith("set"))
        {
            return false;
        }
        if (method.getParameterTypes().length != 1)
        {
            return false;
        }
        return true;
    }

    public T getObj()
    {
        return obj;
    }

    /**
     * TODO[cleaning: this should actually be part of a custom MapWidgetProcessor
     * FIXME: I though metawidget could do that automagically
     * Saves all current widget values back to the object and returns it
     * @return updated object
     */
    public T save()
    {
        /* TODO
        object.setUsername( (String) metawidget.getValue( new String[] {"username"} ) );
        object.setPassword( (String) metawidget.getValue( new String[] {"password"} ) );
         */

        // FIXME: all this code below is terribly ugly.
        //  it has to be a better way of doing it!
        // FIXME: it has to be a way for looping in all attributes/properties
        // from metawidget.xml for a given entity
        List<String> componentNames = new ArrayList<String>();
        int componentCount = metawidget.getComponentCount();
        Component component;
        for (int i=0; i<componentCount; i++)
        {
            component = metawidget.getComponent(i);
            componentNames.add(component.getName());
        }
        String methodName;
        Method tmpMethod;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            methodName = "set"
                    + Character.toUpperCase(field.getName().charAt(0))
                    + field.getName().substring(1);
            try
            {
                // FIXME: hardcoded method argument type
                tmpMethod = obj.getClass().getDeclaredMethod(methodName, String.class);
                try
                {
                     // FIXME: stupid way of checking it
                    if (componentNames.contains(field.getName()))
                    {
                        tmpMethod.invoke(obj,
                                metawidget.getValue(new String[] {field.getName()}));
                    }
                } catch (IllegalAccessException ex)
                {
                    Logger.getLogger(AdminEditPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex)
                {
                    Logger.getLogger(AdminEditPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex)
                {
                    Logger.getLogger(AdminEditPanel.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex)
            {
                Logger.getLogger(AdminEditPanel.class.getName()).log(
                        Level.SEVERE, null, ex);
            } catch (SecurityException ex)
            {
                Logger.getLogger(AdminEditPanel.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }

        return obj;
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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emalaedesktopapplication.EmaLaeDesktopApplication.class).getContext().getResourceMap(AdminEditPanel.class);
        middleContentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("middleContentPanel.border.title"))); // NOI18N
        middleContentPanel.setName("middleContentPanel"); // NOI18N
        middleContentPanel.setLayout(new java.awt.CardLayout());

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
