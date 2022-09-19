package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.PhotostudioVisagisteDAO;
import com.solvd.model.PhotostudioVisagiste;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotostudioVisagisteDAOImpl implements PhotostudioVisagisteDAO {
    private static final Logger LOGGER = LogManager.getLogger(PhotostudioVisagisteDAOImpl.class);

    private static final String INSERT = "INSERT INTO photostudio_visagiste " +
            "(ph_st_id, " +
            "vs_id) " +
            "VALUES (?,?)";

    private static final String UPDATE = "UPDATE photostudio_visagiste " +
            "SET ph_st_id=?, " +
            "vs_id=? " +
            "WHERE ph_vs_id=?";

    private static final String DELETE = "DELETE FROM photostudio_visagiste WHERE ph_vs_id=?";

    private static final String GET_BY_ID = "SELECT * FROM photostudio_visagiste WHERE ph_vs_id=?";

    private static final String GET_ALL = "SELECT * FROM photostudio_visagiste";

    @Override
    public void add(PhotostudioVisagiste object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getPhotostudioId());
            ps.setInt(2, object.getVisagisteId());

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
    public void update(int id, PhotostudioVisagiste updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, updated.getPhotostudioId());
            ps.setInt(2, updated.getVisagisteId());
            ps.setInt(3, id);

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
    public PhotostudioVisagiste getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PhotostudioVisagiste photostudioVisagiste = new PhotostudioVisagiste();
                photostudioVisagiste.setId(rs.getInt("ph_vs_id"));
                photostudioVisagiste.setPhotostudioId(rs.getInt("ph_st_id"));
                photostudioVisagiste.setVisagisteId(rs.getInt("vs_id"));
                return photostudioVisagiste;
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
    public List<PhotostudioVisagiste> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<PhotostudioVisagiste> photostudioVisagistes = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhotostudioVisagiste photostudioVisagiste = new PhotostudioVisagiste();
                photostudioVisagiste.setId(rs.getInt("ph_vs_id"));
                photostudioVisagiste.setPhotostudioId(rs.getInt("ph_st_id"));
                photostudioVisagiste.setVisagisteId(rs.getInt("vs_id"));
                photostudioVisagistes.add(photostudioVisagiste);
            }
            return photostudioVisagistes;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
