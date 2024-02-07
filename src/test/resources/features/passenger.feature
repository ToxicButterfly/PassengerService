Feature: Passenger Service

  Scenario: Creating passenger with unique credentials
    Given A passenger with email "UniqueEmail@tut.by" and username "Unique user" doesn't exist
    When A create request with email "UniqueEmail@tut.by" and username "Unique user" passed to the registration method
    Then The response should return created passenger