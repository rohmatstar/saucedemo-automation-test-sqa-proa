Feature: Logout
  Scenario: User successfully logs out
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "secret_sauce"
    And clicks the 'Login' button
    And clicks the 'logout' button
    Then verify the user is on login page

  Scenario: User fails to access product page after logging out
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "secret_sauce"
    And clicks the 'Login' button
    And clicks the 'logout' button
    Then verify user cannot go to the product page