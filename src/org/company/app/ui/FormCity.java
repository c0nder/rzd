package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.CityEntity;
import org.company.app.database.manager.CityEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.CustomTableModel;

import javax.swing.*;
import java.sql.SQLException;

public class FormCity extends BaseSubForm<MainForm> {
    private JTable TableCity;
    private JPanel FormCity;
    private JButton ButtonCreateCity;
    private JButton ButtonClose;

    private final String[] tableColumns = {
            ""
    };

    private final CityEntityManager cityEntityManager = Application.getCityEntityManager();

    public FormCity(MainForm mainForm) {
        super(mainForm);

        setContentPane(FormCity);
        setVisible(true);

        initTable();
        initButtons();
    }

    public void initButtons() {
        ButtonClose.addActionListener(e -> {
            closeSubForm();
        });
    }

    public void initTable() {
        TableCity.getTableHeader().setReorderingAllowed(false);
        TableCity.setRowHeight(28);

        try {
            CustomTableModel<CityEntity> model = new CustomTableModel<>(
                    CityEntity.class,
                    tableColumns,
                    cityEntityManager.getAll()
            );

            TableCity.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
