package com.aditya.framework.pages;

import com.aditya.framework.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends BasePage{
    @FindBy (css = ".title")
    private WebElement checkoutPageOneTitle;
    @FindBy (id = "first-name")
    private WebElement firstName;
    @FindBy (id = "last-name")
    private WebElement lastName;
    @FindBy (id = "postal-code")
    private WebElement postalCode;
    @FindBy (id = "continue")
    private WebElement continueBtn;
    public CheckoutStepOnePage(DriverFactory driverFactory) {
        super(driverFactory);
    }
    public boolean isLoaded(){
        return isDisplayed(checkoutPageOneTitle) && checkoutPageOneTitle.getText().equals("Checkout: Your Information");
    }
    public void checkOut(String fn, String ln, String zip){
        type(firstName, fn);
        type(lastName, ln);
        type(postalCode, zip);
        click(continueBtn);
    }
}
