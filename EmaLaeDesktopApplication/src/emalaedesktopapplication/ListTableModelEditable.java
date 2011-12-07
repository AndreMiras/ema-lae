/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication;

import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

public class ListTableModelEditable<T>// <T extends Comparable<T>>
        extends AbstractTableModel
{

    //
    // Private statics
    //
    private static final long serialVersionUID = 1l;
    //
    // Private members
    //
    private Class<T> mClass;
    private List<T> mList;
    // private String[] mColumns;
    private List<String> mColumns;
    private boolean mEditable;
    private boolean mExtraBlankRow;

    //
    // Constructor
    //
    // public ListTableModelEditable(Class<T> clazz, Collection<T> collection, List<String> columns)
    public ListTableModelEditable(Class<T> clazz, List<T> list, List<String> columns)
    {

        mClass = clazz;
        mList = list;
        mColumns = columns;

        // importCollection(collection);
    }

    //
    // Public methods
    //
    public void importCollection(Collection<T> collection)
    {

        if (collection == null)
        {
            mList = CollectionUtils.newArrayList();
        } else
        {
            mList = CollectionUtils.newArrayList(collection);
            // needs to implement comparable
            // Collections.sort( mList );
        }

        fireTableDataChanged();
    }

    public List<T> exportList()
    {

        return CollectionUtils.newArrayList(mList);
    }

    public void setEditable(boolean editable)
    {

        mEditable = editable;
    }

    public void setExtraBlankRow(boolean extraBlankRow)
    {

        mExtraBlankRow = extraBlankRow;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {

        return mEditable;
    }

    public int getColumnCount()
    {

        // (mColumns can never be null)

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

        // (mList can never be null)

        int rows = mList.size();

        if (mExtraBlankRow)
        {
            rows++;
        }

        return rows;
    }

    // @Override
    public Class<?> getColumnClassDisabled(int columnIndex)
    {

        String column = getColumnName(columnIndex);

        if (column == null)
        {
            return null;
        }

        return ClassUtils.getReadMethod(mClass, column).getReturnType();
    }

    public T getValueAt(int rowIndex)
    {

        // Sanity check

        if (rowIndex >= mList.size())
        {
            return null;
        }

        return mList.get(rowIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {

        // Sanity check

        if (columnIndex >= getColumnCount())
        {
            return null;
        }

        // Fetch the object

        T t = getValueAt(rowIndex);

        if (t == null)
        {
            return null;
        }

        // Inspect it

        return ClassUtils.getProperty(t, getColumnName(columnIndex));
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {

        // Sanity check

        if (columnIndex >= getColumnCount())
        {
            return;
        }

        // Just-in-time creation

        if (rowIndex == (getRowCount() - 1) && mExtraBlankRow)
        {
            if (value == null || "".equals(value))
            {
                return;
            }

            try
            {
                mList.add(mClass.newInstance());
                fireTableRowsInserted(rowIndex, rowIndex);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        // Fetch the object

        T t = getValueAt(rowIndex);

        if (t == null)
        {
            return;
        }

        // Update it

        ClassUtils.setProperty(t, getColumnName(columnIndex), value);
    }
}
