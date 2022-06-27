package com.Interview.web.util.webdriver;

import com.Interview.core.util.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverExecutableSetup {
    public void setupDriver(String browserName){
        boolean useWebDriverManager = PropertyUtils.getPropertyBoolean("com.Interview.web.usewebdrivermanager");

        if(useWebDriverManager){
            setupDriverUsingWDM(browserName);
        } else {
            setupDriverUsingOsDefaults();
        }
    }

    void setupDriverUsingWDM(String browserName){
        switch (browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "internetexplorer":
                WebDriverManager.iedriver().setup();
                break;
            default:
                throw new IllegalStateException("DriverSetup.setupDriverUsingWDM : Invalid browser value: " + browserName);
        }
    }

    void setupDriverUsingOsDefaults(){
        String os = PropertyUtils.getPropertyString("os.name");

        PropertyUtils.setProperty("webdriver.chrome.driver", getChromeDriverPath(os));
        PropertyUtils.setProperty("webdriver.gecko.driver", getGeckoDriverPath(os));
        PropertyUtils.setProperty("webdriver.ie.driver", getIeDriverPath(os));
    }

    private String getIeDriverPath(String os) {
        if (os.contains("Window")) {
            return PropertyUtils.getPropertyString("com.Interview.web.windows.ie.driver");
        } else if (os.contains("Mac")) {
            return PropertyUtils.getPropertyString("com.Interview.web.mac.ie.driver");
        } else {
            throw new IllegalStateException("DriverSetup.getIeDriverPath : Invalid OS value: " + os);
        }
    }

    private String getGeckoDriverPath(String os) {
        if (os.contains("Window")) {
            return PropertyUtils.getPropertyString("com.Interview.web.windows.gecko.driver");
        } else if (os.contains("Mac")) {
            return PropertyUtils.getPropertyString("com.Interview.web.mac.gecko.driver");
        } else {
            throw new IllegalStateException("DriverSetup.getGeckoDriverPath : Invalid OS value: " + os);
        }
    }

    private String getChromeDriverPath(String os) {
        if (os.contains("Window")) {
            return PropertyUtils.getPropertyString("com.Interview.web.windows.chromedriver");
        } else if (os.contains("Mac")) {
            return PropertyUtils.getPropertyString("com.Interview.web.mac.chromedriver");
        } else {
            throw new IllegalStateException("DriverSetup.getChromeDriverPath : Invalid OS value: " + os);
        }
    }
}
