package by.tr.web.dao;

import by.tr.web.dao.command.CommandHelper;

public class DAOFactory {
	private DAOFactory() {}
	
	private static final DAOFactory instance = new DAOFactory();
    
    private final CommandHelper command = new CommandHelper();
    
    public CommandHelper getCommand() {
    	return command;
    }
    public static DAOFactory getInstance() {
    	return instance;
    }
}
