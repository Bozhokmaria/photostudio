package com.solvd.services.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MainService {

    private static final Logger LOGGER = LogManager.getLogger(MainService.class);

    public void startApp() {
        boolean loginSuccessful = false;

        Scanner scanner = new Scanner(System.in);

        do {
            LOGGER.info("Login as new client - type 1, login as regular - type 2");
            int log = scanner.nextInt();
            if (log == 1) {

            } else if (log == 2) {

            } else {

            }
        } while (loginSuccessful);
    }
}
