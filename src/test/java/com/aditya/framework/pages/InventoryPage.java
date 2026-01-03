package com.aditya.framework.pages;

import com.aditya.framework.driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InventoryPage extends BasePage{
    @FindBy (css = ".title")
    private WebElement pageTitle;
    @FindBy (css = ".inventory_item")
    private List<WebElement> inventoryItems;
    @FindBy (css = ".inventory_item_name")
    private List<WebElement> itemNames;
    @FindBy (css = ".shopping_cart_link")
    private WebElement cartLink;
    @FindBy (css = ".shopping_cart_badge")
    private WebElement cartBadge;
    @FindBy (id = "react-burger-menu-btn")
    private WebElement hamburgerMenu;
    @FindBy(css = "select.product_sort_container")
    private WebElement sortDropdown;
    @FindBy(css = ".inventory_item_price")
    private List<WebElement> itemPrices;
    @FindBy (id = "logout_sidebar_link")
    private WebElement logoutLink;
    public InventoryPage(DriverFactory driverFactory) {
        super(driverFactory);
    }

    public String getTitleText(){
        return getText(pageTitle);
    }
    public int getItemCount(){
        return inventoryItems.size();
    }
    public boolean isLoaded(){
        return isDisplayed(pageTitle) && getTitleText().equals("Products");
    }
    public void sortBy (String visibleText){
        waitVisible(sortDropdown);
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(visibleText);
    }
    public List<Double> getDisplayedPrices(){
        List<Double> prices = new ArrayList<>();
        for (WebElement price : itemPrices){
            prices.add
                    (Double.parseDouble
                            (price.getText().replace("$","").trim()));
        }
        return prices;
    }
    public List<String> getDisplayedNames(){
        List<String> names = new ArrayList<>();
        for (WebElement name: itemNames){
            names.add(name.getText().trim());
        }
        return names;
    }
    public boolean isSortedCorrectly(String sortType){
        switch (sortType){
            case "NAME_ASC":
                return isNameSortedAsc();
            case "NAME_DESC":
                return isNameSortedDesc();
            case "PRICE_ASC":
                return isPriceSortedAsc();
            case "PRICE_DESC":
                return isPriceSortedDesc();
            default:
                throw new IllegalArgumentException("Unknown sort type: " + sortType);
        }
    }
    private boolean isNameSortedAsc(){
        List<String> actual = getDisplayedNames();
        List<String> sorted = new ArrayList<>(actual);
        Collections.sort(sorted);
        return actual.equals(sorted);
    }

    private boolean isNameSortedDesc(){
        List<String> actual = getDisplayedNames();
        List<String> sorted = new ArrayList<>(actual);
        sorted.sort(Collections.reverseOrder());
        return actual.equals(sorted);
    }

    private boolean isPriceSortedAsc(){
        List<Double> actual = getDisplayedPrices();
        List<Double> sorted = new ArrayList<>(actual);
        Collections.sort(sorted);
        return actual.equals(sorted);
    }

    private boolean isPriceSortedDesc(){
        List<Double> actual = getDisplayedPrices();
        List<Double> sorted = new ArrayList<>(actual);
        sorted.sort(Collections.reverseOrder());
        return actual.equals(sorted);
    }

    public boolean isSortedLowToHigh(){
        List<Double> actual = getDisplayedPrices();
        List<Double> sorted = new ArrayList<>(actual);
        Collections.sort(actual);
        return actual.equals(sorted);
    }
    public void addToCart (String productName){
        for (int i = 0; i<itemNames.size(); i++){
            if (itemNames.get(i).getText().equals(productName)){
                WebElement parentCard = itemNames.get(i).findElement(By.xpath(("./ancestor::div[@class='inventory_item']")));
                WebElement addButton = parentCard.findElement(By.xpath(".//button[text()='Add to cart']"));
                click(addButton);
                return;
            }
        }
        throw new RuntimeException("Product not found" + productName);
    }
    public int getCartCount(){
        if (!isDisplayed(cartBadge)){
            return 0;
        }
        String txt = getText(cartBadge).trim();
        return txt.isEmpty() ? 0 : Integer.parseInt(txt);
    }
    public void openCart(){
        click(cartLink);
    }
    public void logout(){
        click(hamburgerMenu);
        click(logoutLink);
    }
}
