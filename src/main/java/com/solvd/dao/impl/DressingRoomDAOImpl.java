package com.solvd.dao.impl;

import com.solvd.connection.ConnectionUtil;
import com.solvd.dao.interfaces.DressingRoomDAO;
import com.solvd.model.DressingRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DressingRoomDAOImpl implements DressingRoomDAO {

    private static final Logger LOGGER = LogManager.getLogger(DressingRoomDAOImpl.class);

    private static final String INSERT = "INSERT INTO dressing_room " +
            "(dressing_room.dress_room_name, " +
            "dressing_room.dress_room_price, " +
            "dressing_room.dress_room_ph_st) " +
            "VALUES (?,?,?)";

    private static final String UPDATE = "UPDATE dressing_room " +
            "SET dressing_room.dress_room_name=?, " +
            "dressing_room.dress_room_price=?, " +
            "dressing_room.dress_room_ph_st=? " +
            "WHERE dressing_room.dress_room_id=?";

    private static final String DELETE = "DELETE FROM dressing_room WHERE dress_room_id=?";

    private static final String GET_BY_ID = "SELECT * FROM dressing_room WHERE dress_room_id=?";

    private static final String GET_ALL = "SELECT * FROM dressing_room";
    @Override
    public int add(DressingRoom object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getName());
            ps.setDouble(2, object.getPrice());
            ps.setInt(3, object.getPhotostudioId());

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
    public void update(DressingRoom updated) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, updated.getName());
            ps.setDouble(2, updated.getPrice());
            ps.setInt(3, updated.getPhotostudioId());
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
    public DressingRoom getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DressingRoom dressingRoom = new DressingRoom();
                dressingRoom.setId(rs.getInt("dress_room_id"));
                dressingRoom.setName(rs.getString("dress_room_name"));
                dressingRoom.setPrice(rs.getDouble("dress_room_price"));
                dressingRoom.setPhotostudioId(rs.getInt("dress_room_ph_st"));
                return dressingRoom;
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
    public List<DressingRoom> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<DressingRoom> dressingRooms = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DressingRoom dressingRoom = new DressingRoom();
                dressingRoom.setId(rs.getInt("dress_room_id"));
                dressingRoom.setName(rs.getString("dress_room_name"));
                dressingRoom.setPrice(rs.getDouble("dress_room_price"));
                dressingRoom.setPhotostudioId(rs.getInt("dress_room_ph_st"));
                dressingRooms.add(dressingRoom);
            }
            return dressingRooms;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
