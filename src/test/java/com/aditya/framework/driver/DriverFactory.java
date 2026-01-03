package com.aditya.framework.driver;

import com.aditya.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final ConfigReader config;
    public DriverFactory(ConfigReader config) {
        this.config = config;
    }

    public WebDriver getDriver(){
        return driver.get();
    }
    public void start(){
        if (driver.get() == null){
            String browser = config.get("browser");
//            Boolean headless = config.getBool("headless");

            if (!browser.equalsIgnoreCase("chrome")) {
                throw new RuntimeException("Only chrome supported right now. Found: " + browser);
            }
            boolean headless = Boolean.parseBoolean(System.getProperty("headless",
                    String.valueOf(config.getBool("headless"))));

            ChromeOptions options = new ChromeOptions();
            // Use a clean session each run (avoid your real Chrome profile settings/popups)
            options.addArguments("--incognito");

            // CI-safe flags
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            driver.set(new ChromeDriver(options));
            driver.get().manage().window().maximize();
        }
    }
    public void quit(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}
