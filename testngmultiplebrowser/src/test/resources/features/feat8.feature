@tag1New
@tag1
Feature: feature8

  Scenario: Eclipse
    Given Go to google page
    When Enter search "eclipse"
    When Enter search "eclips"
    
 Scenario Outline: Cucumber123
    Given Go to Facebook Site
    When Enter Email Address with Example with UserName as "<userName>" and Passwword as "<password>"
    Then Click on Forgot Password

    Examples: 
      | userName     | password |
      | POIUYYTRRY   | POIUYTT  |
      | divyavakkala | QWWERETY |
