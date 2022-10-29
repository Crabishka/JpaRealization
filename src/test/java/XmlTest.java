import com.example.demo.persistence.XmlReader;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class XmlTest {

    @Test
    public void testXml() throws ParserConfigurationException, IOException, SAXException, SQLException {
        XmlReader xmlReader = new XmlReader();
        System.out.println(xmlReader.readPersistenceContext("C:\\Users\\hagog\\IdeaProjects\\demo\\src\\main\\resources\\META-INF\\persistence.xml"));
    }
}
