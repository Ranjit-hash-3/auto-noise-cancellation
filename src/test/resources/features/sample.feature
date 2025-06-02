Feature: Login to HRM Application

  Background:
    Given user loads data "./DataFiles/TestData.xlsx"
    Given user launches url "https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm" on browser "chrome"
    And user waits for "2" seconds

  @demo @Test_Id_TC1
Scenario: Login with valid credentials

    When users enters text "#DataOne" using locator type "name" and value "name"
    When users enters text "#DataTwo" using locator type "name" and value "lastname"
    And user clicks on element using locator type "xpath" and value ".//input[@type='radio' and @value='Male']"
    And user waits for "10" seconds

  @demo @Test_Id_TC2
  Scenario: Login with invalid credentials
    When users enters text "#DataOne" using locator type "name" and value "name"
    When users enters text "#DataTwo" using locator type "name" and value "lastname"
    And user clicks on element using locator type "xpath" and value ".//input[@type='radio' and @value='Male']"
    And user waits for "10" seconds

  @demo @Test_Id_TC3
  Scenario: pass execution
    When users enters text "#DataOne" using locator type "name" and value "name"



