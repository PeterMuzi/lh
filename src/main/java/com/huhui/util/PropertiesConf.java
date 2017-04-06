package com.huhui.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesConf {
	
	private static Map<String,Properties> properties=new HashMap();
	/*
	 * fileName:/some/pkg/resource.properties
	 */
	public	static Properties get(String fileName){
		if(properties.containsKey(fileName)){
			return properties.get(fileName);
		}
		Properties prop = new Properties();
		InputStream in = PropertiesConf.class.getResourceAsStream(fileName);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		properties.put(fileName, prop);
		return prop;
	}
}
