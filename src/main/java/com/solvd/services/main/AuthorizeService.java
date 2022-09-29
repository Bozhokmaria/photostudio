package com.solvd.services.main;

import com.solvd.dao.impl.ClientDAOImpl;
import com.solvd.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class AuthorizeService {

    ClientDAOImpl clientDAO = new ClientDAOImpl();

    private static final Logger LOGGER = LogManager.getLogger(AuthorizeService.class);

    public void loginNewClient() {
        Client client = new Client();

        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Type your First name");
        String firstName = scanner.nextLine();
        client.setFirstName(firstName);
        LOGGER.info("Type your Last name");
        String lastName = scanner.nextLine();
        client.setLastName(lastName);
        LOGGER.info("Type your email");
        String email = scanner.nextLine();
        client.setEmail(email);
        LOGGER.info("Type your password");
        String password = scanner.nextLine();
        client.setPassword(password);

        List<Client> clients = clientDAO.getAll();
        if (clients.contains(client)) {
            LOGGER.error("Such client already exists");
        } else {
            clientDAO.add(client);
            LOGGER.info("Successful registration. Welcome! {} {} ", client.getFirstName(), client.getLastName());
        }
    }


    public void loginOldClient() {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Type your email");
        String email = scanner.nextLine();
        client.setEmail(email);
        LOGGER.info("Type your password");
        String password = scanner.nextLine();
        client.setPassword(password);

        List<Client> clients = clientDAO.getAll();

        Client inSystemClient = clients.stream()
                .filter(client1 ->
                        client.getEmail().equals(client1.getEmail()) && client.getPassword().equals(client1.getPassword()))
                .findAny().orElse(null);

        if(inSystemClient != null){
            LOGGER.info("Successful login. Welcome! {} {} ", inSystemClient.getFirstName(), inSystemClient.getLastName());
        }
    }
}