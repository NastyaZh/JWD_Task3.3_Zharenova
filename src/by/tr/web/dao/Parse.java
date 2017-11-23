package by.tr.web.dao;

import java.util.List;

import by.tr.web.dao.exception.DAOException;
import by.tr.web.entity.Book;;

public interface Parse {
	List<Book> doParse() throws DAOException;
}
