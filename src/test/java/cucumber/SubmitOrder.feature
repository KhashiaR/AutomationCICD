
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background: 
	Given I landed on Ecommerce Page

  @Listeners
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfrimationPage

    Examples: 
      | name  							| password       | productName |
      | KTest1@gmail.com 		| Qwerty@123456  | ZARA COAT 3 |