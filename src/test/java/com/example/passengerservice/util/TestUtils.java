package com.example.passengerservice.util;

import com.example.passengerservice.dto.LoginDto;
import com.example.passengerservice.dto.PassengerDto;
import com.example.passengerservice.dto.PassengerRequestForRide;
import com.example.passengerservice.model.Passenger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {
    public final Integer DEFAULT_ID = 1;
    public final String DEFAULT_FULLNAME = "Default fullname";
    public final String DEFAULT_USERNAME = "Default username";
    public final String DEFAULT_EMAIL = "SomeEmail@gmail.com";
    public final String DEFAULT_PASSWORD = "12345";
    public final Float DEFAULT_RATING = 5.0f;
    public final Integer DEFAULT_COORDINATE = 1;

    public Passenger getDefaultPassengerToSave() {
        return Passenger.builder()
                .id(DEFAULT_ID)
                .fullName(DEFAULT_FULLNAME)
                .username(DEFAULT_USERNAME)
                .email(DEFAULT_EMAIL)
                .build();
    }

    public Passenger getDefaultPassenger() {
        return Passenger.builder()
                .id(DEFAULT_ID)
                .fullName(DEFAULT_FULLNAME)
                .username(DEFAULT_USERNAME)
                .email(DEFAULT_EMAIL)
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

    public PassengerRequestForRide getDefaultPassengerRequestForRide() {
        return PassengerRequestForRide.builder()
                .passId(DEFAULT_ID)
                .coorX(DEFAULT_COORDINATE)
                .coorY(DEFAULT_COORDINATE)
                .coor2X(DEFAULT_COORDINATE)
                .coor2Y(DEFAULT_COORDINATE)
                .passRating(DEFAULT_RATING)
                .build();
    }
}
