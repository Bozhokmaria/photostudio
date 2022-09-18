package com.solvd;

import com.solvd.dao.impl.CityDAOImpl;
import com.solvd.dao.impl.OrderDAOImpl;
import com.solvd.dao.interfaces.OrderDAO;
import com.solvd.model.City;
import com.solvd.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        LOGGER.info("-----Gett all-----");
        CityDAOImpl cityDAO = new CityDAOImpl();
        List<City> cityList = cityDAO.getAll();
        for(City city : cityList) {
            LOGGER.info(city);
        }

        LOGGER.info("-----Gett all-----");
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders = orderDAO.getAll();
        for(Order order : orders) {
            LOGGER.info(order);
        }
        LOGGER.info("-----Gett by Id-----");
        LOGGER.info(orderDAO.getById(1));

    }
}
