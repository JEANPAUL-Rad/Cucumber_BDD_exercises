@cart
Feature: Shopping Cart Functionality

  As a customer
  I want to manage items in my shopping cart
  So that I can review, update quantities, remove items, and proceed to checkout

  Background:
    Given I am on the home page
    When I navigate to Store page
    And I add a product to the cart

  Scenario: View cart with one item
    When I navigate to Cart page
    Then the cart should contain 1 item
    And the item name should be displayed correctly
    And the subtotal should match the product price

  Scenario: Remove item from cart
    When I navigate to Cart page
    And I remove the first item from cart
    Then the cart should be empty
    And I should see a message like "Your cart is currently empty"

  Scenario: Proceed to checkout from cart
    When I navigate to Cart page
    And I click on Proceed to Checkout
    Then I should be redirected to the checkout page