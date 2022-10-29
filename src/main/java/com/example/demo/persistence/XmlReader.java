package com.example.demo.persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class XmlReader {


    public PersistenceContext readPersistenceContext(String path) throws ParserConfigurationException, IOException, SAXException, SQLException {
        PersistenceContext persistenceContext = new PersistenceContext();
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList nodeList = document.getElementsByTagName("persistence-unit");
        Element element = (Element) nodeList.item(0);
        persistenceContext.persistenceUnitName = element.getAttribute("name");
        
//        nodeList = document.getElementsByTagName("property");
//        for (int i=0; i<nodeList.getLength(); i++)
//        {
//            // Get element
//            Element e= (Element)nodeList.item(i);
//            System.out.println(e.getNodeName() + " " + e.getAttribute("name") + " " + e.getAttribute("value"));
//        }
        String URL = "";
        String password = "";
        String username = "";
        DataSourcePool dataSourcePool = DataSourcePool.create(URL, username, password);


        return persistenceContext;
    }


}
