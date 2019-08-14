@tag1
@tag4
Feature: Feature7

  Scenario: Cucumber
    Given Go to google page
    When Enter search "cucumber"

  Scenario: Selenium
    Given Go to google page
    When Enter search "selenium"
    
    Scenario: Facebook
    Given Go to Facebook Site
    When Enter Email Address with Example with UserName as "testFact@book.com" and Passwword as "happy"
    Then Click on Forgot Password
