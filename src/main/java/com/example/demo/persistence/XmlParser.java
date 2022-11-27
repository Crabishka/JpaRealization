package com.example.demo.persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class XmlParser {


    public PersistenceContext readPersistenceContext(String persistenceUnitName) throws ParserConfigurationException, IOException, SAXException, SQLException {
        String path = new String();
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        String URL = "jdbc:postgresql://localhost:5432/postgres";
        String password = "koteika322";
        String username = "admin";
        DataSourcePool dataSourcePool = DataSourcePool.create(URL, username, password);
        PersistenceContext persistenceContext = new PersistenceContext(persistenceUnitName, dataSourcePool);

        return persistenceContext;
    }


}
