package com.aditya.framework.steps;

import com.aditya.framework.driver.DriverFactory;
import com.aditya.framework.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class PurchaseSteps {
    private final InventoryPage inventoryPage;
    private final CartPage cartPage;
    private final CheckoutStepOnePage stepOne;
    private final CheckoutStepTwoPage stepTwo;
    private final CheckoutCompletePage complete;

    public PurchaseSteps(InventoryPage inventoryPage, CartPage cartPage, CheckoutStepOnePage stepOne, CheckoutStepTwoPage stepTwo, CheckoutCompletePage complete) {
        this.inventoryPage = inventoryPage;
        this.cartPage = cartPage;
        this.stepOne = stepOne;
        this.stepTwo = stepTwo;
        this.complete = complete;
    }
    @When("I add {string} to cart")
    public void addItem (String productName){
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page not loaded");
        inventoryPage.addToCart(productName);
        Assert.assertTrue(inventoryPage.getCartCount()>=1, "Cart badge not updated");
    }
    @And ("I open cart")
    public void openCart(){
        inventoryPage.openCart();
        Assert.assertTrue(cartPage.isLoaded(), "Cart page not loaded");
    }
    @Then("cart should contain {string}")
    public void cartShouldContain (String productName){
        Assert.assertTrue(cartPage.hasItem(productName), "cart missing item: " + productName);
    }
    @When("I checkout with firstName {string} lastName {string} postalCode {string}")
    public void checkOut (String fn, String ln, String zip){
        cartPage.checkOut();
        Assert.assertTrue(stepOne.isLoaded(), "Checkout page one not loaded");
        stepOne.checkOut(fn, ln, zip);
        Assert.assertTrue(stepTwo.isLoaded(), "Checkout page two not loaded");
        stepTwo.finish();
    }
    @Then("order should be placed successfully")
    public void orderPlacedSuccessfully(){
        Assert.assertTrue(complete.isLoaded(), "Checkout complete not loaded");
        Assert.assertTrue(
                complete.getSuccessMessage().toUpperCase().contains("THANK YOU"),
                "Success message not found: " + complete.getSuccessMessage());
    }
}
