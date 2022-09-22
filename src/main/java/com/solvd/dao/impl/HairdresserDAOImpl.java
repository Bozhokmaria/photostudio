package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.HairdresserDAO;
import com.solvd.model.Hairdresser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HairdresserDAOImpl implements HairdresserDAO {
    private static final Logger LOGGER = LogManager.getLogger(LocationDAOImpl.class);

    private static final String INSERT = "INSERT INTO hairdresser " +
            "(hairdresser.hd_first_name, " +
            "hairdresser.hd_last_name, " +
            "hairdresser.hd_price) " +
            "VALUES (?,?,?)";

    private static final String UPDATE = "UPDATE hairdresser " +
            "SET hairdresser.hd_first_name=?, " +
            "hairdresser.hd_last_name=?, " +
            "hairdresser.hd_price=? " +
            "WHERE hairdresser.hd_id=?";

    private static final String DELETE = "DELETE FROM hairdresser WHERE hd_id=?";

    private static final String GET_BY_ID = "SELECT * FROM hairdresser WHERE hd_id=?";

    private static final String GET_ALL = "SELECT * FROM hairdresser";

    @Override
    public void add(Hairdresser object) {
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
    public void update(Hairdresser updated) {
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
    public Hairdresser getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hairdresser hairdresser = new Hairdresser();
                hairdresser.setId(rs.getInt("hd_id"));
                hairdresser.setFirstName(rs.getString("hd_first_name"));
                hairdresser.setLastName(rs.getString("hd_last_name"));
                hairdresser.setPrice(rs.getDouble("hd_price"));
                return hairdresser;
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
    public List<Hairdresser> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Hairdresser> hairdressers = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Hairdresser hairdresser = new Hairdresser();
                hairdresser.setId(rs.getInt("hd_id"));
                hairdresser.setFirstName(rs.getString("hd_first_name"));
                hairdresser.setLastName(rs.getString("hd_last_name"));
                hairdresser.setPrice(rs.getDouble("hd_price"));
                hairdressers.add(hairdresser);
            }
            return hairdressers;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
