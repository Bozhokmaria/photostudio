package com.solvd.jaxb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Date;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.add(new Book("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft", "Java 8 in Action", 140.55, new Date()));
        catalog.add(new Book("Petar Tahchiev Felipe Leme Vincent Massol Gary Gregory", "JUnit in Action", 230, new Date()));

        try {
            File file = new File("src/main/java/com/solvd/jaxb/input.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Marshaller mar = jaxbContext.createMarshaller();

            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            mar.marshal(catalog, file);
            mar.marshal(catalog, System.out);

            Unmarshaller unmar = jaxbContext.createUnmarshaller();
            catalog = (Catalog) unmar.unmarshal(file);
            LOGGER.info(catalog);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}