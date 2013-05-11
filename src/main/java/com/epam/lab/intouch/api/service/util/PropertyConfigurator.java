package com.epam.lab.intouch.api.service.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyConfigurator {
	private static final String CONFIG_PROPERTY = "intouch-api.properties";

	public static String getProperty(String key) throws IOException {
		Properties property = new Properties();
		property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PROPERTY));
		String prop = property.getProperty(key);

		if (prop == null) {
			prop = "";
		}

		return prop;
	}
}
