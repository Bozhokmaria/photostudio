package com.solvd.services.main;

import com.solvd.dao.impl.OrderDAOImpl;
import com.solvd.dao.impl.RateDAOImpl;
import com.solvd.dao.interfaces.OrderDAO;
import com.solvd.dao.interfaces.RateDAO;
import com.solvd.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MainService {

    private static final Logger LOGGER = LogManager.getLogger(MainService.class);
    private AuthorizeService authorizeService = new AuthorizeService();
    private ArrangePhotosessionService arrangePhotosessionService = new ArrangePhotosessionService();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private RateDAO rateDAO = new RateDAOImpl();


    private OrderService orderService = new OrderService();

    public void startApp() {
        int clientId = loginApp();
        constructPhotosession(clientId);
    }

    private void constructPhotosession(int clientId) {
        LocalDateTime dateTime = arrangePhotosessionService.chooseDate();
        int photostudioId = arrangePhotosessionService.choosePhotostudio();
        int studioId = arrangePhotosessionService.chooseStudio(photostudioId);
        int dressingRoomId = arrangePhotosessionService.chooseDressingRoom(photostudioId);
        int photographerId = arrangePhotosessionService.choosePhotographer();
        int visagisteId = arrangePhotosessionService.chooseVisagiste();
        int hairDresserId = arrangePhotosessionService.chooseHairDresser();
        double totalPrice = arrangePhotosessionService.getTotalPrice(studioId, dressingRoomId, photographerId, visagisteId, hairDresserId);


        Order order = new Order();

        order.setClientId(clientId);
        order.setPhotostudioId(photostudioId);
        order.setStudioId(studioId);
        order.setDressRoomId(dressingRoomId);
        order.setPhotographerId(photographerId);
        order.setVisagisteId(visagisteId);
        order.setHairdresserId(hairDresserId);

        order.setBooking(dateTime);
        if (dateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) || dateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            LOGGER.info("Rate is for weekend");
            order.setRateId(2);
            totalPrice = totalPrice + totalPrice*rateDAO.getById(2).getPercentage()/100;
            LOGGER.info("Total price after applying the rate {}", totalPrice);

        } else {
            LOGGER.info("Rate is usual");
            order.setRateId(1);
        }
        order.setTotalPrice(totalPrice);


        arrangePhotosessionService.proceedPayment(totalPrice, clientId);

        orderDAO.add(order);
        orderService.getAllMyOrders(clientId);

    }

    private int loginApp() {
        boolean loginNotSuccessful = true;
        int clientId = 0;

        Scanner scanner = new Scanner(System.in);

        do {
            LOGGER.info("Login as new client - type 1, login as regular - type 2");
            int log = scanner.nextInt();
            if (log == 1) {
                clientId = authorizeService.loginNewClient();
            } else if (log == 2) {
                clientId = authorizeService.loginOldClient();
            } else {
                LOGGER.error("No such option. Type correct option");
            }
            if (clientId != 0) {
                loginNotSuccessful = false;
            }
        } while (loginNotSuccessful);
        LOGGER.info("Client id: {}", clientId );
        return clientId;
    }
}
