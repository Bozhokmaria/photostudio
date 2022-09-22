package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.RateDAO;
import com.solvd.model.Rate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RateDAOImpl implements RateDAO {

    private static final Logger LOGGER = LogManager.getLogger(RateDAOImpl.class);

    private static final String INSERT = "INSERT INTO rate " +
            "(rate_name, " +
            "rate_percentage) " +
            "VALUES (?,?)";

    private static final String UPDATE = "UPDATE rate " +
            "SET rate_name=?, " +
            "rate_percentage=?, " +
            "WHERE rate_id=?";

    private static final String DELETE = "DELETE FROM rate WHERE rate_id=?";

    private static final String GET_BY_ID = "SELECT * FROM rate WHERE rate_id=?";

    private static final String GET_ALL = "SELECT * FROM rate";


    @Override
    public void add(Rate object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getName());
            ps.setDouble(2, object.getPercentage());

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
    public void update(Rate updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, updated.getName());
            ps.setDouble(2, updated.getPercentage());
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
    public Rate getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Rate rate = new Rate();
                rate.setId(rs.getInt("rate_id"));
                rate.setName(rs.getString("rate_name"));
                rate.setPercentage(rs.getDouble("rate_percentage"));
                return rate;
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
    public List<Rate> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Rate> rates = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rate rate = new Rate();
                rate.setId(rs.getInt("rate_id"));
                rate.setName(rs.getString("rate_name"));
                rate.setPercentage(rs.getDouble("rate_percentage"));
                rates.add(rate);
            }
            return rates;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
