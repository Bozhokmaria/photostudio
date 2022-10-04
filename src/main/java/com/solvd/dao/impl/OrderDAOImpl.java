package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.OrderDAO;
import com.solvd.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);

    private static final String INSERT = "INSERT INTO photostudio.order " +
            "(order_booking, " +
            "dress_room_id, " +
            "client_id, " +
            "studio_id, " +
            "ph_id, " +
            "vs_id, " +
            "hd_id, " +
            "rate_id, " +
            "ph_st_id, " +
            "order_total_price) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE = "UPDATE photostudio.order " +
            "SET order_booking=?, " +
            "dress_room_id=?, "+
            "client_id=?, " +
            "studio_id=?, " +
            "ph_id=?, " +
            "vs_id=?, " +
            "hd_id=?, " +
            "rate_id=?, " +
            "ph_st_id=?, " +
            "order_total_price=? " +
            "WHERE order_id=?";

    private static final String DELETE = "DELETE FROM photostudio.order WHERE order_id=?";

    private static final String GET_BY_ID = "SELECT * FROM photostudio.order WHERE order_id=?";

    private static final String GET_ALL = "SELECT * FROM photostudio.order";

    @Override
    public int add(Order object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);


            ps.setTimestamp(1, Timestamp.valueOf(object.getBooking()));
            ps.setInt(2, object.getDressRoomId());
            ps.setInt(3, object.getClientId());
            ps.setInt(4, object.getStudioId());
            ps.setInt(5, object.getPhotographerId());
            ps.setInt(6, object.getVisagisteId());
            ps.setInt(7, object.getHairdresserId());
            ps.setInt(8, object.getRateId());
            ps.setInt(9, object.getPhotostudioId());
            ps.setDouble(10, object.getTotalPrice());

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
    public void update(Order updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setDate(1, Date.valueOf(updated.getBooking().toLocalDate()));
            ps.setInt(2, updated.getDressRoomId());
            ps.setInt(3, updated.getClientId());
            ps.setInt(4, updated.getStudioId());
            ps.setInt(5, updated.getPhotographerId());
            ps.setInt(6, updated.getVisagisteId());
            ps.setInt(7, updated.getHairdresserId());
            ps.setInt(8, updated.getRateId());
            ps.setInt(9, updated.getPhotostudioId());
            ps.setDouble(10, updated.getTotalPrice());
            ps.setInt(11, updated.getId());

            ps.executeUpdate();
            LOGGER.info("Updated id - " + updated.getId());

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
            ps.setInt(1, id);
            ps.executeUpdate();
            LOGGER.info("Deleted id - " + id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Order getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));

                String date = rs.getString("order_booking");
                String dateParsed = date.substring(0,19);
                LocalDateTime dateTime = LocalDateTime.parse(dateParsed, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                order.setBooking(dateTime);

                order.setClientId(rs.getInt("client_id"));
                order.setDressRoomId(rs.getInt("dress_room_id"));
                order.setStudioId(rs.getInt("studio_id"));
                order.setPhotographerId(rs.getInt("ph_id"));
                order.setVisagisteId(rs.getInt("vs_id"));
                order.setHairdresserId(rs.getInt("hd_id"));
                order.setRateId(rs.getInt("rate_id"));
                order.setPhotostudioId(rs.getInt("ph_st_id"));
                order.setTotalPrice(rs.getDouble("order_total_price"));
                LOGGER.info("Get by id");
                return order;
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
    public List<Order> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Order> orders = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));

                String date = rs.getString("order_booking");
                String dateParsed = date.substring(0,19);
                LocalDateTime dateTime = LocalDateTime.parse(dateParsed, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                order.setBooking(dateTime);
                order.setClientId(rs.getInt("client_id"));
                order.setDressRoomId(rs.getInt("dress_room_id"));
                order.setStudioId(rs.getInt("studio_id"));
                order.setPhotographerId(rs.getInt("ph_id"));
                order.setVisagisteId(rs.getInt("vs_id"));
                order.setHairdresserId(rs.getInt("hd_id"));
                order.setRateId(rs.getInt("rate_id"));
                order.setPhotostudioId(rs.getInt("ph_st_id"));
                order.setTotalPrice(rs.getDouble("order_total_price"));
                orders.add(order);
            }
            return orders;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
