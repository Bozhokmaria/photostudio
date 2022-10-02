package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.PhotostudioDAO;
import com.solvd.model.Photostudio;
import com.solvd.model.Visagiste;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotostudioDAOImpl implements PhotostudioDAO {

    private static final Logger LOGGER = LogManager.getLogger(PhotostudioDAOImpl.class);

    private static final String INSERT = "INSERT INTO photostudio " +
            "(photostudio.ph_st_name, " +
            "photostudio.ph_st_loc_id) " +
            "VALUES (?,?)";

    private static final String UPDATE = "UPDATE photostudio " +
            "SET ph_st_name=?, " +
            "ph_st_loc_id=? " +
            "WHERE ph_st_id=?";

    private static final String DELETE = "DELETE FROM photostudio WHERE ph_st_id=?";

    private static final String GET_BY_ID = "SELECT * FROM photostudio WHERE ph_st_id=?";

    private static final String GET_ALL = "SELECT * FROM photostudio";

    @Override
    public int add(Photostudio object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getName());
            ps.setInt(2, object.getLocationId());

            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                object.setId(id);
                LOGGER.info("id: " + id + " object: " + object);
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
    public void update(Photostudio updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, updated.getName());
            ps.setInt(2, updated.getLocationId());
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
    public Photostudio getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Photostudio photostudio = new Photostudio();
                photostudio.setId(rs.getInt("ph_st_id"));
                photostudio.setName(rs.getString("ph_st_name"));
                photostudio.setLocationId(rs.getInt("ph_st_loc_id"));
                return photostudio;
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
    public List<Photostudio> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Photostudio> photostudios = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Photostudio photostudio = new Photostudio();
                photostudio.setId(rs.getInt("ph_st_id"));
                photostudio.setName(rs.getString("ph_st_name"));
                photostudio.setLocationId(rs.getInt("ph_st_loc_id"));
                photostudios.add(photostudio);
            }
            return photostudios;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
