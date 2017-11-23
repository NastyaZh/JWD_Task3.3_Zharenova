package by.tr.web.dao.exception;

public class DAOException extends Exception{
	private static final long serialVersionUID = -9092910654631937229L;

	public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable ex) {
        super(message, ex);
    }

    public DAOException(Throwable ex) {
        super(ex);
    }
}
