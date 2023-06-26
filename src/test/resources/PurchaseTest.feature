@selenium
Feature: Purchase a ticket without an account

  In order to purchase a flight ticket
  as a non registered user
  I want to continue the purchase process until the payment

  @NonUserPurchaseFlightTicket @flightsResults
  Scenario: select the first flight option
    Given The flights page on Despegar website is set
    When the dates and locations are set correctly and submit
    Then the passenger is able to select the first flight option from the results page


  @NonUserPurchaseFlightTicket @passengerInformation
  Scenario: fill and submit the passenger's information and payment's information
    Given the flight is selected to be purchase already
    When the passenger and payment information is set and submit
    Then the passenger should be able to review the information provided before