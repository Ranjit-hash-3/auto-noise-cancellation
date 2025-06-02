package com.automation.testreport;

import com.automation.manager.BrowserManager;
import com.automation.scenario.ScenarioDetails;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

public class FrameworkUtil {
    public static String getTestId(Scenario scenario)
    {
        ScenarioDetails sd=null;
        Collection<String> tags= scenario.getSourceTagNames();
        sd= ScenarioDetails.getScenarioDetailsInstance();
        sd.setScenarioName(scenario.getName());
        String temp=null;
        for(String singletag:tags)
        {
            if(singletag.startsWith("@Test_Id_")){
                temp=singletag.split("@Test_Id_")[1];
                sd.setTestID(temp);
            }
        }
        return sd.getTestID();
    }
    public static String getEnvironmentDetails()
    {
        WebDriver driver = BrowserManager.getInstance().getDriver();
        return driver.getCurrentUrl();
    }
}
