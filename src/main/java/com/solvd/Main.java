package com.solvd;

import com.solvd.dao.impl.CityDAOImpl;
import com.solvd.dao.impl.OrderDAOImpl;
import com.solvd.dao.impl.mybatis.LocationMapperDAO;
import com.solvd.dao.interfaces.LocationDAO;
import com.solvd.dao.interfaces.OrderDAO;
import com.solvd.model.City;
import com.solvd.model.Location;
import com.solvd.model.Order;
import com.solvd.services.main.MainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        MainService mainService = new MainService();

        mainService.startApp();

    }
}
