package com.rana.server.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ApplicationProperties {

    private static ApplicationProperties applicationProperties;
    private Properties properties;

    private ApplicationProperties() {
        String resources = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        properties = new Properties();
        try {
            properties.load(new FileReader(resources + "application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ApplicationProperties getInstance() {
        if (Objects.isNull(applicationProperties)) {
            applicationProperties = new ApplicationProperties();
        }
        return applicationProperties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

}
