package com.automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


@CucumberOptions( features = {"src/test/resources/features/sample.feature"},
        glue = {"com.automation"},
        dryRun = false,
        tags = "@Test_Id_TC1 or @Test_Id_TC2 or @Test_Id_TC3 ",
        plugin = { "pretty", "json:target/cucumber/Cucumber.json",
                "junit:target/cucumber/Cucumber.xml",
                "html:target/cucumber/reports.html"})

@Test
public class TestRunner extends AbstractTestNGCucumberTests {

}