package com.aditya.framework.steps;

import com.aditya.framework.data.TestDataReader;
import com.aditya.framework.driver.DriverFactory;
import com.aditya.framework.factory.UserFactory;
import com.aditya.framework.pages.InventoryPage;
import com.aditya.framework.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class SmokeSteps {
    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;
    private final UserFactory userFactory;

    public SmokeSteps(LoginPage loginPage, InventoryPage inventoryPage, UserFactory userFactory) {
        this.loginPage = loginPage;
        this.inventoryPage = inventoryPage;
        this.userFactory = userFactory;
    }
    @Given("I login as {string}")
    public void loginTest(String role){
        loginPage.openURL();
        loginPage.login(userFactory.getUser(role));
        System.out.println("Login Attempted");
    }
    @Then("I should land on Inventory page and see products")
    public void verifyInventory(){
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page not loaded / Products title not visible");
        Assert.assertTrue(inventoryPage.getItemCount()>0, "No inventory items found after login");
        System.out.println("Inventory loaded. Items: " + inventoryPage.getItemCount());
    }
    @Then("I logout successfully")
    public void logoutSuccessfully(){
        inventoryPage.logout();
        System.out.println("Logout Attempted");
    }
}
