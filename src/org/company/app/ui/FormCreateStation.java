package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.CityEntity;
import org.company.app.database.entity.StationEntity;
import org.company.app.database.manager.CityEntityManager;
import org.company.app.database.manager.StationEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class FormCreateStation extends BaseSubForm<FormStation> {
    private JTextField FieldName;
    private JComboBox ComboBoxCity;
    private JButton ButtonClose;
    private JButton ButtonCreate;
    private JPanel PanelCreateStation;

    private final CityEntityManager cityEntityManager = Application.getCityEntityManager();
    private final StationEntityManager stationEntityManager = Application.getStationEntityManager();

    public FormCreateStation(FormStation mainForm) {
        super(mainForm);

        initComboBox();
        initButtons();

        setContentPane(PanelCreateStation);
        setVisible(true);
    }

    public void initComboBox() {
        try {
            List<CityEntity> cityEntities = cityEntityManager.getAll();

            for (CityEntity cityEntity : cityEntities) {
                ComboBoxCity.addItem(cityEntity);
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
            String name = FieldName.getText();
            CityEntity cityEntity = (CityEntity) ComboBoxCity.getSelectedItem();

            if (name.isEmpty()) {
                DialogUtil.showInfo("Все поля для ввода должны быть заполнены");
                return;
            }

            try {
                stationEntityManager.add(new StationEntity(
                        cityEntity.getId(),
                        name
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
