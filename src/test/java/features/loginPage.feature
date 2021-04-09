@LoginPage
@RunAll
Feature: Login page validation with valid and invalid credentials
  This feature will verify login page

  Background:
    Given navigate to login page

  @tagging
  @severity=blocker
  Scenario: Validate login with valid credentials

      And enter Username as "sahinakausar@gmail.com"
      Then click on login button
    And enter Password as "11Shahina@"
    Then click on login submit button
    Then click on board logo
    Then click on add a card
    Then Write card name
    Then Save the card
    Then check card is present

   # Then verify the header name should be "Welcome to webMethods.io B2B" in "login" page
