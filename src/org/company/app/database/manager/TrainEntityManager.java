package org.company.app.database.manager;

import org.company.app.database.entity.TrainEntity;
import org.company.app.database.entity.TrainTypeEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainEntityManager {
    private final MysqlDatabase database;

    public TrainEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(TrainEntity train) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO train(departure_station_id, destination_station_id, train_type) VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, train.getDepartureStationId());
            ps.setInt(2, train.getDestinationStationId());
            ps.setInt(3, train.getTrainType());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                train.setId(resultSet.getInt(1));
            }
        }
    }

    public TrainEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM train WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new TrainEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("departure_station_id"),
                        resultSet.getInt("destination_station_id"),
                        resultSet.getInt("train_type")
                );
            }
        }

        return null;
    }

    public List<TrainEntity> getAll() throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = """
                        SELECT t.id, t.departure_station_id, t.destination_station_id, t.train_type, depStation.name AS departureStation, destStation.name AS destinationStation, tt.name AS type
                        FROM train t
                        LEFT JOIN station depStation ON (t.departure_station_id = depStation.id)
                        LEFT JOIN station destStation ON (t.destination_station_id = destStation.id)
                        LEFT JOIN train_type tt ON (t.train_type = tt.id)
                    """;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<TrainEntity> trains = new ArrayList<>();
            while (resultSet.next()) {
                TrainEntity trainEntity = new TrainEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("departure_station_id"),
                        resultSet.getInt("destination_station_id"),
                        resultSet.getInt("train_type")
                );

                trainEntity.setDepartureStationName(resultSet.getString("departureStation"));
                trainEntity.setDestinationStationName(resultSet.getString("destinationStation"));
                trainEntity.setTrainTypeName(resultSet.getString("type"));

                trains.add(trainEntity);
            }

            return trains;
        }
    }
}
