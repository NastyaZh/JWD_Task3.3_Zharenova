package by.tr.web.service;

import by.tr.web.service.exception.ServiceException;

import java.util.List;

import by.tr.web.entity.Book;

public interface Service {
	List<Book> parse(String typeParser) throws ServiceException;
}
