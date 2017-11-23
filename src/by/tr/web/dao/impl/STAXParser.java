package by.tr.web.dao.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.tr.web.dao.Parse;
import by.tr.web.dao.exception.DAOException;
import by.tr.web.entity.Book;
import by.tr.web.entity.TagList;

public class STAXParser implements Parse{
	private static final String FILE_XML = "books.xml";
	private static final String ATTRIBUTE_ID = "id";
	
	@Override
	public List<Book> doParse() throws DAOException {

	    XMLInputFactory factory = XMLInputFactory.newInstance();
	    XMLStreamReader reader;
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_XML);
			reader = factory.createXMLStreamReader(inputStream);
			return cathEvent(reader);
		} catch (XMLStreamException e) {
			throw new DAOException(e);
		}
}
	
	private List<Book> cathEvent(XMLStreamReader reader) throws XMLStreamException {
		List<Book> bookList = new ArrayList<>();
	    Book book = null;
	    String text = null;
	    
		while(reader.hasNext()){
		      int event = reader.next();
		       
		      switch(event){
		      
		        case XMLStreamConstants.START_ELEMENT: {
		        	if (TagList.BOOK.toString().toLowerCase().equals(reader.getLocalName())){
		        		book = new Book();
		        		book.setId(Integer.parseInt(reader.getAttributeValue(null,ATTRIBUTE_ID))); 
			          }
			          break;
		        }  
		        
		        case XMLStreamConstants.CHARACTERS:{
		        	text = reader.getText().trim();
		        	break;
		        } 
		        
		        case XMLStreamConstants.END_ELEMENT:{
		        	TagList tagName = TagList.valueOf(reader.getLocalName().toUpperCase());
			          switch(tagName){
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
			
		}
		 return bookList;
	}
}
