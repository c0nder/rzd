package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.CarEntity;
import org.company.app.database.entity.ScheduleEntity;
import org.company.app.database.manager.CarEntityManager;
import org.company.app.database.manager.ScheduleEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class FormCreateCar extends BaseSubForm<FormCar> {
    private JTextField FieldCapacity;
    private JTextField FieldNumber;
    private JComboBox ComboBoxSchedule;
    private JButton ButtonClose;
    private JButton ButtonCreate;
    private JPanel FormCreateCar;

    private final ScheduleEntityManager scheduleEntityManager = Application.getScheduleEntityManager();
    private final CarEntityManager carEntityManager = Application.getCarEntityManager();

    public FormCreateCar(FormCar mainForm) {
        super(mainForm);

        initButtons();
        initComboBox();

        setContentPane(FormCreateCar);
        setVisible(true);
    }

    public void initComboBox() {
        List<ScheduleEntity> scheduleEntityList = null;
        try {
            scheduleEntityList = scheduleEntityManager.getAll();

            for (ScheduleEntity scheduleEntity : scheduleEntityList) {
                ComboBoxSchedule.addItem(scheduleEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void initButtons() {
        ButtonClose.addActionListener(e -> {
            closeSubForm();
        });

        ButtonCreate.addActionListener(e -> {
            String capacity = FieldCapacity.getText();
            String number = FieldNumber.getText();
            ScheduleEntity scheduleEntity = (ScheduleEntity) ComboBoxSchedule.getSelectedItem();

            if (capacity.isEmpty() || number.isEmpty()) {
                DialogUtil.showInfo("Заполните все поля");
                return;
            }

            if (!capacity.matches("[0-9]+") || !number.matches("[0-9]+")) {
                DialogUtil.showInfo("Все поля для ввода должны содержать только цифры");
                return;
            }

            try {
                carEntityManager.add(new CarEntity(
                        Integer.parseInt(capacity),
                        scheduleEntity.getId(),
                        Integer.parseInt(number)
                ));

                mainForm.initTable();

                closeSubForm();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
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
