package org.company.app;

import org.company.app.database.entity.CityEntity;
import org.company.app.database.entity.UserEntity;
import org.company.app.database.manager.*;
import org.company.app.ui.MainForm;
import org.company.app.util.MysqlDatabase;
import org.company.app.util.Registry;

import java.sql.SQLException;

public class Application {
    private static Application instance;

    private static final MysqlDatabase database = new MysqlDatabase("127.0.0.1", "rzd", "root", "Sokolov2015");
    private static final TrainEntityManager trainEntityManager = new TrainEntityManager(database);
    private static final StationEntityManager stationEntityManager = new StationEntityManager(database);
    private static final ScheduleEntityManager scheduleEntityManager = new ScheduleEntityManager(database);

    public static final Registry container = Registry.getInstance();

    private Application() {
        instance = this;

        new MainForm();
    }

    public static void main(String[] args) {
        new Application();
    }

    public static Application getInstance() {
        return instance;
    }

    public static ScheduleEntityManager getScheduleEntityManager() {
        return scheduleEntityManager;
    }

    public static TrainEntityManager getTrainEntityManager() {
        return trainEntityManager;
    }

    public static StationEntityManager getStationEntityManager() {
        return stationEntityManager;
    }

    public static Registry getContainer() {
        return container;
    }
}
