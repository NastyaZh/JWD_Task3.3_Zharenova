package by.tr.web.dao.command;

import java.util.HashMap;
import java.util.Map;

import by.tr.web.dao.Parse;
import by.tr.web.dao.impl.DOMParser;
import by.tr.web.dao.impl.SAXParser;
import by.tr.web.dao.impl.STAXParser;

public class CommandHelper {
	private final Map<String, Parse> map = new HashMap<>();
    
    public CommandHelper() {
    	map.put("DOM", new DOMParser());
    	map.put("SAX", new SAXParser());
    	map.put("STAX", new STAXParser());
    }
    
    public Parse getParser(String typeName) {
		return map.get(typeName);
	}
}
