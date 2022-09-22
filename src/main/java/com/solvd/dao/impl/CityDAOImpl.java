package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.CityDAO;
import com.solvd.model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements CityDAO {

    private static final Logger LOGGER = LogManager.getLogger(CityDAOImpl.class);

    private static final String INSERT = "INSERT INTO city " +
            "(city.city_name, " +
            "city.country_id) " +
            "VALUES (?, ?)";

    private static final String UPDATE = "UPDATE city " +
            "SET city.city_name=?, " +
            "city.country_id=? " +
            "WHERE city.city_id=?";

    private static final String DELETE = "DELETE FROM city WHERE city_id=?";

    private static final String GET_BY_ID = "SELECT * FROM city WHERE city_id=?";

    private static final String GET_ALL = "SELECT * FROM city";

    @Override
    public void add(City city) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, city.getName());
            ps.setInt(2, city.getCountryId());
            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            LOGGER.info("id: " + id + " object: " + city);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void update(City updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, updated.getName());
            ps.setInt(2, updated.getCountryId());
            ps.setInt(3, updated.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public City getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("city_id"));
                city.setName(rs.getString("city_name"));
                city.setCountryId(rs.getInt("country_id"));
                return city;
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public List<City> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<City> cities = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("city_id"));
                city.setName(rs.getString("city_name"));
                city.setCountryId(rs.getInt("country_id"));
                cities.add(city);
            }
            return cities;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
