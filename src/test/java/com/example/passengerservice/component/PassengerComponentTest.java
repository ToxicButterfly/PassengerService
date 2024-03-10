//package com.example.passengerservice.component;
//
//import com.example.passengerservice.convert.PassengerDtoConverter;
//import com.example.passengerservice.dto.LoginDto;
//import com.example.passengerservice.dto.PassengerDto;
//import com.example.passengerservice.model.Passenger;
//import com.example.passengerservice.repo.PassengerRepo;
//import com.example.passengerservice.service.PassengerService;
//import com.example.passengerservice.service.impl.PassengerServiceImpl;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.spring.CucumberContextConfiguration;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import java.util.Optional;
//
//import static com.example.passengerservice.util.Messages.*;
//import static com.example.passengerservice.util.TestUtils.*;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doReturn;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@CucumberContextConfiguration
//public class PassengerComponentTest {
//
//    @Mock
//    private PassengerRepo passengerRepo;
//    @Mock
//    private PassengerDtoConverter passengerDtoConverter;
//    @InjectMocks
//    private PassengerServiceImpl passengerService;
//    private Exception exception;
//    private PassengerDto passengerResponse;
//
//    @Given("A passenger with email {string} and username {string} doesn't exist")
//    public void passengerWithEmailAndUsernameDoesntExists(String email, String username) {
//        doReturn(Optional.empty()).when(passengerRepo).findByEmailOrUsername(email, username);
//        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
//        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
//
//        Optional<Passenger> passenger = passengerRepo.findByEmailOrUsername(email, username);
//        assertFalse(passenger.isPresent());
//    }
//
//    @When("Create request with email {string} and username {string} passed to the registration method")
//    public void registerPassengerMethodCalled(String email, String username) {
//        try {
//            passengerResponse = passengerService.register(Passenger.builder()
//                    .id(DEFAULT_ID)
//                    .rating(DEFAULT_RATING)
//                    .fullName(DEFAULT_FULLNAME)
//                    .email(email)
//                    .username(username)
//                    .build());
//        } catch (Exception e) {
//            exception = e;
//        }
//    }
//
//    @Then("The response should return created passenger")
//    public void responseContainsCreatedPassenger() {
//        assertEquals(getDefaultPassengerDto(), passengerResponse);
//    }
//
//    @Given("A passenger with email {string} and username {string} exists")
//    public void passengerWithEmailAndUsernameExists(String email, String username) {
//        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findByEmailOrUsername(email, username);
//    }
//
//    @Then("The Invalid registration message should be thrown")
//    public void theInvalidRegistrationExceptionShouldBeThrown() {
//        assertThat(exception.getMessage()).isEqualTo(INVALID_REGISTRATION_MESSAGE);
//    }
//
//    @Given("A passenger with email {string} and password {string} exists")
//    public void passengerWithEmailAndPasswordExists(String email, String password) {
//        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findByEmailAndPassword(email, password);
//        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
//    }
//
//    @When("Get request with email {string} and password {string} passed to the login method")
//    public void createRequestWithEmailAndPasswordPassedToTheLoginMethod(String email, String password) {
//        try {
//            passengerResponse = passengerService.getPassenger(LoginDto.builder()
//                            .password(password)
//                            .email(email)
//                            .build());
//        } catch (Exception e) {
//            exception = e;
//        }
//    }
//
//    @Then("The response should return passenger data")
//    public void theResponseShouldReturnPassengerData() {
//        assertEquals(getDefaultPassengerDto(), passengerResponse);
//    }
//
//    @Given("A passenger with email {string} and password {string} does not exist")
//    public void aPassengerWithEmailAndPasswordDoesNotExist(String email, String password) {
//        doReturn(Optional.empty()).when(passengerRepo).findByEmailAndPassword(email, password);
//    }
//
//    @Then("The InvalidLoginException should be thrown")
//    public void theInvalidLoginExceptionShouldBeThrown() {
//        assertThat(exception.getMessage()).isEqualTo(INVALID_LOGIN_MESSAGE);
//    }
//
//    @Given("A passenger with id {int} does not exist")
//    public void aPassengerWithIdDoesNotExist(Integer id) {
//        doReturn(Optional.empty()).when(passengerRepo).findById(id);
//        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
//        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
//    }
//
//    @When("Create request with id {int} passed to the addOrUpdate method")
//    public void createRequestWithIdPassedToTheAddOrUpdateMethod(Integer id) {
//        passengerResponse = passengerService.addOrUpdatePassenger(getDefaultPassenger(), id);
//    }
//
//    @Given("A passenger with id {int} exists")
//    public void aPassengerWithIdExists(int id) {
//        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findById(id);
//        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
//        doNothing().when(passengerRepo).deleteById(id);
//        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
//    }
//
//    @When("Delete request with id {int} passed to delete method")
//    public void deleteRequestWithIdPassedToDeleteMethod(int id) {
//        try {
//            passengerResponse = passengerService.deletePassenger(id);
//        } catch (Exception e) {
//            exception = e;
//        }
//    }
//
//    @Then("The UserNotFoundException should be thrown")
//    public void theUserNotFoundExceptionShouldBeThrown() {
//        assertThat(exception.getMessage()).isEqualTo(PASSENGER_NOT_FOUND_MESSAGE);
//    }
//}