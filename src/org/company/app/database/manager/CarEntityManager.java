package org.company.app.database.manager;

import org.company.app.Application;
import org.company.app.database.entity.CarEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;

public class CarEntityManager {
    private final MysqlDatabase database;

    public CarEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(CarEntity car) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO car(capacity, schedule_id, number) VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, car.getCapacity());
            ps.setInt(2, car.getScheduleId());
            ps.setInt(3, car.getNumber());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getInt(1));
            }
        }
    }

    public CarEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM car WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new CarEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("capacity"),
                        resultSet.getInt("schedule_id"),
                        resultSet.getInt("number")
                );
            }
        }

        return null;
    }
}
