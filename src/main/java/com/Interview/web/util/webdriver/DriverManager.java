package com.Interview.web.util.webdriver;

import com.Interview.core.util.logging.SimpliLogger;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private DriverManager() {
        throw new IllegalStateException("DriverManager");
    }

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        WebDriver driver =  webDriver.get();

        if (driver == null) {
            NullPointerException e = new NullPointerException("WebDriver is null.");
            SimpliLogger.error("DriverManager: WebDriver has not be initialised in this thread. Check your code.", e);
            throw e;
        }

        return driver;
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}
