@filter
Feature: Products Filter By Price Range
  As a Customer
  I want to filter the products by specifying the price range
  So that I can find the products easily and quickly in the range I can afford

  Background:
    Given I am on the home page
    When I navigate to Store page

  Scenario: Filter products by price range 50-100
    When I set minimum price to "50"
    And I set maximum price to "100"
    And I click on Filter button
    Then all displayed products should be within price range "50" to "100"
    And I should see at least 1 product

  Scenario Outline: Filter products by different price ranges
    When I set minimum price to "<minPrice>"
    And I set maximum price to "<maxPrice>"
    And I click on Filter button
    Then all displayed products should be within price range "<minPrice>" to "<maxPrice>"
    And I should see at least 1 product

    Examples:
      | minPrice | maxPrice |
      | 50       | 100      |
      | 50       | 60       |
      | 100      | 5000     |
      | 20       | 50       |

  Scenario: Filter products with minimum price only
    When I set minimum price to "100"
    And I click on Filter button
    Then all displayed products should have price greater than or equal to "100"

  Scenario: Verify filter reflects in URL
    When I set minimum price to "50"
    And I set maximum price to "100"
    And I click on Filter button
    Then URL should contain "min_price=50"
    And URL should contain "max_price=100"