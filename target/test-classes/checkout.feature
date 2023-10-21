Feature: Checkout
  Scenario: User successfully initiates checkout with products in the cart
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "secret_sauce"
    And clicks the 'Login' button
    And the user selects a product to purchase
    And adds it to the cart
    And clicks the 'Checkout' button
    And fill first name, last name, and zip code
    And clicks the 'continue' button
    And clicks the 'finish' button
    Then the user successfully proceeds to the checkout overview page

  Scenario: User fails to initiate checkout with an empty cart
    Given the user is on the Saucedemo login page
    When enter credentials, username "standard_user" and password "secret_sauce"
    And clicks the 'Login' button
    And clicks the 'Checkout' button
    Then the user receives an error message indicating the cart is empty