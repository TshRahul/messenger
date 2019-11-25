package com.messenger.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BasicUtility {
	
	public String getValuesFromPropertyFile(String key) throws IOException {
	
	Properties property = new Properties();
	String propertyFileName = "testQueries.properties";
	
	InputStream stream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
	if(stream != null) {
		property.load(stream);
	}else {
		throw new FileNotFoundException("Property file : " + propertyFileName + " not found in classpath");
	}
	
	return property.getProperty(key);

}
}

