package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.UserEntity;
import org.company.app.database.entity.UserTicketEntity;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.database.manager.UserTicketEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.ui.MainForm;
import org.company.app.util.DialogUtil;
import org.company.app.util.Registry;

import javax.swing.*;
import java.sql.SQLException;

public class FormClientTicketBuy extends BaseSubForm<FormScheduleDetails> {
    private JLabel pricePane;
    private JButton cancelButton;
    private JButton buyButton;
    private JPanel mainPanel;

    private int scheduleID;
    private int carID;
    private int seatID;

    private final UserTicketEntityManager userTicketEntityManager = Application.getUserTicketEntityManager();
    private final Registry container =  Application.getContainer();

    public FormClientTicketBuy(FormScheduleDetails mainForm) {
        super(mainForm);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void init(int scheduleID, int carID,int  seatID , int price){
        this.scheduleID = scheduleID;
        this.carID = carID;
        this.seatID = seatID;
        cancelButton.addActionListener(e -> {
            closeSubForm();
        });
        buyButton.addActionListener(e -> {
            this.buyTicket();
        });
        pricePane.setText(String.format("Price: %d", price));
    }


    public void buyTicket() {
        UserEntity userEntity = (UserEntity) container.get("user");
        try {
        userTicketEntityManager.add(
                new UserTicketEntity(
                        userEntity.getId(), this.seatID,
                        this.scheduleID));
            closeSubForm();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        DialogUtil.showInfo("Вы успешно купили билет!");
    }
    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 200;
    }
}
