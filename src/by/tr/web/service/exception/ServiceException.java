package by.tr.web.service.exception;

public class ServiceException extends Exception{
	private static final long serialVersionUID = -6876843690184882880L;
	
	public ServiceException(){
        super();
    }
    public ServiceException(Exception e){
        super(e);
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message, Exception e){
        super(message,e);
    }
}
