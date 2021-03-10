package org.company.app.util;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomTableModel<T> extends AbstractTableModel
{
    private final Class<T> cls;

    private final String[] columnNames;

    private List<T> values;

    public CustomTableModel(Class<T> cls, String[] columnNames, List<T> values)
    {
        this.values = values;
        this.cls = cls;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return values.size();
    }

    @Override
    public int getColumnCount() {
        return cls.getDeclaredFields().length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return cls.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnIndex >= columnNames.length ? cls.getDeclaredFields()[columnIndex].getName() : columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        try {
            Field field = cls.getDeclaredFields()[columnIndex];

            field.setAccessible(true);

            return field.get(values.get(rowIndex));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public void sort(Comparator<T> comparator) {
        Collections.sort(this.values, comparator);
        fireTableDataChanged();
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
