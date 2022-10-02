package com.solvd.services.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MainService {

    private static final Logger LOGGER = LogManager.getLogger(MainService.class);
    private AuthorizeService authorizeService = new AuthorizeService();

    public void startApp() {
        loginApp();
        constructPhotosession();
    }

    private void constructPhotosession() {

    }

    private void loginApp() {
        boolean loginSuccessful = false;

        Scanner scanner = new Scanner(System.in);

        do {
            LOGGER.info("Login as new client - type 1, login as regular - type 2");
            int log = scanner.nextInt();
            if (log == 1) {
                loginSuccessful = authorizeService.loginNewClient();
            } else if (log == 2) {
                loginSuccessful = authorizeService.loginOldClient();
            } else {
                LOGGER.error("No such option. Type correct option");
            }
        } while (loginSuccessful);
    }
}
