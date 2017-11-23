package by.tr.web.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import by.tr.web.dao.Parse;
import by.tr.web.dao.exception.DAOException;
import by.tr.web.entity.Book;
import by.tr.web.entity.TagList;

public class DOMParser implements Parse{

	private static final String FILE_XML = "books.xml";
	private static final String ATTRIBUTE_ID = "id";
		
	@Override
	public List<Book> doParse() throws DAOException {
		
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_XML);
	        InputSource inputSource = new InputSource(inputStream);
	        
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(inputSource);
			Element root = doc.getDocumentElement();
			
			List<Book> lib = new ArrayList<>();
			
			NodeList bookNodes = root.getElementsByTagName(TagList.BOOK.toString().toLowerCase());
	        Book book;
	        for (int i = 0; i < bookNodes.getLength(); i++) {
	        	book = getNode(bookNodes, i);
	            lib.add(book);
	        }
	        return lib;
			
		} catch (ParserConfigurationException | SAXException |IOException e) {
			throw new DAOException(e);
		} 
	}
	
	private Book getNode(NodeList bookNodes, int i) {
		Book book;
        book = new Book();
        Element bookElement = (Element) bookNodes.item(i);
        book.setId(Integer.parseInt(bookElement.getAttribute(ATTRIBUTE_ID)));
        book.setAuthor(getSingleChild(bookElement,TagList.AUTHOR.toString().toLowerCase())
        		.getTextContent().trim());
        book.setTitle(getSingleChild(bookElement, TagList.TITLE.toString().toLowerCase())
        		.getTextContent().trim());
        book.setGenre(getSingleChild(bookElement, TagList.GENRE.toString().toLowerCase())
        		.getTextContent().trim());
        String price = getSingleChild(bookElement, TagList.PRICE.toString().toLowerCase())
        		.getTextContent().trim();
        book.setPrice(Double.parseDouble(price));
        book.setPublishDate(getSingleChild(bookElement, TagList.PUBLISH_DATE.toString()
        		.toLowerCase()).getTextContent().trim());
        book.setDescription(getSingleChild(bookElement, TagList.DESCRIPTION.toString()
        		.toLowerCase()).getTextContent().trim());
        return book;
    }
	
	private static Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        return (Element) nodeList.item(0);
    }

}
