package com.aditya.framework.pages;

import com.aditya.framework.config.ConfigReader;
import com.aditya.framework.driver.DriverFactory;
import com.aditya.framework.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    private final ConfigReader config;
    @FindBy (id = "user-name")
    private WebElement username;
    @FindBy (id = "password")
    private WebElement password;
    @FindBy (id = "login-button")
    private WebElement loginButton;
    @FindBy (css = "h3[data-test='error']")
    private WebElement errorBox;
    public LoginPage(DriverFactory driverFactory, ConfigReader config) {
        super(driverFactory);
        this.config = config;
    }

    public void openURL(){
        driver.get(config.get("base.url"));
    }
    public void login(User user){
        type(username, user.getUsername());
        type(password, user.getPassword());
        click(loginButton);
    }
    public String getErrorMessageIfAny(){
        if (!isDisplayed(errorBox)) return null;
        return getText(errorBox);
    }
}
