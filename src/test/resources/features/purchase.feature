@purchase @regression
Feature: Purchase

  Scenario Outline: Buy one item successfully
    Given I login as "<userType>"
    When I add "<product>" to cart
    And I open cart
    Then cart should contain "<product>"
    When I checkout with firstName "<firstname>" lastName "<lastname>" postalCode "<postalCode>"
    Then order should be placed successfully
    Examples:
      | userType | product              | firstname | lastname  | postalCode  |
      | standard | Sauce Labs Backpack  | Aditya    | Bastia    | 12345       |