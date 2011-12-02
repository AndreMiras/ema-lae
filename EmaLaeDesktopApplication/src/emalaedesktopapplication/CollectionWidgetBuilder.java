/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import static org.metawidget.inspector.InspectionResultConstants.*;

import java.util.*;

import javax.swing.*;

import org.metawidget.swing.*;
import org.metawidget.util.*;
import org.metawidget.widgetbuilder.iface.*;
import org.w3c.dom.*;

/**
 *
 * @author andre
 */
public class CollectionWidgetBuilder
        implements WidgetBuilder<JComponent, SwingMetawidget>
{

    public JComponent buildWidget(String elementName, Map<String, String> attributes, SwingMetawidget metawidget)
    {

        // Not for us?

        if (TRUE.equals(attributes.get(HIDDEN)) || attributes.containsKey(LOOKUP))
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

        if (!List.class.isAssignableFrom(clazz)) // if (!Collection.class.isAssignableFrom(clazz))
        {
            return null;
        }

        // Inspect type of List

        String componentType = attributes.get(PARAMETERIZED_TYPE);
        String inspectedType = metawidget.inspect(null, componentType, (String[]) null);

        // Determine columns

        List<String> columns = CollectionUtils.newArrayList();
        Element root = XmlUtils.documentFromString(inspectedType).getDocumentElement();
        NodeList elements = root.getFirstChild().getChildNodes();

        for (int loop = 0, length = elements.getLength(); loop < length; loop++)
        {

            Node node = elements.item(loop);
            Map<String, String> attributesAsMap = XmlUtils.getAttributesAsMap(node);
            String columnName = attributesAsMap.get("name");
            // columns.add(metawidget.getLabelString(XmlUtils.getAttributesAsMap(node)));
            columns.add(columnName);
        }

        // Fetch the data. This part could be improved to use BeansBinding or similar

        List<?> list = (List<?>) ClassUtils.getProperty(metawidget.getToInspect(), attributes.get(NAME));
        // Return the JTable
        @SuppressWarnings("unchecked")
        ListTableModel<?> tableModel = new ListTableModel(list, columns);

        return new JScrollPane(new JTable(tableModel));
    }
}
