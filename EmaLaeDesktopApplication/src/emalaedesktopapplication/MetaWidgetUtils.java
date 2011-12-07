/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import emalaedesktopapplication.forms.admin.AdminListChangePanel;
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
        T[] obj = null;
        String[] tests = new String[]{"foo","bar", "foobar"}; // tests

        JDialog f = new JDialog();
        f.setModal(true);
        f.getContentPane().add(createObjectListWidget(tests));
        f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        f.setSize(400, 250);
        f.setVisible(true);
    }
}
