package com.Interview.core.util;

import java.security.InvalidParameterException;

public class PropertyUtils {

    private PropertyUtils() {
        throw new IllegalStateException("PropertyUtils");
    }

    public static String getPropertyString(String key){
        String value = System.getProperty(key);
        if (value == null){
            throw new InvalidParameterException("PropertyUtils.getPropertyString: Property not found with key: " + key);
        }
        return value;
    }

    public static int getPropertyInteger(String key){
        String value = getPropertyString(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("PropertyUtils.getPropertyInteger: Property value cannot be parsed to integer: " + value);
        }
    }

    public static boolean getPropertyBoolean(String key){
        String value = getPropertyString(key).toLowerCase();

        switch (value) {
            case "true" :
                return true;
            case "false" :
                return false;
            default:
                throw new InvalidParameterException("PropertyUtils.getPropertyBoolean: Property value cannot be parsed to boolean: " + value);
        }
    }

    public static void setProperty(String key, String value){
        System.setProperty(key, value);
    }
}
