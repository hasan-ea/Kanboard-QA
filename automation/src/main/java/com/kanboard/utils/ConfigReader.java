package com.kanboard.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final String TEST_CONFIG_PATH = "config/test-config.properties";
    private static final String CREDENTIALS_PATH = "config/credentials.local.properties";

    private static final Properties testConfigProperties = new Properties();
    private static final Properties credentialProperties = new Properties();

    static {
        loadProperties(TEST_CONFIG_PATH, testConfigProperties);
        loadProperties(CREDENTIALS_PATH, credentialProperties);
    }

    private ConfigReader() {
    }

    private static void loadProperties(String path, Properties properties) {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new RuntimeException("Properties file not found: " + path);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + path, e);
        }
    }

    public static String getProperty(String key) {
        return testConfigProperties.getProperty(key);
    }

    public static String getCredential(String key) {
        return credentialProperties.getProperty(key);
    }
}