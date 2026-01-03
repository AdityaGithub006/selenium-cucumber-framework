package com.aditya.framework.data;

import java.io.InputStream;
import java.util.Properties;

public class TestDataReader {

    private static final Properties data = new Properties();

    static {
        try (InputStream is =
                     TestDataReader.class
                             .getClassLoader()
                             .getResourceAsStream("testdata/users.properties")) {

            if (is == null) {
                throw new RuntimeException("users.properties not found");
            }
            data.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static String get(String key) {
        String val = data.getProperty(key);
        if (val == null) {
            throw new RuntimeException("Missing test data key: " + key);
        }
        return val.trim();
    }
}