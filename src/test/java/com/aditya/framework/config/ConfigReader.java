package com.aditya.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try {
            // Default environment = qa
            String env = System.getProperty("env", "qa");
            String path = "config/" + env + ".properties";

            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream(path);

            if (is == null) {
                throw new RuntimeException("Config file not found on classpath: " + path);
            }

            props.load(is);
            is.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public String get(String key) {
        String val = props.getProperty(key);
        if (val == null) throw new RuntimeException("Missing key in config: " + key);
        return val.trim();
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public boolean getBool(String key) {
        return Boolean.parseBoolean(get(key));
    }
}