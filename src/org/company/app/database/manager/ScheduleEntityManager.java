package org.company.app.database.manager;

import org.company.app.Application;
import org.company.app.database.entity.ScheduleEntity;
import org.company.app.database.entity.StationEntity;
import org.company.app.database.entity.TrainEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleEntityManager {
    private final MysqlDatabase database;

    private final TrainEntityManager trainEntityManager = Application.getInstance().getTrainEntityManager();
    private final StationEntityManager stationEntityManager = Application.getInstance().getStationEntityManager();

    public ScheduleEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(ScheduleEntity schedule) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO schedule(departure, arrival, train_id) VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(schedule.getDeparture().getTime()));
            ps.setTimestamp(2, new Timestamp(schedule.getArrival().getTime()));
            ps.setInt(3, schedule.getTrainId());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                schedule.setId(resultSet.getInt(1));
            }
        }
    }

    public ScheduleEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM schedule WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new ScheduleEntity(
                        resultSet.getInt("id"),
                        resultSet.getDate("departure"),
                        resultSet.getDate("arrival"),
                        resultSet.getInt("train_id")
                );
            }
        }

        return null;
    }

    public List<ScheduleEntity> getAll() throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM schedule";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<ScheduleEntity> schedules = new ArrayList<>();
            while(resultSet.next()) {
                int trainId = resultSet.getInt("train_id");

                TrainEntity train = trainEntityManager.getById(trainId);

                if (train == null) {
                    throw new SQLException("Train with this id not found");
                }

                StationEntity departureStation = stationEntityManager.getById(train.getDepartureStationId());
                StationEntity destinationStation = stationEntityManager.getById(train.getDestinationStationId());

                if (departureStation == null || destinationStation == null) {
                    throw new SQLException("Departure station or destination station not found");
                }

                ScheduleEntity scheduleEntity = new ScheduleEntity(
                        resultSet.getInt("id"),
                        resultSet.getDate("departure"),
                        resultSet.getDate("arrival"),
                        resultSet.getInt("train_id")
                );

                scheduleEntity.setDepartureStationName(departureStation.getName());
                scheduleEntity.setDestinationStationName(destinationStation.getName());

                schedules.add(scheduleEntity);
            }

            return schedules;
        }
    }
}
