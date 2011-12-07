/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

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

        Class<?> elementType;
        if (t instanceof ParameterizedType)
        {
            ParameterizedType pt = (ParameterizedType) t;
            elementType = (Class<?>) pt.getActualTypeArguments()[0];
        } else
        {
            elementType = Object.class;
        }
        // Return the JTable
        @SuppressWarnings(
        {
            "unchecked", "rawtypes"
        })
        final ListTableModelEditable<?> tableModel =
                new ListTableModelEditable(
                elementType,
                new ArrayList(list), columns.toArray(new String[] { }));
        JPanel panel = new JPanel();
        final JTable table = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        // /*
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

        // panel.setLayout(new GridBagLayout());
        panel.add(jScrollPane);
        /*
        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
            }
        });
         */

        return jScrollPane;
        // return panel;
        // */
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
