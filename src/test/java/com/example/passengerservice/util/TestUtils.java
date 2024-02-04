package com.example.passengerservice.util;

import com.example.passengerservice.dto.LoginDto;
import com.example.passengerservice.dto.PassengerDto;
import com.example.passengerservice.model.Passenger;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

@UtilityClass
public class TestUtils {
    public final Integer DEFAULT_ID = 1;
    public final String DEFAULT_FULLNAME = "Default fullname";
    public final String DEFAULT_USERNAME = "Default username";
    public final String DEFAULT_EMAIL = "SomeEmail@gmail.com";
    public final String DEFAULT_PASSWORD = "12345";
    public final Float DEFAULT_RATING = 5.0f;

    public Passenger getDefaultPassengerToSave() {
        return Passenger.builder()
                .id(DEFAULT_ID)
                .fullName(DEFAULT_FULLNAME)
                .username(DEFAULT_USERNAME)
                .email(DEFAULT_EMAIL)
                .password(DEFAULT_PASSWORD)
                .build();
    }

    public Passenger getDefaultPassenger() {
        return Passenger.builder()
                .id(DEFAULT_ID)
                .fullName(DEFAULT_FULLNAME)
                .username(DEFAULT_USERNAME)
                .email(DEFAULT_EMAIL)
                .password(DEFAULT_PASSWORD)
                .rating(DEFAULT_RATING)
                .build();
    }

    public PassengerDto getDefaultPassengerDto() {
        return PassengerDto.builder()
                .id(DEFAULT_ID)
                .fullName(DEFAULT_FULLNAME)
                .username(DEFAULT_USERNAME)
                .email(DEFAULT_EMAIL)
                .build();
    }

    public LoginDto getDefaultLoginDto() {
        return LoginDto.builder()
                .email(DEFAULT_EMAIL)
                .password(DEFAULT_PASSWORD)
                .build();
    }
}
