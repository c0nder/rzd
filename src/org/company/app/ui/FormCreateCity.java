package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.CityEntity;
import org.company.app.database.manager.CityEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class FormCreateCity extends BaseSubForm<FormCity> {
    private JTextField FieldCityName;
    private JButton ButtonClose;
    private JButton ButtonCreate;
    private JPanel FormCityAdd;

    private final CityEntityManager cityEntityManager = Application.getCityEntityManager();

    public FormCreateCity(FormCity mainForm) {
        super(mainForm);

        initButtons();

        setContentPane(FormCityAdd);
        setVisible(true);
    }

    public void initButtons() {
        ButtonClose.addActionListener(e -> {
            closeSubForm();
        });

        ButtonCreate.addActionListener(e -> {
            String cityName = FieldCityName.getText();

            if (cityName.isEmpty()) {
                DialogUtil.showInfo("Заполните все поля");
                return;
            }

            try {
                cityEntityManager.add(new CityEntity(cityName));

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
