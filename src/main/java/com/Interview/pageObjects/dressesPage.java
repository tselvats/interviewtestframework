package com.Interview.pageObjects;

import com.Interview.web.BasePageWeb;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class dressesPage extends BasePageWeb {

    @FindBy(xpath="//input[@id='quantity_wanted']")
    WebElement qtyWanted;
    @FindBy(xpath="//div[@id='uniform-group_1']//following-sibling::select")
    WebElement sizeWanted;
    @FindBy(xpath="//a[@name='Beige']")
    WebElement colorWanted;
    @FindBy(xpath="//button[@name='Submit']")
    WebElement addToCartButton;
    @FindBy(xpath="//div/h1")
    WebElement orderedDressName;
    @Step("Add highest price dress to cart")
    public void addHighestPriceDressToCart() throws InterruptedException {
        cartPage cp = new cartPage();
        List<WebElement> dressPriceElements = driver.findElements(By.xpath("//div[@class='right-block']//following-sibling::span[@class='price product-price']"));
        float maxValue = 0;
        for (int i = 0; i < dressPriceElements.size(); i++) {
            float dressPrice = Float.parseFloat(dressPriceElements.get(i).getText().replaceAll("[^a-zA-Z0-9.]", ""));
            if (dressPrice > maxValue)
                maxValue = dressPrice;
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        moveToElement(driver.findElement(By.xpath("(//span[contains(text(),'"+maxValue+"')])[2]")));
        By container = By.cssSelector(".button-container");
        waitUntilPresent(container);
        driver.findElement(By.xpath("(//span[contains(text(),'"+maxValue+"')])[2]//following::div[1]/a/span[contains(text(),'More')]")).click();
        waitForPageLoad();

        String expectedDressName = orderedDressName.getText();
        writeText(qtyWanted,"1");
        dropdownSelectByText(sizeWanted,"M");
        colorWanted.click();
        addToCartButton.click();
        Thread.sleep(10000);
        cp.verifyCart(expectedDressName, String.valueOf(maxValue));
    }
}
