package com.Interview.pageObjects;

import com.Interview.web.BasePageWeb;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;


public class cartPage extends BasePageWeb {

    @FindBy(xpath="(//div[@id='layer_cart']//following-sibling::h2)[1]")
    WebElement successMsg;
    @FindBy(xpath="(//div[@id='layer_cart']//following-sibling::h2)[1]//following::div[2]/span[1]")
    WebElement dressName;
    @FindBy(xpath="(//div[@id='layer_cart']//following-sibling::h2)[1]//following::span[@id='layer_cart_product_quantity']")
    WebElement dressQuantity;
    @FindBy(xpath="(//div[@id='layer_cart']//following-sibling::h2)[1]//following::span[@id='layer_cart_product_price']")
    WebElement totalDressPrice;

    @Step("Verify cart if dress is successfully added")
    public void verifyCart(String expectedDressName, String maxValue) {

        highLight(successMsg);
        highLight(dressName);
        highLight(dressQuantity);
        highLight(totalDressPrice);
        String successMessage = successMsg.getText();
        Assert.assertEquals(successMessage,"Product successfully added to your shopping cart");
        Assert.assertEquals(dressName.getText(),expectedDressName);
        Assert.assertEquals(dressQuantity.getText(),"1");
        Assert.assertEquals((totalDressPrice.getText()), "$"+ maxValue);

    }
}
