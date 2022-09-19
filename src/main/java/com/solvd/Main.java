package com.solvd;

import com.solvd.dao.impl.CityDAOImpl;
import com.solvd.dao.impl.OrderDAOImpl;
import com.solvd.dao.interfaces.OrderDAO;
import com.solvd.model.City;
import com.solvd.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        LOGGER.info("-----Get all-----");
        CityDAOImpl cityDAO = new CityDAOImpl();
        List<City> cityList = cityDAO.getAll();
        for(City city : cityList) {
            LOGGER.info(city);
        }

        LOGGER.info("-----Get all-----");
        OrderDAO orderDAO = new OrderDAOImpl();
        for(Order order : orderDAO.getAll()) {
            LOGGER.info(order);
        }


        LOGGER.info("-----Get by Id-----");
        LOGGER.info(orderDAO.getById(2));


        LOGGER.info("-----Create-----");
        Order orderNew = new Order();
        orderNew.setDressRoomId(1);
        orderNew.setClientId(1);
        orderNew.setBooking(LocalDateTime.now());
        orderNew.setPhotostudioId(1);
        orderNew.setVisagisteId(2);
        orderNew.setHairdresserId(2);
        orderNew.setStudioId(1);
        orderNew.setRateId(1);
        orderNew.setPhotographerId(2);
        orderNew.setTotalPrice(1400.00);

        orderDAO.add(orderNew);

        LOGGER.info("-----Update-----");

        Order orderUpdate = new Order();
        orderUpdate.setBooking(LocalDateTime.now());
        orderUpdate.setDressRoomId(2);
        orderUpdate.setClientId(1);
        orderUpdate.setPhotostudioId(1);
        orderUpdate.setVisagisteId(2);
        orderUpdate.setHairdresserId(2);
        orderUpdate.setStudioId(1);
        orderUpdate.setRateId(1);
        orderUpdate.setPhotographerId(2);
        orderUpdate.setTotalPrice(1400.00);

        orderDAO.update(1, orderUpdate);

        for(Order order : orderDAO.getAll()) {
            LOGGER.info(order);
        }

        LOGGER.info("-----Delete-----");
        orderDAO.delete(2);
        for(Order order : orderDAO.getAll()) {
            LOGGER.info(order);
        }
    }
}
