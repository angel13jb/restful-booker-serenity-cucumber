Feature: End to End testing for booking

  Scenario: User should be able to creat booking
    When user send a post request to creat booking
    And  user insert name, lastname, price, result, dates, needs
    Then user should be able to creat booking successfully

  Scenario: User should be able to get single booking by Id
    When user send get request to fatch record by single id
    Then user should be able to get booking in responsebody
    And  verify booking name

  Scenario: User should be able to update booking by given id
    When user send Put patch request for updating booking
    And user add name in name field
    Then user should be able to update booking succefully

  Scenario: User should be able to delete data succefully
    When user send delete request for deleting booking
    Then booking should be succesffuly deleted
    And  to verify booking is deleted