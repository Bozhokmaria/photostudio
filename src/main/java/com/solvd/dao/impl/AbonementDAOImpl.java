package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.AbonementDAO;
import com.solvd.model.Abonement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonementDAOImpl implements AbonementDAO {
    private static final Logger LOGGER = LogManager.getLogger(AbonementDAOImpl.class);

    private static final String INSERT = "INSERT INTO abonement " +
            "(abonement.client_id, " +
            "abonement.abon_name, " +
            "abonement.abon_photosession_amount, " +
            "abonement.abon_price) " +
            "VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE abonement " +
            "SET abonement.client_id=?, " +
            "abonement.abon_name=?, " +
            "abonement.abon_photosession_amount=?, " +
            "abonement.abon_price=? " +
            "WHERE abonement.abon_id=?";
    private static final String DELETE = "DELETE FROM abonement WHERE abon_id=?";

    private static final String GET_BY_ID = "SELECT * FROM abonement WHERE abon_id=?";

    private static final String GET_ALL = "SELECT * FROM abonement";

    @Override
    public int add(Abonement abonement) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, abonement.getClientId());
            ps.setString(2, abonement.getName());
            ps.setInt(3, abonement.getPhotosessionAmount());
            ps.setDouble(4, abonement.getPrice());
            ps.executeUpdate();


            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                abonement.setId(id);
                LOGGER.info("id: " + id + " object: " + abonement);
                return id;
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return 0;
    }

    @Override
    public void update(Abonement updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, updated.getClientId());
            ps.setString(2, updated.getName());
            ps.setInt(3, updated.getPhotosessionAmount());
            ps.setDouble(4, updated.getPrice());
            ps.setInt(5, updated.getId());

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
    public Abonement getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Abonement abonement = new Abonement();
                abonement.setId(rs.getInt("abon_id"));
                abonement.setClientId(rs.getInt("client_id"));
                abonement.setName(rs.getString("abon_name"));
                abonement.setPhotosessionAmount(rs.getInt("abon_photosession_amount"));
                abonement.setPrice(rs.getDouble("abon_price"));
                return abonement;
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
    public List<Abonement> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Abonement> abonements = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Abonement abonement = new Abonement();
                abonement.setId(rs.getInt("abon_id"));
                abonement.setClientId(rs.getInt("client_id"));
                abonement.setName(rs.getString("abon_name"));
                abonement.setPhotosessionAmount(rs.getInt("abon_photosession_amount"));
                abonement.setPrice(rs.getDouble("abon_price"));
                abonements.add(abonement);
            }
            return abonements;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}