package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.PhotostudioHairdresserDAO;
import com.solvd.model.PhotostudioHairdresser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotostudioHairdresserDAOImpl implements PhotostudioHairdresserDAO {

    private static final Logger LOGGER = LogManager.getLogger(PhotostudioHairdresserDAOImpl.class);

    private static final String INSERT = "INSERT INTO photostudio_hairdresser " +
            "(ph_st_id, " +
            "hd_id) " +
            "VALUES (?,?)";

    private static final String UPDATE = "UPDATE photostudio_hairdresser " +
            "SET ph_st_id=?, " +
            "hd_id=? " +
            "WHERE ph_hd_id=?";

    private static final String DELETE = "DELETE FROM photostudio_hairdresser WHERE ph_hd_id=?";

    private static final String GET_BY_ID = "SELECT * FROM photostudio_hairdresser WHERE ph_hd_id=?";

    private static final String GET_ALL = "SELECT * FROM photostudio_hairdresser";


    @Override
    public void add(PhotostudioHairdresser object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getPhotostudioId());
            ps.setInt(2, object.getHairdresserId());

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
    public void update(PhotostudioHairdresser updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, updated.getPhotostudioId());
            ps.setInt(2, updated.getHairdresserId());
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
    public PhotostudioHairdresser getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PhotostudioHairdresser photostudioHairdresser = new PhotostudioHairdresser();
                photostudioHairdresser.setId(rs.getInt("ph_hd_id"));
                photostudioHairdresser.setPhotostudioId(rs.getInt("ph_st_id"));
                photostudioHairdresser.setHairdresserId(rs.getInt("hd_id"));
                return photostudioHairdresser;
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
    public List<PhotostudioHairdresser> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<PhotostudioHairdresser> photostudioHairdressers = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhotostudioHairdresser photostudioHairdresser = new PhotostudioHairdresser();
                photostudioHairdresser.setId(rs.getInt("ph_hd_id"));
                photostudioHairdresser.setPhotostudioId(rs.getInt("ph_st_id"));
                photostudioHairdresser.setHairdresserId(rs.getInt("hd_id"));
                photostudioHairdressers.add(photostudioHairdresser);
            }
            return photostudioHairdressers;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
