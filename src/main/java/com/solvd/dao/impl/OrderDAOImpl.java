package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.OrderDAO;
import com.solvd.model.Order;
import com.solvd.model.Visagiste;
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
            "photostudio.order.order_booking, " +
            "photostudio.order.dress_room_id, " +
            "photostudio.order.studio_id, " +
            "photostudio.order.ph_id, " +
            "photostudio.order.vs_id, " +
            "photostudio.order.hd_id, " +
            "photostudio.order.rate_id, " +
            "photostudio.order.ph_st_id, " +
            "photostudio.order.order_total_price " +
            "VALUES (?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE = "UPDATE photostudio.order " +
            "SET photostudio.order.order_booking=?, " +
            "photostudio.order.dress_room_id=?, " +
            "photostudio.order.studio_id=?, " +
            "photostudio.order.ph_id=?, " +
            "photostudio.order.vs_id=?, " +
            "photostudio.order.hd_id=?, " +
            "photostudio.order.rate_id=?, " +
            "photostudio.order.ph_st_id=?, " +
            "photostudio.order.order_total_price=? " +
            "WHERE photostudio.order.order_id=?";

    private static final String DELETE = "DELETE FROM photostudio.order WHERE photostudio.order.order_id=?";

    private static final String GET_BY_ID = "SELECT * FROM photostudio.order WHERE photostudio.order.order_id=?";

    private static final String GET_ALL = "SELECT * FROM photostudio.order";

    @Override
    public void add(Order object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setDate(1, Date.valueOf(object.getBooking().toLocalDate()));
            ps.setInt(2, object.getDressRoomId());
            ps.setInt(3, object.getStudioId());
            ps.setInt(4, object.getPhotographerId());
            ps.setInt(5, object.getVisagisteId());
            ps.setInt(6, object.getHairdresserId());
            ps.setInt(7, object.getRateId());
            ps.setInt(8, object.getPhotostudioId());
            ps.setDouble(9, object.getTotalPrice());

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
    public void update(int id, Order updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setDate(1, Date.valueOf(updated.getBooking().toLocalDate()));
            ps.setInt(2, updated.getDressRoomId());
            ps.setInt(3, updated.getStudioId());
            ps.setInt(4, updated.getPhotographerId());
            ps.setInt(5, updated.getVisagisteId());
            ps.setInt(6, updated.getHairdresserId());
            ps.setInt(7, updated.getRateId());
            ps.setInt(8, updated.getPhotostudioId());
            ps.setDouble(9, updated.getTotalPrice());
            ps.setInt(10, id);

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
                order.setClientId(rs.getInt("dress_room_id"));
                order.setClientId(rs.getInt("studio_id"));
                order.setClientId(rs.getInt("ph_id"));
                order.setClientId(rs.getInt("vs_id"));
                order.setClientId(rs.getInt("hd_id"));
                order.setClientId(rs.getInt("rate_id"));
                order.setClientId(rs.getInt("ph_st_id"));
                order.setTotalPrice(rs.getDouble("order_total_price"));
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
                order.setClientId(rs.getInt("dress_room_id"));
                order.setClientId(rs.getInt("studio_id"));
                order.setClientId(rs.getInt("ph_id"));
                order.setClientId(rs.getInt("vs_id"));
                order.setClientId(rs.getInt("hd_id"));
                order.setClientId(rs.getInt("rate_id"));
                order.setClientId(rs.getInt("ph_st_id"));
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
