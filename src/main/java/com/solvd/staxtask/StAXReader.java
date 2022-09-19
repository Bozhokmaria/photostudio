package com.solvd.staxtask;

import java.io.FileReader;
import java.io.Reader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class StAXReader {
    public static void main(String args[]){
        try {
            String filePath = "src/main/java/com/solvd/staxtask/input.xml";

            Reader fileReader = new FileReader(filePath);

            XMLInputFactory xmlInputFactory =
                    XMLInputFactory.newInstance();

            XMLStreamReader xmlStreamReader  =
                    xmlInputFactory.createXMLStreamReader(fileReader);

            while(xmlStreamReader.hasNext()){
                int xmlEvent = xmlStreamReader.next();

                if (xmlEvent == XMLStreamConstants.START_ELEMENT) {
                    System.out.println("Start Element: "
                            +xmlStreamReader.getLocalName());
                    int attributes = xmlStreamReader.getAttributeCount();
                    for(int i=0; i<attributes; i++){
                        QName name = xmlStreamReader.getAttributeName(i);
                        String value=xmlStreamReader.getAttributeValue(i);
                        System.out.println("Attribute name: " + name);
                        System.out.println("Attribute value: " + value);
                    }
                }

                if (xmlEvent == XMLStreamConstants.END_ELEMENT) {
                    System.out.println("End Element: "
                            +xmlStreamReader.getLocalName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}