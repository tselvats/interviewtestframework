package com.Interview;

import com.Interview.pageObjects.LandingPage;
import com.Interview.pageObjects.MyStorePage;
import com.Interview.pageObjects.dressesPage;
import com.Interview.web.BaseTestWeb;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.Interview.web.BasePageWeb.waitForPageLoad;


public class addHighestPriceItemToCartTest extends BaseTestWeb {

    @Test(description = "01_Add Highest Price Item to Cart", groups = {"Mercator"})
    @Description("Add Highest Price Item to Cart)")
    public void TC01_AddHighestPriceItemToCart() throws IOException, InterruptedException {
        LandingPage login = new LandingPage();
        login.navigateToAnURL();
        MyStorePage lp = new MyStorePage();
        dressesPage dp = new dressesPage();
        waitForPageLoad();
        lp.clickOnDressMenu();
        dp.addHighestPriceDressToCart();
    }

}