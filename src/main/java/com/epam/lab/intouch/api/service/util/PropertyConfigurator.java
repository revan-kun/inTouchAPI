package com.epam.lab.intouch.api.service.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyConfigurator {
	private static final String CONFIG_PROPERTY = "intouch-api.properties";

	private static final Map<String, String> map = new HashMap<String, String>();;

	public static String getProperty(String key) throws IOException {
		if (map.isEmpty()) {
			Properties property = new Properties();
			property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PROPERTY));

			for (Object propKeyObj : property.keySet()) {
				if (propKeyObj instanceof String) {
					String propKey = (String) propKeyObj;
					map.put(propKey, property.getProperty(propKey));
				}
			}
		}

		String prop = map.get(key);

		if (prop == null) {
			prop = "";
		}
		
		System.out.println("Property: "+prop);

		return prop;
	}
}
