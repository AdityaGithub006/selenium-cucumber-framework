@auth @negative
Feature: Negative Login

  Scenario Outline: Login should fail with correct error
    When I login as "<userType>"
    Then login should fail with "<errorMessage>"

    Examples:
      | userType | errorMessage                                                               |
      | locked   | Epic sadface: Sorry, this user has been locked out.                        |
      | invalid  | Epic sadface: Username and password do not match any user in this service  |
