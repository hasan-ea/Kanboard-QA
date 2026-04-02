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
        loadRequiredProperties(TEST_CONFIG_PATH, testConfigProperties);
        loadOptionalProperties(CREDENTIALS_PATH, credentialProperties);
    }

    private ConfigReader() {
    }

    private static void loadRequiredProperties(String path, Properties properties) {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new RuntimeException("Required properties file not found: " + path);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + path, e);
        }
    }

    private static void loadOptionalProperties(String path, Properties properties) {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                return;
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load optional properties file: " + path, e);
        }
    }

    public static String getProperty(String key) {
        String value = resolveValue(key, testConfigProperties);
        if (isBlank(value)) {
            throw new RuntimeException("Missing config value for key: " + key);
        }
        return value;
    }

    public static String getCredential(String key) {
        String value = resolveValue(key, credentialProperties);
        if (isBlank(value)) {
            throw new RuntimeException("Missing credential value for key: " + key);
        }
        return value;
    }

    private static String resolveValue(String key, Properties fileProperties) {
        String systemValue = System.getProperty(key);
        if (!isBlank(systemValue)) {
            return systemValue;
        }

        String envValue = System.getenv(toEnvKey(key));
        if (!isBlank(envValue)) {
            return envValue;
        }

        String fileValue = fileProperties.getProperty(key);
        if (!isBlank(fileValue)) {
            return fileValue;
        }

        return null;
    }

    private static String toEnvKey(String key) {
        return key.toUpperCase().replace('.', '_');
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}