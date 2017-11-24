package by.tr.web.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.tr.web.dao.Parse;
import by.tr.web.dao.exception.DAOException;
import by.tr.web.entity.Book;

public class SAXParser implements Parse{

	private static final String FILE_XML = "books.xml";
	
	@Override
	public List<Book> doParse() throws DAOException {
		try {
            List<Book> list;
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_XML);
            InputSource inputSource = new InputSource(inputStream);

            XMLReader reader = XMLReaderFactory.createXMLReader();

            BookSAXHandler handler = new BookSAXHandler();
            reader.setContentHandler(handler);
            reader.parse(inputSource);

            list = handler.getBookList();

            return list;

        } catch (SAXException e) {
            throw new DAOException("Parser SAX error",e);
        } catch (IOException e) {
        	throw new DAOException("File error",e);
		}
	}

}
