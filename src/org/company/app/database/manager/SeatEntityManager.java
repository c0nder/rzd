package org.company.app.database.manager;

import org.company.app.database.entity.SeatEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;

public class SeatEntityManager {
    private final MysqlDatabase database;

    public SeatEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(SeatEntity seat) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO seat(car_id, number, price) VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, seat.getCarId());
            ps.setInt(2, seat.getNumber());
            ps.setInt(3, seat.getPrice());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                seat.setId(resultSet.getInt(1));
            }
        }
    }

    public SeatEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM seat WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new SeatEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("car_id"),
                        resultSet.getInt("number"),
                        resultSet.getInt("price")
                );
            }
        }

        return null;
    }
}
