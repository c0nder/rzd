package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.StationEntity;
import org.company.app.database.entity.TrainEntity;
import org.company.app.database.entity.TrainTypeEntity;
import org.company.app.database.manager.StationEntityManager;
import org.company.app.database.manager.TrainEntityManager;
import org.company.app.database.manager.TrainTypeEntityManager;
import org.company.app.util.BaseComboBoxModel;
import org.company.app.util.BaseSubForm;
import org.company.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class FormCreateTrain extends BaseSubForm<FormTrain> {
    private JComboBox BoxDestStation;
    private JComboBox BoxDepStation;
    private JComboBox BoxTrainType;
    private JButton ButtonClose;
    private JButton ButtonCreate;
    private JPanel FormCreateTrain;

    private final TrainEntityManager trainEntityManager = Application.getTrainEntityManager();
    private final TrainTypeEntityManager trainTypeEntityManager = Application.getTrainTypeEntityManager();
    private final StationEntityManager stationEntityManager = Application.getStationEntityManager();

    public FormCreateTrain(FormTrain mainForm) {
        super(mainForm);

        setContentPane(FormCreateTrain);
        setVisible(true);

        initBoxes();
        initButtons();
    }

    public void initButtons() {
        ButtonClose.addActionListener(e -> {
            closeSubForm();
        });

        ButtonCreate.addActionListener(e -> {
            StationEntity selectedDepartureStation = (StationEntity) BoxDepStation.getSelectedItem();
            StationEntity selectedDestinationStation = (StationEntity) BoxDestStation.getSelectedItem();
            TrainTypeEntity selectedTrainType = (TrainTypeEntity) BoxTrainType.getSelectedItem();

            if (selectedDepartureStation == selectedDestinationStation) {
                DialogUtil.showInfo("Станции отправления и прибытия не должны совпадать");
                return;
            }

            try {
                trainEntityManager.add(new TrainEntity(
                        selectedDepartureStation.getId(),
                        selectedDestinationStation.getId(),
                        selectedTrainType.getId()
                ));

                mainForm.initTable();
                closeSubForm();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void initBoxes() {
        try {
            List<TrainTypeEntity> trainTypesList = trainTypeEntityManager.getAll();

            for (TrainTypeEntity trainTypeEntity : trainTypesList) {
                BoxTrainType.addItem(trainTypeEntity);
            }

            List<StationEntity> stationEntityList = stationEntityManager.getAll();

            for (StationEntity stationEntity : stationEntityList) {
                BoxDestStation.addItem(stationEntity);
                BoxDepStation.addItem(stationEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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
