/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import client.ControllerServiceClient;
import emalaedesktopapplication.forms.admin.ManyToManySelectorPanel;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.metawidget.inspector.InspectionResultConstants.*;

import java.awt.GridBagLayout;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

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

        JPanel panel = new JPanel();
        // final JTable table = new JTable(tableModel);
        List<?> allObjects = new ArrayList();
        try
        {
            allObjects = new ArrayList(Arrays.asList(ControllerServiceClient.getController().getAllObjects(elementType)));
        } catch (RemoteException ex)
        {
            Logger.getLogger(CollectionWidgetBuilderEditable.class.getName()).log(Level.SEVERE, null, ex);
        }
        ManyToManySelectorPanel<?> manyToManySelectorPanel =
                new ManyToManySelectorPanel(elementType, allObjects, list);
        manyToManySelectorPanel.addWidgetUpdatedListener(
                new ManyToManyWidgetDataListener(
                    elementType, list, manyToManySelectorPanel));
        JScrollPane jScrollPane = new JScrollPane(manyToManySelectorPanel);

        // Adding add/delete buttons and constrains
        panel.setLayout(new GridBagLayout());
        panel.add(jScrollPane);


        // return jScrollPane;
        return new JScrollPane(panel);
        // return panel;
    }

    // ManyToManySelector listener
        class ManyToManyWidgetDataListener<T> implements ListDataListener
        {
            private Class<T> type;
            private List<T> listToUpdate;
            private ManyToManySelectorPanel manyToManySelectorPanel;

            public ManyToManyWidgetDataListener(
                    Class<T> type, List<T> listToUpdate,
                    ManyToManySelectorPanel manyToManySelectorPanel)
            {
                this.type = type;
                this.listToUpdate = listToUpdate;
                this.manyToManySelectorPanel = manyToManySelectorPanel;
            }
            // This method is called when new items have been added to the list
            public void intervalAdded(ListDataEvent evt)
            {
                // update with items
                listToUpdate.clear();
                List<?> selectedObjects =
                        manyToManySelectorPanel.getSelectedObjects();
                listToUpdate.addAll((Collection<? extends T>) selectedObjects);
            }

            // This method is called when items have been removed from the list
            public void intervalRemoved(ListDataEvent evt)
            {
                // Get range of removed items
                intervalAdded(evt);
            }

            // This method is called when items in the list are replaced
            public void contentsChanged(ListDataEvent evt)
            {
                DefaultListModel model = (DefaultListModel) evt.getSource();

                // Get range of changed items
                int start = evt.getIndex0();
                int end = evt.getIndex1();
                int count = end - start + 1;

                // Get changed items
                for (int i = start; i <= end; i++)
                {
                    Object item = model.getElementAt(i);
                }
            }
        };
}
