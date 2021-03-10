package org.company.app.database.manager;

import org.company.app.database.entity.UserTicketEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;

public class UserTicketEntityManager {
    private final MysqlDatabase database;

    public UserTicketEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(UserTicketEntity userTicket) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO user_ticket(user_id, seat_id, schedule_id) VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userTicket.getUserId());
            ps.setInt(2, userTicket.getSeatId());
            ps.setInt(3, userTicket.getScheduleId());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                userTicket.setId(resultSet.getInt(1));
            }
        }
    }

    public UserTicketEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM user_ticket WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new UserTicketEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("seat_id"),
                        resultSet.getInt("schedule_id")
                );
            }
        }

        return null;
    }
}
