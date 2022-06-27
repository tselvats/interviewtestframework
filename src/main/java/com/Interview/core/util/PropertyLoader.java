package com.Interview.core.util;

import com.Interview.core.util.logging.SimpliLogger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyLoader {

    public void load() {
        // Get new properties from file
        String filePath="src/main/resources/InterviewConfig.properties";
        Properties fileProperties = getPropertiesFromFile(filePath);
        String environment = fileProperties.getProperty("WEB-URL");
        System.out.println("******************");
        System.out.println("Environment : "+environment);
        System.out.println("******************");
        // Load  new properties from file
        loadProperties(fileProperties);
    }

    private Properties getPropertiesFromFile(String path){
        Properties p = new Properties();
        try {
            p.load(new FileReader(new File(path)));
        } catch (IOException e) {
            SimpliLogger.error("PropertyLoader.getPropertiesFromFile: Error reading property file", e);
        }
        return p;
    }

    private void loadProperties(Properties properties){
        for (String name : properties.stringPropertyNames()) {
            String value = properties.getProperty(name);
            System.setProperty(name, value);
        }
    }
}
