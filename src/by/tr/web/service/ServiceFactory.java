package by.tr.web.service;

import by.tr.web.service.impl.ServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();

    private Service service = new ServiceImpl();

    private ServiceFactory(){
    }
    public static ServiceFactory getInstance(){
        return instance;
    }

    public Service getService() {
        return service;
    }
}
