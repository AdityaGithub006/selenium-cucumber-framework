package com.aditya.framework.pages;

import com.aditya.framework.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{

    @FindBy (css = "span.title")
    private WebElement cartTitle;
    @FindBy (css = ".cart_item")
    private List<WebElement> cartItem;
    @FindBy (css = ".inventory_item_name")
    private List<WebElement> itemName;
    @FindBy (id = "checkout")
    private WebElement checkoutBtn;
    public CartPage(DriverFactory driverFactory) {
        super(driverFactory);
    }
    public boolean isLoaded(){
        return isDisplayed(cartTitle) && cartTitle.getText().equals("Your Cart");
    }
    public boolean hasItem(String productName){
        for (WebElement item : itemName){
            if (item.getText().equals(productName))
                return true;
        }
        return false;
    }
    public void checkOut(){
        click(checkoutBtn);
    }
}
