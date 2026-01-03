package com.aditya.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun.txt",
        glue = {"com.aditya.framework"},
        plugin = {
                "pretty",
                "html:target/cucumber-rerun-report.html",
                "json:target/cucumber-rerun-report.json"
        },
        monochrome = true
)

public class RerunTestRunner extends AbstractTestNGCucumberTests {
}
