package com.automation.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserManager {

    private static BrowserManager instance=null;
    private WebDriver driver=null;
    public String browserType;
    private BrowserManager(){

    }
    public static BrowserManager getInstance()
    {
        if(instance==null)
            instance= new BrowserManager();
        return instance;
    }

    public synchronized WebDriver getDriver()
    {
        return driver;
    }

    public synchronized void initializeWebdriver(String browserName){

        browserType=browserName;
        /*if(this.browserType.equalsIgnoreCase("chrome"))
        {
            driver= new ChromeDriver();

        }*/
        ChromeOptions options= new ChromeOptions();
        switch(this.browserType.toLowerCase())
        {
            case "chrome":
            options.addArguments("chrome.switches","--disable-extension");
            options.addArguments("--disable-browser-side-navigation");
            options.setAcceptInsecureCerts(true);
            options.addArguments("--incognito");
            options.setExperimentalOption("useAutomationExtension",false);
          //  System.setProperty("webdriver.chrome.driver", ".//driver//chromedriver");
            driver= new ChromeDriver(options);
            driver.manage().window().maximize();

        }

    }

    public synchronized  void closeBrowser()
    {
        if(BrowserManager.getInstance().getDriver()!=null)
            BrowserManager.getInstance().getDriver().quit();
    }
}
