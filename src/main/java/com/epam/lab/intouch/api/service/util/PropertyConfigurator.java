package com.epam.lab.intouch.api.service.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfigurator {
	private static final String CONFIG_PROPERTY = "resources/config.properties";

	public static String getProperty(String key) throws IOException {
		Properties property = new Properties();
		property.load(new FileInputStream(CONFIG_PROPERTY));
		return property.getProperty(key);
	}

	public static void setProperty(String key, String value) throws IOException {
		Properties property = new Properties();
		property.load(new FileInputStream(CONFIG_PROPERTY));
		property.setProperty(key, value);
		property.store(new FileOutputStream(CONFIG_PROPERTY), null);
	}

}
