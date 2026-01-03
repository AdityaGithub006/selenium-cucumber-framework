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
            Boolean headless = config.getBool("headless");

            if (!browser.equalsIgnoreCase("chrome")) {
                throw new RuntimeException("Only chrome supported right now. Found: " + browser);
            }

            ChromeOptions chromeOptions = new ChromeOptions();
            // Use a clean session each run (avoid your real Chrome profile settings/popups)
            chromeOptions.addArguments("--incognito");
            if (headless) {
                chromeOptions.addArguments("--headless=new");
            }
            driver.set(new ChromeDriver(chromeOptions));
            driver.get().manage().window().maximize();

            // Block password manager + related UI prompts             // Turn OFF password manager + credentials service
//            Map<String, Object> prefs = new HashMap<>();
//            prefs.put("credentials_enable_service", false);
//            prefs.put("profile.password_manager_enabled", false);

            // Optional: reduce other “Chrome automation” distractions
//            prefs.put("profile.default_content_setting_values.notifications", 2);

//            chromeOptions.setExperimentalOption("prefs", prefs);

            // Disable password leak detection feature (this popup)
//            chromeOptions.addArguments("--disable-features=PasswordLeakDetection,AutofillPasswordLeakDetection");

//            chromeOptions.addArguments("--user-data-dir=target/chrome-profile");

        }
    }
    public void quit(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}
