package quotes;

import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class AddXMLQuote {
    public void addQuoteToXML(String author, String Newquote, String keyword) throws Exception {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("src/quotes/quotes.xml");
        Element root = document.getDocumentElement();

        Collection<Quote> quotes = new ArrayList<Quote>();
        quotes.add(new Quote());

        for (Quote quote : quotes) {
            Element newQuoteToBeAdded = document.createElement("quote"); //new quote(node) to be added

            Element name = document.createElement("quote-text");//quote section
            name.appendChild(document.createTextNode(Newquote));
            newQuoteToBeAdded.appendChild(name);

            Element port = document.createElement("author");//author section
            port.appendChild(document.createTextNode(author));
            newQuoteToBeAdded.appendChild(port);
            
            Element port2 = document.createElement("keyword");//keyword section
            port2.appendChild(document.createTextNode(keyword));
            newQuoteToBeAdded.appendChild(port2);

            root.appendChild(newQuoteToBeAdded);//add to quote-list
        }

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("src/quotes/quotes.xml");
        transformer.transform(source, result);//overwrite with new file/result
    }

}