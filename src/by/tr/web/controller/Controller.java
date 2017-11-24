package by.tr.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tr.web.entity.Book;
import by.tr.web.service.Service;
import by.tr.web.service.ServiceFactory;
import by.tr.web.service.exception.ServiceException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARSER = "parser";
    private static final String CURRENT_PAGE = "pageNumber";
    private static final String LIST = "list";
    private static final String XML_VIEWER = "/WEB-INF/view.jsp";
    private static final String ERROR = "/WEB-INF/error.jsp";
    private static final int BOOKS_ON_PAGE = 4;
    
    public Controller() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNumberValue = request.getParameter(CURRENT_PAGE);
        String parserParam = request.getParameter(PARSER);
		List<Book> viewBooks;
        int page = 1;
        
        if (pageNumberValue != null) {
            try {
                page = Integer.parseInt(pageNumberValue);
            } catch (NumberFormatException e) {
            	RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR);
            	dispatcher.forward(request, response);
            }
        }
        
        try {
        	viewBooks = getListBook(parserParam, page);
            request.setAttribute(PARSER, parserParam);
            request.setAttribute(LIST, viewBooks);

            RequestDispatcher dispatcher = request.getRequestDispatcher(XML_VIEWER);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException |ServiceException e) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR);
        	dispatcher.forward(request, response);
        }
        
	}
	private List<Book> getListBook(String parserParam, int page) 
			throws ServiceException {
        Service service = ServiceFactory.getInstance().getService();
        	List<Book> allBooks = service.parse(parserParam);
        	int offset = BOOKS_ON_PAGE * (page - 1);
        	List<Book> viewBooks = allBooks.subList(offset, BOOKS_ON_PAGE + offset);
            return viewBooks;
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
