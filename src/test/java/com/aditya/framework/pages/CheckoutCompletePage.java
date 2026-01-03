package com.aditya.framework.pages;

import com.aditya.framework.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage{
    @FindBy (css = ".title")
    private WebElement checkoutCompletePageTitle;
    @FindBy (css = ".complete-header")
    private WebElement successHeader;
    public CheckoutCompletePage(DriverFactory driverFactory) {
        super(driverFactory);
    }
    public boolean isLoaded(){
        return isDisplayed(checkoutCompletePageTitle) && checkoutCompletePageTitle.getText().equals("Checkout: Complete!");
    }
    public String getSuccessMessage(){
        return getText(successHeader);
    }
}
