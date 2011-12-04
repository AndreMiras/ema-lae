/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import javax.swing.JDialog;

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
}
