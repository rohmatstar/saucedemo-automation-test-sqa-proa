Feature: Product
  Scenario: See product detail
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "secret_sauce"
    And clicks the 'Login' button
    And the user selects a product to purchase
    And clicks the 'logout' button
    Then verify the user is on login page

  Scenario: Filter products
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "secret_sauce"
    And clicks the 'Login' button
    And the user filter product
    And clicks the 'logout' button
    Then verify the user is on login page