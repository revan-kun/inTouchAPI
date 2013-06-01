package com.epam.lab.intouch.api.service.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class exists for loading and caching properties and provides access to it
 * 
 * @author Zatorsky D.B
 * 
 */
public class PropertyConfigurator {
	private static final String CONFIG_PROPERTY = "intouch-api.properties";

	private static final Map<String, String> map = new HashMap<String, String>();

	/**
	 * This method will return property value in accordance to the key. It will cache properties in memory during first call of the method
	 * 
	 * @param key
	 *            the key of property map
	 * @return value in accordance to the key
	 * @throws IOException
	 */
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

		return prop;
	}
}
