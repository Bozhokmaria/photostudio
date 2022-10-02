package com.solvd.services.main;

import com.solvd.dao.impl.ClientDAOImpl;
import com.solvd.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizeService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private ClientDAOImpl clientDAO = new ClientDAOImpl();

    private static final Logger LOGGER = LogManager.getLogger(AuthorizeService.class);

    public int loginNewClient() {

        Client client = new Client();

        Scanner scanner = new Scanner(System.in);


        boolean notValidFirstName = true;
        while (notValidFirstName) {
            LOGGER.info("Type your First name");
            String firstName = scanner.nextLine();
            if (firstName.matches("[A-Z][a-z]+")) {
                client.setFirstName(firstName);
                notValidFirstName = false;
            } else {
                LOGGER.error("First letter should be uppercase");
            }
        }


        boolean notValidLastName = true;
        while (notValidLastName) {
            LOGGER.info("Type your Last name");
            String lastName = scanner.nextLine();
            if (lastName.matches("[A-Z][a-z]+")) {
                client.setLastName(lastName);
                notValidLastName = false;
            } else {
                LOGGER.error("First letter should be uppercase");
            }
        }


        boolean notValidEmail = true;
        while (notValidEmail) {
            LOGGER.info("Type your email");
            String email = scanner.nextLine();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (matcher.find()) {
                client.setEmail(email);
                notValidEmail = false;
            } else {
                LOGGER.error("not valid email entered");
            }
        }


        LOGGER.info("Type your password");
        String password = scanner.nextLine();
        client.setPassword(password);

        List<Client> clients = clientDAO.getAll();
        if (clients.contains(client)) {
            LOGGER.error("Such client already exists");
           Client clientIn = clients.stream().filter(client1 -> client1.equals(client)).findAny().get();
            return clientIn.getId();
        } else {
          int id = clientDAO.add(client);
            LOGGER.info("Successful registration. Welcome! {} {} ", client.getFirstName(), client.getLastName());
            return id;
        }
    }


    public int loginOldClient() {

        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        boolean notValidEmail = true;
        while (notValidEmail) {
            LOGGER.info("Type your email");
            String email = scanner.nextLine();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (matcher.find()) {
                client.setEmail(email);
                notValidEmail = false;
            } else {
                LOGGER.error("not valid email entered");
            }
        }


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
            return inSystemClient.getId();
        } else {
            LOGGER.error("No such client in the system");
            return 0;
        }
    }
}