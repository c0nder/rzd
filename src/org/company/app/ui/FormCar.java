package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.CarEntity;
import org.company.app.database.entity.CityEntity;
import org.company.app.database.manager.CarEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.CustomTableModel;

import javax.swing.*;
import java.sql.SQLException;

public class FormCar extends BaseSubForm<MainForm> {
    private JTable TableCar;
    private JPanel PanelCar;
    private JButton ButtonCreateCar;
    private JButton ButtonClose;

    private CarEntityManager carEntityManager = Application.getCarEntityManager();

    private final String[] tableColumns = {
            ""
    };

    public FormCar(MainForm mainForm) {
        super(mainForm);

        initTable();
        initButtons();

        setContentPane(PanelCar);
        setVisible(true);
    }

    public void initButtons() {
        ButtonClose.addActionListener(e -> {
            closeSubForm();
        });

        ButtonCreateCar.addActionListener(e -> {
            dispose();

            new FormCreateCar(this);
        });
    }

    public void initTable() {
        TableCar.getTableHeader().setReorderingAllowed(false);
        TableCar.setRowHeight(28);

        try {
            CustomTableModel<CarEntity> model = new CustomTableModel<>(
                    CarEntity.class,
                    tableColumns,
                    carEntityManager.getAll()
            );

            TableCar.setModel(model);
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
