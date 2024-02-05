package com.example.passengerservice.controller;

import com.example.passengerservice.convert.PassengerDtoConverter;
import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.repo.PassengerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static com.example.passengerservice.util.TestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PassengerRepo passengerRepo;
    @Mock
    private PassengerDtoConverter passengerDtoConverter;

    @Test
    void registration_shouldReturnPassengerDto_whenCredentialsValid() throws Exception {
        doReturn(Optional.empty()).when(passengerRepo).findByEmailOrUsername(DEFAULT_EMAIL, DEFAULT_USERNAME);
        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        mockMvc.perform(post("/api/v1/passengers").content("{\n" +
                        "    \"fullName\": \"Evgeniy\",\n" +
                        "    \"username\": \"Kappa\",\n" +
                        "    \"email\": \"AnotherOneMail@tut.by\",\n" +
                        "    \"password\": \"654321\"\n" +
                        "}").contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1," +
                        "\"fullName\":\"Default fullname\"," +
                        "\"username\":\"Default username\"," +
                        "\"email\":\"SomeEmail@gmail.com\"}"));
    }

    @Test
    void registration_shouldReturnInvalidLoginResponse_whenCredentialsInvalid() throws Exception {
        doReturn(Optional.empty()).when(passengerRepo).findByEmailOrUsername(DEFAULT_EMAIL, DEFAULT_USERNAME);
        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        mockMvc.perform(post("/api/v1/passengers").content("{\n" +
                        "    \"fullName\": \"\",\n" +
                        "    \"username\": \"\",\n" +
                        "    \"email\": \"AnotherOneMaiy\",\n" +
                        "    \"password\": \"321\"\n" +
                        "}").contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"password\":\"Password must be atleast 6 symbold or longer\"," +
                        "\"fullName\":\"Your name field must not be empty\"," +
                        "\"email\":\"Email should be valid\"," +
                        "\"username\":\"Your username field must not be empty\"}\n"));
    }

    @Test
    void getAllPassengers_shouldReturnPassengersDto() throws Exception {
        doReturn(new ArrayList<>(Arrays.asList(getDefaultPassenger(), getDefaultPassenger()))).when(passengerRepo).findAll();
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        mockMvc.perform(get("/api/v1/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"passengers\":" +
                        "[{\"id\":1," +
                        "\"fullName\":\"Default fullname\"," +
                        "\"username\":\"Default username\"," +
                        "\"email\":\"SomeEmail@gmail.com\"}," +
                        "{\"id\":1," +
                        "\"fullName\":\"Default fullname\"," +
                        "\"username\":\"Default username\"," +
                        "\"email\":\"SomeEmail@gmail.com\"}]}\n"));
    }

    @Test
    void getPassenger_shouldReturnPassengerDto_whenPassengerExists() throws Exception {
        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findByEmailAndPassword("Kolya@gmail.com", "123457");
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        mockMvc.perform(post("/api/v1/passengers/login")
                .content("{\n" +
                "    \"email\": \"Kolya@gmail.com\",\n" +
                "    \"password\": \"123457\"\n" +
                "}").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1," +
                        "\"fullName\":\"Default fullname\"," +
                        "\"username\":\"Default username\"," +
                        "\"email\":\"SomeEmail@gmail.com\"}"));
    }

    @Test
    void getPassenger_shouldReturnInvalidLoginResponse_whenPassengerNotExist() throws Exception {
        doReturn(Optional.empty()).when(passengerRepo).findByEmailAndPassword("Kolya@gmail.com", "123457");

        mockMvc.perform(post("/api/v1/passengers/login")
                        .content("{\n" +
                                "    \"email\": \"Kolya@gmail.com\",\n" +
                                "    \"password\": \"123457\"\n" +
                                "}").contentType("application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json("{\"errorMessage\":\"Invalid email or password\"}"));
    }
    @Test
    void updatePassenger_shouldReturnPassengerDto_whenPassengerValid() throws Exception {
        doReturn(Optional.empty()).when(passengerRepo).findById(DEFAULT_ID);
        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        mockMvc.perform(put("/api/v1/passengers/1")
                .content("{\n" +
                        "    \"fullName\": \"Evgeniy2\",\n" +
                        "    \"username\": \"Poggers\",\n" +
                        "    \"email\": \"AnotherOneMail@tut.by\",\n" +
                        "    \"password\": \"654321\"\n" +
                        "}").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1," +
                        "\"fullName\":\"Default fullname\"," +
                        "\"username\":\"Default username\"," +
                        "\"email\":\"SomeEmail@gmail.com\"}"));
    }

    @Test
    void updatePassenger_shouldReturnInvalidLoginResponse_whenPassengerNotValid() throws Exception {

        mockMvc.perform(put("/api/v1/passengers/1")
                        .content("{\n" +
                                "    \"fullName\": \"\",\n" +
                                "    \"username\": \"\",\n" +
                                "    \"email\": \"AnotherOut.by\",\n" +
                                "    \"password\": \"31\"\n" +
                                "}").contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"password\":\"Password must be atleast 6 symbold or longer\"," +
                        "\"fullName\":\"Your name field must not be empty\"," +
                        "\"email\":\"Email should be valid\"," +
                        "\"username\":\"Your username field must not be empty\"}\n"));
    }

    @Test
    void deletePassenger_shouldReturnPassengerDto() throws Exception {
        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findById(DEFAULT_ID);

        mockMvc.perform(delete("/api/v1/passengers/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().json("{\"id\":1," +
                        "\"fullName\":\"Default fullname\"," +
                        "\"username\":\"Default username\"," +
                        "\"email\":\"SomeEmail@gmail.com\"}"));
    }
}