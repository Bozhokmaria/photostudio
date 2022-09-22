package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.LocationDAO;
import com.solvd.model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAOImpl implements LocationDAO {

    private static final Logger LOGGER = LogManager.getLogger(LocationDAOImpl.class);

    private static final String INSERT = "INSERT INTO location " +
            "(location.loc_address_line, " +
            "location.loc_city_id) " +
            "VALUES (?,?)";

    private static final String UPDATE = "UPDATE location " +
            "SET location.loc_address_line=?, " +
            "location.loc_city_id=? " +
            "WHERE location.loc_id=?";

    private static final String DELETE = "DELETE FROM location WHERE loc_id=?";

    private static final String GET_BY_ID = "SELECT * FROM location WHERE loc_id=?";

    private static final String GET_ALL = "SELECT * FROM location";
    @Override
    public void add(Location object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getAddress());
            ps.setInt(2, object.getCityId());

            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            LOGGER.info("id: " + id + " object: " + object);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void update(Location updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, updated.getAddress());
            ps.setInt(2, updated.getCityId());
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
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Location getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Location location = new Location();
                location.setId(rs.getInt("loc_id"));
                location.setAddress(rs.getString("loc_address_line"));
                location.setCityId(rs.getInt("loc_city_id"));
                return location;
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
    public List<Location> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Location> locations = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Location location = new Location();
                location.setId(rs.getInt("loc_id"));
                location.setAddress(rs.getString("loc_address_line"));
                location.setCityId(rs.getInt("loc_city_id"));
                locations.add(location);
            }
            return locations;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
