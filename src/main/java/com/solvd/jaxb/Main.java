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
        catalog.add(new Book("Author1", "Title1", 40.55, new Date()));
        catalog.add(new Book("Author2", "Title2", 30, new Date()));

        try {
            File file = new File("src/main/java/com/solvd/jaxb/output.xml");
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