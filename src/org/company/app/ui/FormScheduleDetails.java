package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.CarEntity;
import org.company.app.database.entity.ScheduleEntity;
import org.company.app.database.entity.SeatEntity;
import org.company.app.database.manager.CarEntityManager;
import org.company.app.database.manager.SeatEntityManager;
import org.company.app.database.manager.ScheduleEntityManager;
import org.company.app.util.CustomTableModel;
import org.company.app.util.BaseSubForm;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FormScheduleDetails extends BaseSubForm<MainForm>  {

    private JPanel panel1;
    private JTable tableSeats;
    private JTable tableCar;
    private JButton backButton;
    private JFrame frame;

    private int scheduleID;
    private int carID;
    private int seatID;

    private final ScheduleEntityManager scheduleEntityManager = Application.getScheduleEntityManager();
    private final CarEntityManager carEntityManager = Application.getCarEntityManager();
    private final SeatEntityManager seatEntityManager = Application.getSeatEntityManager();

    private final String[] tableCarColumns = new String[] {
            "№", "Capacity", "Schedule №", "Number"
    };

    private final String[] tableSeatColumns = new String[] {
            "№", "Car №", "Number", "Price"
    };

    public FormScheduleDetails(MainForm mainForm) {
        super(mainForm);

        setContentPane(panel1);
        setVisible(true);
    }

    public void init(int scheduleID) {
        this.scheduleID = scheduleID;
        this.initCarTable(scheduleID);
        this.setTitle(String.format("№ %d", scheduleID));
        backButton.addActionListener(e -> {
            closeSubForm();
        });
    }

    private void initCarTable(int scheduleID) {
        tableCar.getTableHeader().setReorderingAllowed(false);
        tableCar.setRowHeight(28);

        try {
            CustomTableModel<CarEntity> model = new CustomTableModel<>(
                    CarEntity.class,
                    tableCarColumns,
                    carEntityManager.getByScheduleId(scheduleID)
            );

            tableCar.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tableCar.setFocusable(false);
        tableCar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    selectCar(target);
                }
            }
        });
    }

    private void selectCar(JTable target) {
        // to detect double click events
        int row = target.getSelectedRow(); // select a row
        this.scheduleID = (Integer) tableCar.getModel().getValueAt(row, 0);
        this.initSeatTable((Integer) tableCar.getModel().getValueAt(row, 0));
    }

    private void initSeatTable(int carID) {
        this.carID = carID;
        tableSeats.getTableHeader().setReorderingAllowed(false);
        tableSeats.setRowHeight(28);

        try {
            CustomTableModel<SeatEntity> model = new CustomTableModel<>(
                    SeatEntity.class,
                    tableSeatColumns,
                    seatEntityManager.getByCarId(carID)
            );

            tableSeats.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tableSeats.setFocusable(false);
        tableSeats.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    selectSeat(target);
                }
            }
        });
    }

    private void selectSeat(JTable target) {
        setVisible(false);

        int row = target.getSelectedRow(); // select a row
        this.seatID = (Integer) tableSeats.getModel().getValueAt(row, 0);

        FormClientTicketBuy buyTicket = new FormClientTicketBuy(this);
        buyTicket.init(this.scheduleID, this.carID, this.seatID, (Integer) tableSeats.getModel().getValueAt(row, 3));
    }

    @Override
    public int getFormWidth() {
        return 640;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
