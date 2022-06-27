package com.Interview.pageObjects;
import com.Interview.core.util.PropertyUtils;
import com.Interview.web.BasePageWeb;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePageWeb {

    private @FindBy (xpath="(//a[@title='Dresses'])[2]")
    WebElement dressMenu;

    @Step("Navigate to an URL")
    public LandingPage navigateToAnURL(){
    driver.get(PropertyUtils.getPropertyString("WEB-URL"));
    waitUntilClickable(dressMenu);
    return new LandingPage();
    }

}
