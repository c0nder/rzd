package org.company.app.ui;

import com.mysql.cj.xdevapi.Table;
import org.company.app.Application;
import org.company.app.database.entity.ScheduleEntity;
import org.company.app.database.manager.ScheduleEntityManager;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.util.BaseForm;
import org.company.app.util.CustomTableModel;
import java.awt.event.*;
import javax.swing.*;

import java.sql.SQLException;

public class MainForm extends BaseForm {
    private JPanel MainPanel;
    private JButton ButtonSignIn;
    private JTable TableSchedule;
    private FormScheduleDetails formScheduleDetails;
    private final ScheduleEntityManager scheduleEntityManager = Application.getScheduleEntityManager();

    private final String[] tableColumns = new String[] {
            "№", "Departure date", "Arrival date", "Train", "Departure station", "Destination station", "Train type"
    };

    public MainForm() {
        setContentPane(MainPanel);
        setVisible(true);

        initTable();
        initButtons();
    }

    private void initTable() {
        TableSchedule.getTableHeader().setReorderingAllowed(false);
        TableSchedule.setRowHeight(28);

        try {
            CustomTableModel<ScheduleEntity> model = new CustomTableModel<>(
                    ScheduleEntity.class,
                    tableColumns,
                    scheduleEntityManager.getAll()
            );

            TableSchedule.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TableSchedule.setFocusable(false);
        TableSchedule.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    showFormScheduleDetails(target);
                }
            }
        });
    }

    private void showFormScheduleDetails(JTable target) {
        // to detect double click events
        setVisible(false);
        int row = target.getSelectedRow(); // select a row
        FormScheduleDetails formScheduleDetails = new FormScheduleDetails(this);
        formScheduleDetails.init((Integer) TableSchedule.getModel().getValueAt(row, 0));
//                    int column = target.getSelectedColumn(); // select a column
//                    JOptionPane.showMessageDialog(null, TableSchedule.getValueAt(row, )); // get the value of a row and column.
    }

    private void initButtons() {
        ButtonSignIn.addActionListener(e -> {
            setVisible(false);

            new FormSignIn(this);
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
