package com.aditya.framework.tests;

import com.aditya.framework.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {
    @Test
    public void openGoogle(){
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
    }

}
