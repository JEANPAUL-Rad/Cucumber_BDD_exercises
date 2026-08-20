
  @login
  Feature: User Login
    As a registered customer on AskOmDch e-commerce site
    I want to securely log in to my account
    So that I can view my order history
  Background:
    Given I am on the home page
    When I click on Account link

  Scenario: Successful login with valid credentials
    When I login with following credentials:
      | username | password   |
      | dybara6 | Test@12345 |
    Then I should see account dashboard
    And I should see logout link


  Scenario: Login with invalid username
    When I login with following credentials:
      | username      | password   |
      | invaliduser   | Test@12345 |
    Then I should see error message containing "not registered"

  Scenario: Login with invalid password
    When I login with following credentials:
      | username   | password      |
      | dybara6 | WrongPass123  |
    Then I should see error message containing "incorrect"

  Scenario: Login with empty username
    When I login with following credentials:
      | username | password   |
      |          | Test@12345 |
    Then I should see error message containing "required"

  Scenario: Login with empty password
    When I login with following credentials:
      | username   | password |
      | dybara6    |          |
    Then I should see error message containing "password field is empty"

  Scenario Outline: Login with multiple invalid credentials
    When I login with username "<username>" and password "<password>"
    Then I should see error message containing "<error_text>"

    Examples:
      | username    | password    | error_text |
      | wronguser   | Test@12345  | not registered  |
      | dybara6     | wrongpass   | incorrect  |
      |             |             | username is required   |
      | testuser99 |            | password field is empty |

  Scenario: User logout successfully
    When I login with following credentials:
      | username   | password   |
      | dybara6    | Test@12345 |
    And I click on logout link
    Then I should see login form
    And I should see "Login" text on the page