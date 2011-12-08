/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import client.ControllerServiceClient;
import emalaedesktopapplication.forms.admin.ManyToManySelectorPanel;
import java.awt.GridBagConstraints;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.metawidget.inspector.InspectionResultConstants.*;

import java.awt.GridBagLayout;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;

import javax.swing.*;

import org.metawidget.swing.SwingMetawidget;
import org.metawidget.util.*;
import org.metawidget.widgetbuilder.iface.WidgetBuilder;
import org.w3c.dom.*;

public class CollectionWidgetBuilderEditable implements
        WidgetBuilder<JComponent, SwingMetawidget>
{

    public JComponent buildWidget(String elementName,
            Map<String, String> attributes, SwingMetawidget metawidget)
    {

        // Not for us?

        if (TRUE.equals(attributes.get(HIDDEN))
                || attributes.containsKey(LOOKUP))
        {
            return null;
        }

        String type = attributes.get(TYPE);

        if (type == null || "".equals(type))
        {
            return null;
        }

        final Class<?> clazz = ClassUtils.niceForName(type);

        if (clazz == null)
        {
            return null;
        }

        if (!Collection.class.isAssignableFrom(clazz))
        {
            return null;
        }

        // Inspect type of List

        String componentType = attributes.get(PARAMETERIZED_TYPE);
        String inspectedType = metawidget.inspect(null, componentType,
                (String[]) null);

        // Determine columns

        List<String> columns = CollectionUtils.newArrayList();
        Element root = XmlUtils.documentFromString(inspectedType).getDocumentElement();
        NodeList elements = root.getFirstChild().getChildNodes();

        for (int loop = 0, length = elements.getLength(); loop < length; loop++)
        {

            Node node = elements.item(loop);
            Map<String, String> attributesAsMap;
            if (!"action".equals(node.getNodeName()))
            {
                /*
                columns.add(metawidget.getLabelString(XmlUtils
                .getAttributesAsMap(node)));
                 */
                attributesAsMap = XmlUtils.getAttributesAsMap(node);
                columns.add(attributesAsMap.get("name"));
            }
        }

        // Fetch the data. This part could be improved to use BeansBinding or
        // similar

        List<?> list = new ArrayList();
        Method m = null;
        Type t = null;

        /*
         * Workarounding metawidget bug, see:
         * http://sourceforge.net/projects/metawidget/forums/forum/747623/topic/4707912 (upstreams)
         * http://code.google.com/p/ema-lae/issues/detail?id=35
         */
        String mPath =  metawidget.getPath(); // e.g. database.entity.UserProfile/user
        if (!mPath.contains("/"))
        {
            list = (List<?>) ClassUtils.getProperty(
                    metawidget.getToInspect(), attributes.get(NAME));
            m = ClassUtils.getReadMethod(metawidget.getToInspect().getClass(), attributes.get(NAME));
            t = m.getGenericReturnType();
        }

        final Class<?> elementType;
        if (t instanceof ParameterizedType)
        {
            ParameterizedType pt = (ParameterizedType) t;
            elementType = (Class<?>) pt.getActualTypeArguments()[0];
        } else
        {
            elementType = Object.class;
        }

        // @SuppressWarnings("unchecked")
        // ListTableModel<?> tableModel = new ListTableModel(list, columns);

        // Return the JTable
        @SuppressWarnings({"unchecked", "rawtypes"})
        final ListTableModelEditable<?> tableModel =
                new ListTableModelEditable(
                elementType,
                list, // new ArrayList(list),
                columns // columns.toArray(new String[] {})
                );
        JPanel panel = new JPanel();
        // final JTable table = new JTable(tableModel);
        Object[] allObjects = null;
        ManyToManySelectorPanel table =
                new ManyToManySelectorPanel();
        try
        {
            allObjects = ControllerServiceClient.getController().getAllObjects(elementType);
        } catch (RemoteException ex)
        {
            Logger.getLogger(CollectionWidgetBuilderEditable.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setSelectedObjectsListFromObjects(list.toArray());
        table.setAllObjectsListFromObjects(allObjects);

        JScrollPane jScrollPane = new JScrollPane(table);
        /*
        table.addMouseListener(new MouseListener()
        {

            public void mouseReleased(MouseEvent e)
            {
                // TODO Auto-generated method stub
            }

            public void mousePressed(MouseEvent e)
            {
                // TODO Auto-generated method stub
            }

            public void mouseExited(MouseEvent e)
            {
                // TODO Auto-generated method stub
            }

            public void mouseEntered(MouseEvent e)
            {
                // TODO Auto-generated method stub
            }

            public void mouseClicked(MouseEvent e)
            {
                Object obj = tableModel.getValueAt(table.getSelectedRow());
                MetaWidgetUtils.createEditDialog(obj);
            }
        });
         */

        // Adding add/delete buttons and constrains
        panel.setLayout(new GridBagLayout());
        panel.add(jScrollPane);
        JButton buttonAdd = new JButton("Add");
        JButton buttonDelete = new JButton("Delete");
        /*
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 0;
        gBC.gridy = 1;
        gBC.gridwidth = 2;
        panel.add(jScrollPane, gBC);
        gBC.anchor = GridBagConstraints.PAGE_START;
        gBC.weightx = 0.5;
        gBC.gridx = 0;
        gBC.gridy = 0;
        gBC.gridwidth = 1;
        panel.add(buttonAdd, gBC);
        gBC.gridx = 1;
        panel.add(buttonDelete, gBC);
         */
        buttonAdd.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println("TODO: show the collection selector");
                MetaWidgetUtils.addObjectDialog(elementType);
            }
        });
        buttonDelete.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println("TODO: delete the selected object");
            }
        });

        // return jScrollPane;
        return new JScrollPane(panel);
        // return panel;
    }
//      public SwingMetawidget createMetaWidget(Object obj) {
//              SwingMetawidget metawidget = new SwingMetawidget();
//              CompositeInspectorConfig inspectorConfig = new CompositeInspectorConfig()
//                              .setInspectors(new PropertyTypeInspector(),
//                                              new MetawidgetAnnotationInspector(),
//                                              new Java5Inspector());
//              metawidget.setInspector(new CompositeInspector(inspectorConfig));
//
//              metawidget.setToInspect(obj);
//              return metawidget;
//      }
}
