package org.company.app.ui;

import com.mysql.cj.xdevapi.Table;
import org.company.app.Application;
import org.company.app.database.entity.ScheduleEntity;
import org.company.app.database.entity.UserEntity;
import org.company.app.database.manager.ScheduleEntityManager;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.util.BaseForm;
import org.company.app.util.CustomTableModel;
import org.company.app.util.Registry;

import java.awt.event.*;
import javax.swing.*;

import java.sql.SQLException;

public class MainForm extends BaseForm {
    private JPanel MainPanel;
    private JButton ButtonSignIn;
    private JTable TableSchedule;
    private JButton ButtonCreateSchedule;
    private JButton ButtonSignUp;
    private JButton ButtonLogOut;
    private JButton ButtonCity;
    private JButton ButtonTrain;
    private JButton ButtonCar;
    private JButton ButtonStation;
    private JPanel PannelLoggedIn;
    private JPanel PannelNotLoggedIn;
    private FormScheduleDetails formScheduleDetails;

    private final ScheduleEntityManager scheduleEntityManager = Application.getScheduleEntityManager();
    private final Registry container =  Application.getContainer();

    private final String[] tableColumns = new String[] {
            "№", "Departure date", "Arrival date", "Train", "Departure station", "Destination station", "Train type"
    };

    public MainForm() {
        initTable();
        initButtons();
        initPanels();

        setContentPane(MainPanel);
        setVisible(true);
    }

    public void initPanels() {
        hidePanelLoggedIn();
    }

    public void hideAdminButtons() {
        ButtonCreateSchedule.setVisible(false);
        ButtonCity.setVisible(false);
        ButtonTrain.setVisible(false);
        ButtonCar.setVisible(false);
        ButtonStation.setVisible(false);
    }

    public void showAdminButtons() {
        ButtonCreateSchedule.setVisible(true);
        ButtonCity.setVisible(true);
        ButtonTrain.setVisible(true);
        ButtonCar.setVisible(true);
        ButtonStation.setVisible(true);
    }

    public void hidePanelNotLoggedIn() {
        PannelNotLoggedIn.setVisible(false);
    }

    public void showPanelLoggedIn() {
        PannelLoggedIn.setVisible(true);

        UserEntity userEntity = (UserEntity) container.get("user");
        if (userEntity != null) {
            if (userEntity.getAdmin()) {
                showAdminButtons();
            } else {
                hideAdminButtons();
            }
        }
    }

    public void hidePanelLoggedIn() {
        PannelLoggedIn.setVisible(false);
    }

    public void showPanelNotLoggedIn() {
        PannelNotLoggedIn.setVisible(true);
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
        setVisible(false);
        int row = target.getSelectedRow();
        FormScheduleDetails formScheduleDetails = new FormScheduleDetails(this);
        formScheduleDetails.init((Integer) TableSchedule.getModel().getValueAt(row, 0));
    }

    private void initButtons() {
        ButtonSignIn.addActionListener(e -> {
            setVisible(false);

            new FormSignIn(this);
        });

        ButtonSignUp.addActionListener(e -> {
            dispose();

            new FormSignUp(this);
        });

        ButtonLogOut.addActionListener(e -> {
            container.set("user", null);

            hidePanelLoggedIn();
            showPanelNotLoggedIn();
        });

        ButtonCity.addActionListener(e -> {
            dispose();

            new FormCity(this);
        });

        ButtonTrain.addActionListener(e -> {
            dispose();

            new FormTrain(this);
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
