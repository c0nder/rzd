package org.company.app.database.manager;

import org.company.app.database.entity.UserEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;

public class UserEntityManager {
    private final MysqlDatabase database;

    public UserEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(UserEntity user) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO user(first_name, last_name, passport, password, email, is_admin) VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassport());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setBoolean(6, user.getAdmin());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        }
    }

    public UserEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM user WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("passport"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("is_admin")
                );
            }
        }

        return null;
    }

    public UserEntity getByEmailAndPassword(String email, String password) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("passport"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("is_admin")
                );
            }
        }

        return null;
    }
}
