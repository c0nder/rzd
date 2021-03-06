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
    private static final UserEntityManager userEntityManager = new UserEntityManager(database);
    private static final UserTicketEntityManager userTicketEntityManager = new UserTicketEntityManager(database);
    private static final TrainTypeEntityManager trainTypeEntityManager = new TrainTypeEntityManager(database);
    private static final CityEntityManager cityEntityManager = new CityEntityManager(database);
    private static final CarEntityManager carEntityManager = new CarEntityManager(database);
    private static final SeatEntityManager seatEntityManager = new SeatEntityManager(database);

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

    public static UserEntityManager getUserEntityManager() {
        return userEntityManager;
    }

    public static TrainTypeEntityManager getTrainTypeEntityManager() {
        return trainTypeEntityManager;
    }

    public static CarEntityManager getCarEntityManager() {
        return carEntityManager;
    }

    public static SeatEntityManager getSeatEntityManager() {
        return seatEntityManager;
    }

    public static UserTicketEntityManager getUserTicketEntityManager() {
        return userTicketEntityManager;
    }

    public static Registry getContainer() {
        return container;
    }

    public static CityEntityManager getCityEntityManager() {
        return cityEntityManager;
    }
}
