@selenium
Feature: Purchase a ticket without an account

  In order to purchase a flight ticket
  as a non registered user
  I want to continue the purchase process until the payment

  @flightsResults
  Scenario: select the first flight option
    Given The flights page on Despegar website is set
    When the dates and locations are set correctly and summit
    Then the passenger is able to select the first flight option from the results page