package com.Interview.web;

import com.Interview.core.BaseTest;
import com.Interview.web.util.TestListenerWeb;
import com.Interview.web.util.webdriver.DriverExecutableSetup;
import com.Interview.web.util.webdriver.DriverManager;
import com.Interview.web.util.webdriver.LocalDriverFactory;
import com.Interview.web.util.webdriver.RemoteDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

@Listeners(TestListenerWeb.class)
public class BaseTestWeb extends BaseTest {
    private final String DEFAULT_BROWSER_NAME = "chrome";
    private final String DEFAULT_IS_REMOTE = "false";

    @BeforeSuite(alwaysRun = true)
    @Parameters(value = {"browserName", "isRemote"})
    protected void beforeEachSuiteWeb(@Optional(DEFAULT_BROWSER_NAME) String browserName, @Optional(DEFAULT_IS_REMOTE) boolean isRemote){
        if (!isRemote){
            new DriverExecutableSetup().setupDriver(browserName);
        }
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"browser", "isRemote"})
    protected void beforeEachTestMethodWeb(@Optional(DEFAULT_BROWSER_NAME) String browserName, @Optional(DEFAULT_IS_REMOTE) boolean isRemote) throws MalformedURLException {
        if (!isRemote){
            WebDriver driver = LocalDriverFactory.createInstance(browserName);
            DriverManager.setWebDriver(driver);
        }
        else
        {
            WebDriver driver = RemoteDriverFactory.createInstance(browserName);
            DriverManager.setWebDriver(driver);

        }
    }

    @AfterMethod(alwaysRun = true)
    protected void afterEachMethodWeb() {
        WebDriver driver = DriverManager.getDriver();
        driver.switchTo().defaultContent();
        driver.quit();
    }
}
