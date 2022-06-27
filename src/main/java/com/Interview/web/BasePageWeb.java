package com.Interview.web;

import com.Interview.core.util.PropertyUtils;
import com.Interview.core.util.logging.SimpliLogger;
import com.Interview.web.util.webdriver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.Set;

public class BasePageWeb {
    protected WebDriver driver;
    private  int waitTimeout;
    private Wait<WebDriver> wait;
    public static Set<String> openWindows;

    protected BasePageWeb() {
        this.driver = DriverManager.getDriver();
        this.waitTimeout = PropertyUtils.getPropertyInteger("com.Interview.web.implicitlywait");
        this.wait = new WebDriverWait(driver, waitTimeout);
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    /**
     * Waits for element to be visible. Timeout set by system property - com.simplitest.web.implicitlywait
     * @param locator Element or By to wait for
     */
    protected void waitUntilPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            SimpliLogger.trace("BasePageWeb.waitUntilPresent (!>" + waitTimeout + "s) | ERROR: Timeout expired");
            Assert.fail(locator+" is not present");
            throw e;
        }
    }

    /**
     * Waits for element to be clickable. Timeout set by system property - com.simplitest.web.implicitlywait
     * @param element Element to wait for
     */
    protected void waitUntilClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            SimpliLogger.trace("BasePageWeb.waitUntilClickable (!>" + waitTimeout + "s) | ERROR: Timeout expired");
            Assert.fail(element+" is not visble");
            throw e;
        }
    }

//    public void selectDDValue(WebElement ele, String str){
//        WebElement drpd = driver.findElement(By.xpath(ele));
//        Select sel = new Select(drpd);
//        sel.selectByVisibleText("M");
//        SimpliLogger.trace("Selecteddrop down value: " + str);
//    }

    protected void dropdownSelectByText(WebElement element, String selection) {
        SimpliLogger.trace("BasePageWeb.dropdownSelectByText: Select value from dropdown: '" + selection);

        new Select(element).selectByVisibleText(selection);
        SimpliLogger.trace("BasePageWeb.dropdownSelectByText: Select value from dropdown: '" + selection + " | DONE");
    }

    /**
     * Waits for element to be visible. Timeout set by system property - com.simplitest.web.implicitlywait
     * @param element Element or By to wait for
     */
    protected void waitUntilVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            SimpliLogger.trace("BasePageWeb.waitUntilVisible (!>" + waitTimeout + "s) | ERROR: Timeout expired");
            Assert.fail(element+" is not visble");
            throw e;
        }
    }
    /*
     * To select a tab inside a page by providing the tab text
     */
    /**
     * Clicks given element using JavaScript
     * Method may be needed when GUI does not respond to standard BasePageWeb.click() method
     * @param element Element to click
     */
    protected void clickWithJavascript(WebElement element) {
        try {
            SimpliLogger.trace("BasePageWeb.clickWithJavascript: Clicking element");
            moveToElement(element);
            waitUntilClickable(element);
            JavascriptExecutor executor = ((JavascriptExecutor) driver);
            executor.executeScript("arguments[0].click();", element);
            SimpliLogger.trace("BasePageWeb.clickWithJavascript: Clicking | DONE");
        }catch(Exception e) {
            Assert.fail(element + " unable to click");
        }
    }
    /**
     * Enters text into given input
     * @param element Element to receive text input
     * @param text Text to enter
     */
    protected void writeText(WebElement element, String text) {
        try {
            SimpliLogger.trace("BasePageWeb.writeText: Entering text: " + text);
            waitUntilClickable(element);
            clearText(element);
            element.sendKeys(text);
            SimpliLogger.trace("BasePageWeb.writeText: Entering text: " + text + " | DONE");
        }catch(Exception e) {
            Assert.fail(element + " unable write text");
        }
    }

    /**
     * Clear text from given input element
     * @param element Element to clear
     */
    protected void clearText(WebElement element) {
        try{
            SimpliLogger.trace("BasePageWeb.clearText: Clearing element");
            waitUntilClickable(element);
            element.clear();
            SimpliLogger.trace("BasePageWeb.clearText: Clearing element | DONE");
        }catch(Exception e) {
            Assert.fail(element + " unable clear text");
        }
    }


    /**
     * Clicks given element
     * @param element Element to click
     */

    protected void click(WebElement element) {
        try{
            SimpliLogger.trace("BasePageWeb.click: Clicking element");
        waitUntilClickable(element);
       moveToElement(element);
        element.click();
            SimpliLogger.trace("BasePageWeb.click: Clicking element | DONE");
        }catch(Exception e) {
            Assert.fail(element + " unable to click");
        }
    }

    public static void waitForPageLoad() {
        WebDriver driver =DriverManager.getDriver();
        ExpectedCondition<Boolean> expect = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver,180);
        try {
            wait.until(expect);
        } catch (Exception E) {

        }
    }

    public static void moveToElement(WebElement element) {
        try {
            WebDriver driver = DriverManager.getDriver();
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
        } catch (Exception e) {
            SimpliLogger.trace("scrollIntoView failed: " + element);
        }
    }

   public  void highLight(WebElement element){
        try {
//            waitUntilVisible(element);
            WebDriver driver = DriverManager.getDriver();
            Actions Act = new Actions(driver);
            Act.moveToElement(element).build().perform();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background: yellow; border:2px solid red;');", element);
        } catch (Exception e) {
            Assert.fail(element+" is not visble");
        }
    }


}
