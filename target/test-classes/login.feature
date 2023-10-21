Feature: Login
  Scenario: User successfully logs in
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "secret_sauce"
    And clicks the 'Login' button
    Then the user is redirected to the product page

  Scenario: User fails to log in with invalid credentials
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "123"
    And clicks the 'Login' button
    Then the user receives an error message and remains on the login page