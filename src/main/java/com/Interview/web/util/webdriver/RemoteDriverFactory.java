package com.Interview.web.util.webdriver;

import com.Interview.core.util.PropertyUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteDriverFactory {
    private RemoteDriverFactory() {
        throw new IllegalStateException("RemoteDriverFactory");
    }

    public static WebDriver createInstance(String browserName) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, browserName);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
        cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        cap.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
        WebDriver driver = new RemoteWebDriver(new URL("http://172.16.2.1:4444/wd/hub"), cap);
        setDriverProperties(driver);
        return driver;
    }


    private static void setDriverProperties(WebDriver driver){
        // Get values from properties file
        int pageLoadTimeout = PropertyUtils.getPropertyInteger("com.Interview.web.pageloadtimeout");
        int implicitlyWait = PropertyUtils.getPropertyInteger("com.Interview.web.implicitlywait");

        // Set driver timeout values and maximise window
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
