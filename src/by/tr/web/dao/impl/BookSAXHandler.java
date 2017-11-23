package by.tr.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.tr.web.entity.Book;
import by.tr.web.entity.TagList;

public class BookSAXHandler extends DefaultHandler{
	private static final String ATTRIBUTE_ID = "id";
	
	private List<Book> bookList = new ArrayList<>();
    private Book book;
    private StringBuilder text;

    public List<Book> getBookList() {
        return bookList;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
    		throws SAXException {
        text = new StringBuilder();
        if (qName.equals(TagList.BOOK.toString().toLowerCase())) {
            book = new Book();
            book.setId(Integer.parseInt(attributes.getValue(ATTRIBUTE_ID)));
        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        TagList tagName = TagList.valueOf(localName.toUpperCase());
        switch (tagName) {
        	case BOOK:{
        		bookList.add(book);
        		break;
        	}
            case AUTHOR:{
            	book.setAuthor(text.toString());
                break;
            }
            case TITLE:{
            	book.setTitle(text.toString());
                break;
            }
            case GENRE:{
            	book.setGenre(text.toString());
                break;
            }
            case PRICE:{
            	book.setPrice(Double.parseDouble(text.toString()));
                break;
            }
            case PUBLISH_DATE:{
            	book.setPublishDate(text.toString());
                break;
            }
            case DESCRIPTION:{
            	book.setDescription(text.toString());
                break;
            }
        }
    }
}
