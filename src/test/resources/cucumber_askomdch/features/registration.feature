
  @registration
  Feature: User Registration
  As a new user
  I want to create account
  So that i can purchase on websit

  Scenario: User registers successfully with valid details
    Given I am on the home page
    When I click on Account link
    And I register with following details:
      | username   | email                  | password   |
      | dybara6 | dybara6@gmail.com | Test@12345 |
    Then I should see account dashboard
    And I should not see any error message

   @Negative
  Scenario: User registers with existing email
    Given I am on the home page
    When I click on Account link
    And I register with following details:
      | username    | email                 | password   |
      | user2 | dybara@gmail.com  | Test@12345 |
    Then I should see error message containing "already registered"