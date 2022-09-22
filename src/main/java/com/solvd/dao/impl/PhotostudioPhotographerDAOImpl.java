package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.PhotostudioPhotographerDAO;
import com.solvd.model.PhotostudioPhotographer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotostudioPhotographerDAOImpl implements PhotostudioPhotographerDAO {
    private static final Logger LOGGER = LogManager.getLogger(PhotostudioPhotographerDAOImpl.class);

    private static final String INSERT = "INSERT INTO photostudio_photographer " +
            "(ph_st_id, " +
            "ph_id) " +
            "VALUES (?,?)";

    private static final String UPDATE = "UPDATE photostudio_photographer " +
            "SET ph_st_id=?, " +
            "ph_id=? " +
            "WHERE ph_ph_id=?";

    private static final String DELETE = "DELETE FROM photostudio_photographer WHERE ph_ph_id=?";

    private static final String GET_BY_ID = "SELECT * FROM photostudio_photographer WHERE ph_ph_id=?";

    private static final String GET_ALL = "SELECT * FROM photostudio_photographer";


    @Override
    public void add(PhotostudioPhotographer object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getPhotostudioId());
            ps.setInt(2, object.getPhotographerId());

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
    public void update(PhotostudioPhotographer updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, updated.getPhotostudioId());
            ps.setInt(2, updated.getPhotographerId());
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
    public PhotostudioPhotographer getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PhotostudioPhotographer photostudioPhotographer = new PhotostudioPhotographer();
                photostudioPhotographer.setId(rs.getInt("ph_ph_id"));
                photostudioPhotographer.setPhotostudioId(rs.getInt("ph_st_id"));
                photostudioPhotographer.setPhotographerId(rs.getInt("ph_id"));
                return photostudioPhotographer;
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
    public List<PhotostudioPhotographer> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<PhotostudioPhotographer> photostudioPhotographers = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhotostudioPhotographer photostudioPhotographer = new PhotostudioPhotographer();
                photostudioPhotographer.setId(rs.getInt("ph_ph_id"));
                photostudioPhotographer.setPhotostudioId(rs.getInt("ph_st_id"));
                photostudioPhotographer.setPhotographerId(rs.getInt("ph_id"));
                photostudioPhotographers.add(photostudioPhotographer);
            }
            return photostudioPhotographers;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
