package org.company.app.util;

import javax.swing.*;
import java.util.List;
import java.lang.Object;

public class BaseComboBoxModel<T> extends DefaultComboBoxModel<T> {
    public BaseComboBoxModel(T[] items) {
        super(items);
    }

    @Override
    public T getSelectedItem() {
        Class<?> selected = (Class<?>) super.getSelectedItem();

        return (T)selected;
    }
}
