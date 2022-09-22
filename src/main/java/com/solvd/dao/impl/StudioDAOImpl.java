package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.StudioDAO;
import com.solvd.model.Rate;
import com.solvd.model.Studio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudioDAOImpl implements StudioDAO {

    private static final Logger LOGGER = LogManager.getLogger(StudioDAOImpl.class);

    private static final String INSERT = "INSERT INTO studio " +
            "(studio_ph_st_id, " +
            "studio_name, " +
            "studio_price ) " +
            "VALUES (?,?,?)";

    private static final String UPDATE = "UPDATE studio " +
            "SET studio_ph_st_id=?, " +
            "studio_name=?, " +
            "studio_price=?, " +
            "WHERE studio_id=?";

    private static final String DELETE = "DELETE FROM studio WHERE studio_id=?";

    private static final String GET_BY_ID = "SELECT * FROM studio WHERE studio_id=?";

    private static final String GET_ALL = "SELECT * FROM studio";

    @Override
    public void add(Studio object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getPhotostudioId());
            ps.setString(2, object.getName());
            ps.setDouble(3, object.getPrice());

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
    public void update(Studio updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, updated.getPhotostudioId());
            ps.setString(2, updated.getName());
            ps.setDouble(3, updated.getPrice());
            ps.setInt(4, updated.getId());

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
    public Studio getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Studio studio = new Studio();
                studio.setId(rs.getInt("studio_id"));
                studio.setPhotostudioId(rs.getInt("studio_ph_st_id"));
                studio.setName(rs.getString("studio_name"));
                studio.setPrice(rs.getDouble("studio_price"));
                return studio;
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
    public List<Studio> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Studio> studios = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Studio studio = new Studio();
                studio.setId(rs.getInt("studio_id"));
                studio.setPhotostudioId(rs.getInt("studio_ph_st_id"));
                studio.setName(rs.getString("studio_name"));
                studio.setPrice(rs.getDouble("studio_price"));
                studios.add(studio);
            }
            return studios;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
