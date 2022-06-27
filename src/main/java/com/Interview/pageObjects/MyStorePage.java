package com.Interview.pageObjects;

import com.Interview.web.BasePageWeb;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;


public class MyStorePage extends BasePageWeb {
public WebDriver driver;

    private @FindBy (xpath="(//a[@title='Dresses'])[2]")
    WebElement dressMenu;
    @Step("Click on Dress Menu")
    public void clickOnDressMenu() throws IOException {
        highLight(dressMenu);
        click(dressMenu);
    }
}
