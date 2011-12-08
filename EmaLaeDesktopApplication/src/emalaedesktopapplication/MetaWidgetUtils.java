/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import client.ControllerServiceClient;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        final JList objectList = new JList(objs);

        /**
         * Listens to the double click event
         */
        MouseListener keyListener = new MouseListener()
        {

            public void mouseClicked(MouseEvent me)
            {
                Object obj = null;
                if (me.getClickCount() == 2)
                {
                    int index = objectList.locationToIndex(me.getPoint());
                    obj = objectList.getSelectedValue();
                }
            }

            public void mousePressed(MouseEvent me)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseReleased(MouseEvent me)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseEntered(MouseEvent me)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseExited(MouseEvent me)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        objectList.addMouseListener(keyListener);

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
