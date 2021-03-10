package org.company.app.database.manager;

import org.company.app.database.entity.CarEntity;
import org.company.app.database.entity.CityEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityEntityManager {
    private final MysqlDatabase database;

    public CityEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(CityEntity city) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO city(city_name) VALUES(?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, city.getCityName());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                city.setId(resultSet.getInt(1));
            }
        }
    }

    public CityEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM city WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new CityEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
                );
            }
        }

        return null;
    }

    public List<CityEntity> getAll() throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM city";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<CityEntity> cityEntities = new ArrayList<>();
            while (resultSet.next()) {
                cityEntities.add(new CityEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }

            return cityEntities;
        }
    }
}
