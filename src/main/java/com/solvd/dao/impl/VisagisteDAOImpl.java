package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.VisagisteDAO;
import com.solvd.model.Visagiste;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisagisteDAOImpl implements VisagisteDAO {

    private static final Logger LOGGER = LogManager.getLogger(VisagisteDAOImpl.class);

    private static final String INSERT = "INSERT INTO visagiste " +
            "(visagiste.vs_first_name, " +
            "visagiste.vs_last_name, " +
            "visagiste.vs_price) " +
            "VALUES (?,?,?)";

    private static final String UPDATE = "UPDATE visagiste " +
            "SET visagiste.vs_first_name=?, " +
            "visagiste.vs_last_name=?, " +
            "visagiste.vs_price=? " +
            "WHERE visagiste.vs_id=?";

    private static final String DELETE = "DELETE FROM visagiste WHERE vs_id=?";

    private static final String GET_BY_ID = "SELECT * FROM visagiste WHERE vs_id=?";

    private static final String GET_ALL = "SELECT * FROM visagiste";
    @Override
    public int add(Visagiste object) {
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
    public void update(Visagiste updated) {
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
    public Visagiste getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Visagiste visagiste = new Visagiste();
                visagiste.setId(rs.getInt("vs_id"));
                visagiste.setFirstName(rs.getString("vs_first_name"));
                visagiste.setLastName(rs.getString("vs_last_name"));
                visagiste.setPrice(rs.getDouble("vs_price"));
                return visagiste;
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
    public List<Visagiste> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Visagiste> visagistes = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Visagiste visagiste = new Visagiste();
                visagiste.setId(rs.getInt("vs_id"));
                visagiste.setFirstName(rs.getString("vs_first_name"));
                visagiste.setLastName(rs.getString("vs_last_name"));
                visagiste.setPrice(rs.getDouble("vs_price"));
                visagistes.add(visagiste);
            }
            return visagistes;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
