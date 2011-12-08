/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import client.ControllerServiceClient;
import emalaedesktopapplication.forms.admin.AdminListChangePanel;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JList;

import org.metawidget.swing.SwingMetawidget;

public class MetaWidgetUtils
{

    @SuppressWarnings("unchecked")
    public static SwingMetawidget createMetaWidget(Object obj)
    {
        SwingMetawidget metawidget = new SwingMetawidget();
        metawidget.setConfig("emalaedesktopapplication/metawidget.xml");

        metawidget.setToInspect(obj);
        return metawidget;
    }

    public static JList createObjectListWidget(Object[] objs)
    {
        JList objectList = new JList(objs);
        return objectList;
    }

    /**
     * @param obj
     */
    public static void createEditDialog(Object obj)
    {
        JDialog f = new JDialog();
        f.setModal(true);
        f.getContentPane().add(createMetaWidget(obj));
        f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        f.setSize(400, 250);
        f.setVisible(true);
    }

    /**
     * TODO: I would prefer using MVC here (Controller to fetch through RMI)
     * @param <T>
     * @param type
     */
    public static <T> void addObjectDialog(Class<T> type)
    {
        T[] objs = null;
        try
        {
            // TODO[uglyness]: This should really be done in the controller
            // rather than in the view
            objs = ControllerServiceClient.getController().getAllObjects(type);
        } catch (RemoteException ex)
        {
            Logger.getLogger(MetaWidgetUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        JDialog f = new JDialog();
        f.setModal(true);
        f.getContentPane().add(createObjectListWidget(objs));
        f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        f.setSize(400, 250);
        f.setVisible(true);
    }
}
