@smoke
Feature: Smoke

  Scenario Outline: Login works
    Given I login as "<userType>"
    Then I should land on Inventory page and see products
    Examples:
    | userType |
    | standard |

    Scenario Outline: Logout works
      Given I login as "<userType>"
      Then I logout successfully
      Examples:
        | userType |
        | standard |