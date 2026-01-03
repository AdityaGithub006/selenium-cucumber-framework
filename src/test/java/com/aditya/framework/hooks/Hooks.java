package com.aditya.framework.hooks;

import com.aditya.framework.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static Logger log = LoggerFactory.getLogger(Hooks.class);
    private final DriverFactory driverFactory;

    // PicoContainer injects this automatically
    public Hooks(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    @Before
    public void beforeScenario(Scenario scenario){
        log.info("START Scenario='{}' | Thread={}", scenario.getName(), Thread.currentThread().getName());
        driverFactory.start();
    }

    @After
    public void afterScenario(Scenario scenario){
        try {
            if (scenario.isFailed() && driverFactory.getDriver() != null){
                byte[] screenshot =((TakesScreenshot)driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
                log.error("FAILED Scenario='{}' | Thread={}", scenario.getName(), Thread.currentThread().getName());
            }
            else {
                log.info("END Scenario='{}' | Thread={}", scenario.getName(), Thread.currentThread().getName());
            }
        }
        finally {
            driverFactory.quit();
        }
    }
}
