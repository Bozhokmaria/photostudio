package com.solvd.jacksonTask;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = new Book("Petar Tahchiev Felipe Leme Vincent Massol Gary Gregory", "JUnit in Action", 230, new Date());

        try {
            objectMapper.writeValue(new File("src/main/java/com/solvd/jacksonTask/book.json"), book);
        } catch (StreamWriteException e) {
            LOGGER.error(e.getMessage());
        } catch (DatabindException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            Book book1 = objectMapper.readValue(new URL("file:src/main/java/com/solvd/jacksonTask/book.json"), Book.class);
            LOGGER.info(book1);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}