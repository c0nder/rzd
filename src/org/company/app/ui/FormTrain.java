package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.TrainEntity;
import org.company.app.database.manager.TrainEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.CustomTableModel;

import javax.swing.*;
import java.sql.SQLException;

public class FormTrain extends BaseSubForm<MainForm> {
    private JPanel FormTrain;
    public JTable TableTrain;
    private JButton ButtonCreateTrain;
    private JButton ButtonClose;

    private final TrainEntityManager trainEntityManager = Application.getTrainEntityManager();

    private String[] tableColumns = new String[] {
            ""
    };

    public FormTrain(MainForm mainForm) {
        super(mainForm);

        setContentPane(FormTrain);
        setVisible(true);

        initButtons();
        initTable();
    }

    public void initButtons() {
        ButtonClose.addActionListener(e -> {
            closeSubForm();
        });

        ButtonCreateTrain.addActionListener(e -> {
            dispose();

            new FormCreateTrain(this);
        });
    }

    public void initTable() {
        TableTrain.getTableHeader().setReorderingAllowed(false);
        TableTrain.setRowHeight(28);

        try {
            CustomTableModel<TrainEntity> model = new CustomTableModel<>(
                    TrainEntity.class,
                    tableColumns,
                    trainEntityManager.getAll()
            );

            TableTrain.setModel(model);
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
