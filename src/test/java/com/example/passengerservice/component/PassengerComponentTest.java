package com.example.passengerservice.component;

import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.service.PassengerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Optional;

import static com.example.passengerservice.util.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class PassengerComponentTest {

    @Autowired
    private PassengerService passengerService;

    @Given("A passenger with email {string} and username {string} doesn't exist")
    public void passengerWithEmailAndUsernameDoesntExists(String email, String username) {

    }

    @When("A create request with email {string} and username {string} passed to the registration method")
    public void registerPassengerMethodCalled(String email, String username) {
        passengerService.register(Passenger.builder()
                        .id(DEFAULT_ID)
                        .rating(DEFAULT_RATING)
                        .fullName(DEFAULT_FULLNAME)
                        .email(email)
                        .username(username)
                        .build());
    }

    @Then("The response should return created passenger")
    public void responseContainsCreatedPassenger() {

    }
}
