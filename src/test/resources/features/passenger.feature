Feature: Passenger Service

  Scenario: Creating passenger with unique credentials
    Given A passenger with email "UniqueEmail@tut.by" and username "Unique user" doesn't exist
    When Create request with email "UniqueEmail@tut.by" and username "Unique user" passed to the registration method
    Then The response should return created passenger

  Scenario: Creating passenger with non unique credentials
    Given A passenger with email "UniqueEmail@tut.by" and username "Unique user" exists
    When Create request with email "UniqueEmail@tut.by" and username "Unique user" passed to the registration method
    Then The Invalid registration message should be thrown

  Scenario: Getting passenger data with correct login credentials
    Given A passenger with email "CorrectEmail@gmail.com" and password "123456" exists
    When Get request with email "CorrectEmail@gmail.com" and password "123456" passed to the login method
    Then The response should return passenger data

  Scenario Outline: Getting passenger data with incorrect login credentials
    Given A passenger with email <email> and password <password> does not exist
    When Get request with email <email> and password <password> passed to the login method
    Then The InvalidLoginException should be thrown
    Examples:
      | email                    | password |
      | "CorrectEmail@gmail.com" | "123456" |

  Scenario: Adding new passenger
    Given A passenger with id 5 does not exist
    When Create request with id 5 passed to the addOrUpdate method
    Then The response should return passenger data

  Scenario: Updating existing passenger
    Given A passenger with id 1 exists
    When Create request with id 1 passed to the addOrUpdate method
    Then The response should return passenger data

  Scenario: Deleting existing passenger
    Given A passenger with id 1 exists
    When Delete request with id 1 passed to delete method
    Then The response should return passenger data

  Scenario: Deleting non existing passenger
    Given A passenger with id 5 does not exist
    When Delete request with id 5 passed to delete method
    Then The UserNotFoundException should be thrown