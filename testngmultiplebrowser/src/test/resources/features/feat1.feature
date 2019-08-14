@tagTest1
Feature: Feature1

  Scenario: Cucumber
    Given Go to google page
    When Enter search "cucumber"
    Given Go to Facebook Site
    When Enter Email Address in Facebook with DataTable
      | emailId   | password |
      | v.bhavya5 | qwqewqe  |
    Then Click on Forgot Password

  Scenario Outline: facebook
    Given Go to Facebook Site
    When Enter Email Address with Example with UserName as "<userName>" and Passwword as "<password>"
    Then Click on Forgot Password

    Examples: 
      | userName     | password |
      | POIUYYTRRY   | POIUYTT  |
      | divyavakkala | QWWERETY |
