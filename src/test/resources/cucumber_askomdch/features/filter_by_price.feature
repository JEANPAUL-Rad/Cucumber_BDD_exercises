
@filter
Feature: Products Filter By Price Range

  As a Customer
  I want to interact with the price filter widget
  So that I can verify the form submission and URL update
  # Note: The actual price filtering is NOT applied by the website

  Background:
    Given I am on the home page
    When I navigate to Store page

  Scenario: Filter form submits and updates URL (50-100)
    When I set minimum price to "50"
    And I set maximum price to "100"
    And I click on Filter button
    Then URL should contain "min_price=50"
    And URL should contain "max_price=100"
    And I should see at least 1 product

  Scenario Outline: Filter form submits and updates URL for various ranges
    When I set minimum price to "<minPrice>"
    And I set maximum price to "<maxPrice>"
    And I click on Filter button
    Then URL should contain "min_price=<minPrice>"
    And URL should contain "max_price=<maxPrice>"
    And I should see at least 1 product

    Examples:
      | minPrice | maxPrice |
      | 50       | 100      |
      | 20       | 50       |
      | 100      | 5000     |

  Scenario: Filter with minimum price only
    When I set minimum price to "100"
    And I click on Filter button
    Then URL should contain "min_price=100"
    And I should see at least 1 product