package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.CarEntity;
import org.company.app.database.entity.StationEntity;
import org.company.app.database.manager.StationEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.CustomTableModel;

import javax.swing.*;
import java.sql.SQLException;

public class FormStation extends BaseSubForm<MainForm> {
    private JTable TableStation;
    private JPanel PanelStation;
    private JButton ButtonClose;
    private JButton ButtonCreate;

    private final StationEntityManager stationEntityManager = Application.getStationEntityManager();

    private final String[] tableColumns = {
            ""
    };

    public FormStation(MainForm mainForm) {
        super(mainForm);

        initButtons();
        initTable();

        setContentPane(PanelStation);
        setVisible(true);
    }

    public void initTable() {
        TableStation.getTableHeader().setReorderingAllowed(false);
        TableStation.setRowHeight(28);

        try {
            CustomTableModel<StationEntity> model = new CustomTableModel<>(
                    StationEntity.class,
                    tableColumns,
                    stationEntityManager.getAll()
            );

            TableStation.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initButtons() {
        ButtonClose.addActionListener(e -> {
            closeSubForm();
        });

        ButtonCreate.addActionListener(e -> {
            dispose();

            new FormCreateStation(this);
        });
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
