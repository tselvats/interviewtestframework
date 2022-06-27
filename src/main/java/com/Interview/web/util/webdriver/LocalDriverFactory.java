package com.Interview.web.util.webdriver;

import com.Interview.core.util.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class LocalDriverFactory {

    private LocalDriverFactory() {
        throw new IllegalStateException("LocalDriverFactory");
    }

    public static WebDriver createInstance(String browser) {
        WebDriver driver;

        switch (browser){
            case "chrome":
                driver = getChromeDriver(false);
                break;
            case "chrome-headless":
                driver = getChromeDriver(true);
                break;
            case "firefox":
                driver = getFirefoxDriver();
                break;
            case "internetexplorer":
                driver = getInternetExplorerDriver();
                break;
            default:
                throw new IllegalStateException("DriverFactory.getLocalDriver : Invalid browser value: " + browser);
        }

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

    private static ChromeDriver getChromeDriver(boolean headless){
        // To hide 'Timed out receiving message from renderer' error in ChromeDriver 80 / 81
        System.setProperty("webdriver.chrome.silentOutput","true");

        //Create Chrome Options
        ChromeOptions option = new ChromeOptions();
        option.addArguments(
                "--test-type",
                "--start-maximized",
                "--ignore-certificate-errors",
                "--disable-popup-blocking",
                "--enable-javascript",
                //"--incognito",
                "--disable-extensions");
        if (headless) option.addArguments("--headless");
        option.setCapability(ChromeOptions.CAPABILITY, option);
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        //Create driver object for Chrome
        return new ChromeDriver(option);
    }

    private static FirefoxDriver getFirefoxDriver(){
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();

        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);

        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);

        //Set Firefox profile to capabilities
        options.setCapability(FirefoxDriver.PROFILE, profile);

        return new FirefoxDriver(options);
    }

    private static WebDriver getInternetExplorerDriver() {
        //Prepare InternetExplorerDriver using WebDriverManager
        WebDriverManager.iedriver().setup();

        return new InternetExplorerDriver();
    }
}
