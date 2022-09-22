package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.ClientDAO;
import com.solvd.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDAOImpl implements ClientDAO {

    private static final Logger LOGGER = LogManager.getLogger(ClientDAOImpl.class);

    private static final String INSERT = "INSERT INTO client " +
            "(client.client_email, " +
            "client.client_first_name, " +
            "client.client_last_name, " +
            "client.client_password) " +
            "VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE client " +
            "SET client.client_email=?, " +
            "client.client_first_name=?, " +
            "client.client_last_name=?, " +
            "client.client_password=? " +
            "WHERE client.client_id=?";

    private static final String DELETE = "DELETE FROM client WHERE client_id=?";

    private static final String GET_BY_ID = "SELECT * FROM client WHERE client_id=?";

    private static final String GET_ALL = "SELECT * FROM client";

    @Override
    public void add(Client client) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getEmail());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getPassword());

            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            LOGGER.info("id: " + id + " object: " + client);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void update(Client updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, updated.getEmail());
            ps.setString(2, updated.getFirstName());
            ps.setString(3, updated.getLastName());
            ps.setString(4, updated.getPassword());
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
    public Client getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setEmail(rs.getString("client_email"));
                client.setFirstName(rs.getString("client_first_name"));
                client.setLastName(rs.getString("client_last_name"));
                client.setPassword(rs.getString("client_password"));
                return client;
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
    public List<Client> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Client> clients = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setEmail(rs.getString("client_email"));
                client.setFirstName(rs.getString("client_first_name"));
                client.setLastName(rs.getString("client_last_name"));
                client.setPassword(rs.getString("client_password"));
                clients.add(client);
            }
            return clients;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
