package com.aditya.framework.steps;

import com.aditya.framework.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SortingSteps {
    private final InventoryPage inventoryPage;

    public SortingSteps(InventoryPage inventoryPage) {
        this.inventoryPage = inventoryPage;
    }
    @When("I sort product by {string}")
    public void sortProducts(String sortOption){
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page not loaded");
        inventoryPage.sortBy(sortOption);
    }
    @Then("Product should be sorted correctly by {string}")
    public void verifySorting(String sortType){
        Assert.assertTrue(inventoryPage.isSortedCorrectly(sortType), "Sorting validation failed for: "+ sortType);
    }
}
