package com.automation.scenario;

import java.util.HashMap;
import java.util.Map;

public class ScenarioDetails {
    private String scenarioName;
    private String testID;
    private String jiraID;
    private String scenarioStatus;
    private Map<String,String> scenarioData= new HashMap<>();
    private static ScenarioDetails scenarioDetails;

    private ScenarioDetails(){

    }

    public static ScenarioDetails getScenarioDetailsInstance(){
        if(scenarioDetails==null)
            scenarioDetails=new ScenarioDetails();
        return  scenarioDetails;
    }

    public String getScenarioName(){
        return scenarioName;
    };
    public void setScenarioName(String scenarioName)
    {
        this.scenarioName=scenarioName;
    }

    public String getTestID(){
        return  testID;
    }

    public void setTestID(String testID){
        this.testID=testID;
    }

    public Map<String,String> getScenarioData(){
        return scenarioData;
    };

    public void setScenarioData(Map<String,String> scenarioData){
        this.scenarioData=scenarioData;
    }

    public String getScenarioStatus()
    {
        return  scenarioStatus;
    }

    public void setScenarioStatus(String scenarioStatus)
    {
        this.scenarioStatus=scenarioStatus;
    }

    //this will return scenario test data for a particular column for current test case
    //input "column" name
    //output : corresponding column value for that particular test row
    public static String getTestDataForKeyInMap(String key)
    {
        return scenarioDetails.scenarioData.get(key);
    }




}
