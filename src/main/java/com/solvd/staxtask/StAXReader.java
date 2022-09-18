package com.solvd.staxtask;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXReader {

    public static void main(String args[]) {
        try {
            String filePath = "src/main/java/com/solvd/staxtask/input.xml";

            Reader fileReader = new FileReader(filePath);

            XMLInputFactory xmlInputFactory =
                    XMLInputFactory.newInstance();

            XMLEventReader xmlEventReader =
                    xmlInputFactory.createXMLEventReader(fileReader);

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    System.out.println("Start Element: " + startElement.getName());

                    Iterator iterator = startElement.getAttributes();
                    while (iterator.hasNext()) {
                        Attribute attribute = (Attribute) iterator.next();
                        QName name = attribute.getName();
                        String value = attribute.getValue();
                        System.out.println("Attribute name: " + name);
                        System.out.println("Attribute value: " + value);
                    }
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    System.out.println("End Element: " + endElement.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}