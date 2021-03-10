package org.company.app.database.manager;

import org.company.app.database.entity.StationEntity;
import org.company.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationEntityManager {
    private final MysqlDatabase database;

    public StationEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(StationEntity station) throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO station(city_id, name) VALUES(?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, station.getCityId());
            ps.setString(2, station.getName());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                station.setId(resultSet.getInt(1));
            }
        }
    }

    public StationEntity getById(int id) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM station WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new StationEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("city_id"),
                        resultSet.getString("name")
                );
            }
        }

        return null;
    }

    public List<StationEntity> getAll() throws SQLException {
        try(Connection connection = database.getConnection()) {
            String sql = """
                    SELECT station.id, station.name, station.city_id, city.name as cityName FROM station
                    LEFT JOIN city ON (station.city_id = city.id)
                    """;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<StationEntity> stationEntities = new ArrayList<>();
            while (resultSet.next()) {
                StationEntity stationEntity = new StationEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("city_id"),
                        resultSet.getString("name")
                );

                stationEntity.setCityName(resultSet.getString("cityName"));
                stationEntities.add(stationEntity);
            }

            return stationEntities;
        }
    }
}
