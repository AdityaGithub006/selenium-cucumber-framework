Feature: Inventory sorting

  Scenario Outline: Verify inventory sorting
    Given I login as "<userType>"
    When I sort product by "<sortOption>"
    Then Product should be sorted correctly by "<sortType>"
    Examples:
    | userType | sortOption | sortType |
    | standard | Name (A to Z) | NAME_ASC |
    | standard | Name (Z to A) | NAME_DESC |
    | standard | Price (low to high) | PRICE_ASC |
    | standard | Price (high to low) | PRICE_DESC |
