/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.metawidget.util.ClassUtils;

/**
 *
 * @author andre
 */
class ListTableModel<T> extends AbstractTableModel
{

    private List<T> mList;
    private List<String> mColumns;

    public ListTableModel(List<T> list, List<String> columns)
    {

        mList = list;
        mColumns = columns;
    }

    public int getColumnCount()
    {

        return mColumns.size();
    }

    @Override
    public String getColumnName(int columnIndex)
    {

        if (columnIndex >= getColumnCount())
        {
            return null;
        }

        return mColumns.get(columnIndex);
    }

    public int getRowCount()
    {

        return mList.size();
    }

    public T getValueAt(int rowIndex)
    {

        if (rowIndex >= getRowCount())
        {
            return null;
        }

        return mList.get(rowIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {

        if (columnIndex >= getColumnCount())
        {
            return null;
        }

        T t = getValueAt(rowIndex);

        if (t == null)
        {
            return null;
        }

        return ClassUtils.getProperty(t, getColumnName(columnIndex));
    }
}
