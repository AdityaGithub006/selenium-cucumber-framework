package com.aditya.framework.steps;

import com.aditya.framework.factory.UserFactory;
import com.aditya.framework.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginNegativeSteps {

    private final LoginPage loginPage;
    private final UserFactory userFactory;

    public LoginNegativeSteps(LoginPage loginPage, UserFactory userFactory) {
        this.loginPage = loginPage;
        this.userFactory = userFactory;
    }

    @Then("login should fail with {string}")
    public void loginShouldFailWith(String expectedMessage) {
        String actual = loginPage.getErrorMessageIfAny();
        Assert.assertNotNull(actual, "Expected an error message but got null (login may have succeeded)");
        Assert.assertEquals(actual.trim(), expectedMessage.trim(), "Error message mismatch");
    }
}