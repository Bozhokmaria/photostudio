package com.solvd.services.main;

import com.solvd.dao.impl.OrderDAOImpl;
import com.solvd.dao.interfaces.OrderDAO;
import com.solvd.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);
    private OrderDAO orderDAO = new OrderDAOImpl();

    public void getAllMyOrders(int clientId) {
        List<Order> orderList = orderDAO.getAll();
        List<Order> clientOrders = orderList.stream().filter(order -> order.getClientId() == clientId).collect(Collectors.toList());
        if (clientOrders.isEmpty()) {
            LOGGER.info("No orders for the client");
        } else {
            for (Order order : clientOrders) {
                LOGGER.info("Your order details: Date {} , Total Price {} ",  order.getBooking(), order.getTotalPrice());
            }

        }


    }


}
