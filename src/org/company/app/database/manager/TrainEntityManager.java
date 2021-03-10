package org.company.app.database.manager;

import org.company.app.database.entity.TrainEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;

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
}
