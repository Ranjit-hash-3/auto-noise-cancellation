package com.automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.automation.logs.ExcelClearRows.preExecutionSetup;


@CucumberOptions( features = {"src/test/resources/features/FunctionalTestCase.feature"},
        glue = {"com.automation"},
        dryRun = false,
        tags = "@demo",
        plugin = { "pretty", "json:target/cucumber/Cucumber.json",
                "junit:target/cucumber/Cucumber.xml",
                "html:target/cucumber/reports.html"})

@Test
public class TestRunner extends AbstractTestNGCucumberTests {
    @BeforeClass
    public static void setup() throws IOException {
        System.out.println("clearing the existing analysis rows");
        preExecutionSetup();
    }

}