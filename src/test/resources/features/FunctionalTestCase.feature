Feature: Login to HRM Application

  Background:
    Given user loads data "./DataFiles/TestData.xlsx"
    Given user launches url "https://www.saucedemo.com/" on browser "chrome"

  @demo @Test_Id_TC1
Scenario: Login with standard_user credentials

    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"

  @demo @Test_Id_TC2 @demo1
  Scenario: Login with locked_out_user credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"


  @demo @Test_Id_TC3 @demo1
  Scenario: Login with problem_user credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs1" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"


  @demo @Test_Id_TC4
  Scenario: Login with performance_glitch_user credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs2" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"


  @demo @Test_Id_TC5
  Scenario: Login with error_user credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs3" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"

  @demo @Test_Id_TC6
  Scenario: Login with visual_user credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs4" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"

  @demo @Test_Id_TC7
  Scenario: Login with invalid_user one credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"


  @demo @Test_Id_TC8
  Scenario: Login with invalid_user two credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"


  @demo @Test_Id_TC9
  Scenario: Login with valid_user invalid credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"


  @demo @Test_Id_TC10
  Scenario: Login with new user and new credentials
    When users enters text "#DataOne" using locator type "xpath" and value "//input[@id='user-name']"
    When users enters text "#DataTwo" using locator type "xpath" and value "//input[@id='password']"
    And user clicks on element using locator type "xpath" and value "//input[@id='login-button']"
    And user waits for "2" seconds
    And user verify text "Swag Labs" is on the screen having locator type "xpath" and value "//*[@id='header_container']/div[1]/div[2]/div"
