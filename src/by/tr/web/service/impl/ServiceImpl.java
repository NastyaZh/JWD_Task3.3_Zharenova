package by.tr.web.service.impl;

import java.util.List;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.Parse;
import by.tr.web.dao.exception.DAOException;
import by.tr.web.entity.Book;
import by.tr.web.service.Service;
import by.tr.web.service.exception.ServiceException;

public class ServiceImpl implements Service{

	@Override
	public List<Book> parse(String typeParser) throws ServiceException {
		try {
            Parse parser = DAOFactory.getInstance().getCommand().getParser(typeParser);
            List<Book> list = parser.doParse();
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
	}

}
