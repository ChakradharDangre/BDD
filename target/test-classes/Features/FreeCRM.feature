@smoke
Feature: I want to validate
@HomePage
  Scenario: validate the page Home Page
    Given user open the application
    When user validate the title
    Then user validate the Sign up elements
@LoginPage
  Scenario: validate the Login Page
    Given user open the application
    When user click on Login button
    Then user validate the login page
@Login
  Scenario: validate the Login Page
    Given user open the application
    When user click on Login button
    When user enter the username
    Then user enter the password
    Then use click on login button
    Then validate the login
