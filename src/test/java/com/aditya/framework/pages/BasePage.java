package com.aditya.framework.pages;

import com.aditya.framework.driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class BasePage {
    protected final DriverFactory driverFactory;
    protected final WebDriver driver;
    protected final JavascriptExecutor js;
    protected final FluentWait<WebDriver> wait;

    public BasePage(DriverFactory driverFactory){
        this.driverFactory = driverFactory;
        this.driver = driverFactory.getDriver();
        this.js = (JavascriptExecutor) driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);
        // IMPORTANT: initializes all @FindBy fields in the child class too
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected WebElement waitClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView({block:'center'})", element);
    }
    protected void click(WebElement element){
        waitClickable(element);
        scrollIntoView(element);
        try {
            element.click();
        }
        catch (ElementClickInterceptedException e){
            js.executeScript("arguments[0].click()", element);
        }
    }
    protected void type(WebElement element, String input){
        waitVisible(element);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(input);
    }
    protected String getText(WebElement element){
        return waitVisible(element).getText();
    }
    protected boolean isDisplayed(WebElement element){
        try {
            return element.isDisplayed();
        }
        catch (Exception e){
            return false;
        }
    }
}
