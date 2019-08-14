@tag1
Feature: feature2

  Scenario: Eclipse
    Given Go to google page
    When Enter search "eclipse"
    When Enter search "eclips"

  Scenario: Facebook
    Given Go to Facebook Site
    When Enter Email Address with Example with UserName as "testFact@book.com" and Passwword as "happy"
    Then Click on Forgot Password
