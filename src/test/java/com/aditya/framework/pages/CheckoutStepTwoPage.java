package com.aditya.framework.pages;

import com.aditya.framework.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends BasePage{
    @FindBy (css = ".title")
    private WebElement checkoutPageTwoTitle;
    @FindBy (id = "finish")
    private WebElement finishBtn;
    public CheckoutStepTwoPage(DriverFactory driverFactory) {
        super(driverFactory);
    }
    public boolean isLoaded(){
        return isDisplayed(checkoutPageTwoTitle) && checkoutPageTwoTitle.getText().equals("Checkout: Overview");
    }
    public void finish(){
        click(finishBtn);
    }
}
