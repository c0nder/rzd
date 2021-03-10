package org.company.app.database.manager;

import org.company.app.database.entity.TrainTypeEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;

public class TrainTypeEntityManager {
    private final MysqlDatabase database;

    public TrainTypeEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(TrainTypeEntity trainType) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO train_type(name) VALUES(?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, trainType.getName());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                trainType.setId(resultSet.getInt(1));
            }
        }
    }

    public TrainTypeEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM train_type WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new TrainTypeEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        }

        return null;
    }
}
