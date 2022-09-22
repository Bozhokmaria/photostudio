package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.PhotographerDAO;
import com.solvd.model.Photographer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotographerDAOImpl implements PhotographerDAO {

    private static final Logger LOGGER = LogManager.getLogger(PhotographerDAOImpl.class);

    private static final String INSERT = "INSERT INTO photographer " +
            "(photographer.ph_first_name, " +
            "photographer.ph_last_name, " +
            "photographer.ph_price) " +
            "VALUES (?,?,?)";

    private static final String UPDATE = "UPDATE photographer " +
            "SET photographer.ph_first_name=?, " +
            "photographer.ph_last_name=?, " +
            "photographer.ph_price=? " +
            "WHERE photographer.ph_id=?";

    private static final String DELETE = "DELETE FROM photographer WHERE ph_id=?";

    private static final String GET_BY_ID = "SELECT * FROM photographer WHERE ph_id=?";

    private static final String GET_ALL = "SELECT * FROM photographer";
    @Override
    public void add(Photographer object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getFirstName());
            ps.setString(2, object.getLastName());
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
    public void update(Photographer updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, updated.getFirstName());
            ps.setString(2, updated.getLastName());
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
    public Photographer getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Photographer photographer = new Photographer();
                photographer.setId(rs.getInt("ph_id"));
                photographer.setFirstName(rs.getString("ph_first_name"));
                photographer.setLastName(rs.getString("ph_last_name"));
                photographer.setPrice(rs.getDouble("ph_price"));
                return photographer;
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
    public List<Photographer> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Photographer> photographers = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Photographer photographer = new Photographer();
                photographer.setId(rs.getInt("ph_id"));
                photographer.setFirstName(rs.getString("ph_first_name"));
                photographer.setLastName(rs.getString("ph_last_name"));
                photographer.setPrice(rs.getDouble("ph_price"));
                photographers.add(photographer);
            }
            return photographers;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
