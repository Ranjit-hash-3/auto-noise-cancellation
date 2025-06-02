package com.automation.hooks;

import com.automation.manager.BrowserManager;
import com.automation.scenario.ScenarioDetails;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.IOException;
import java.util.Collection;




public class Hooks {
    ScenarioDetails sd=null;

    @AfterStep
    public void addScreenshot(Scenario scenario) throws IOException {
//        WebDriver driver = BrowserManager.getInstance().getDriver();
//        if (driver != null){
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
//        scenario.attach(fileContent, "image/png", "screenshot");
//        }
    }
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("this will execute before every scenario");
        System.out.println("scenario currently executing : "+scenario.getName());
       // clearExistingRows();
        Collection<String> tags= scenario.getSourceTagNames();
        sd=ScenarioDetails.getScenarioDetailsInstance();
        sd.setScenarioName(scenario.getName());
        String temp=null;
        for(String singletag:tags)
        {
            if(singletag.startsWith("@Test_Id_")){
                temp=singletag.split("@Test_Id_")[1];
                sd.setTestID(temp);
            }
        }

    }

    @After(order=1)
    public void afterScenario(Scenario scenario){
        BrowserManager.getInstance().getDriver().close();
    }
}
