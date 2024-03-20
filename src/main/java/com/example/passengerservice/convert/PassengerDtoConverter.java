package com.example.passengerservice.convert;

import com.example.passengerservice.dto.LoginDto;
import com.example.passengerservice.dto.PassengerDto;
import com.example.passengerservice.model.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassengerDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PassengerDto convertPassengerToPassengerDto(Passenger passenger) {
        return modelMapper.map(passenger, PassengerDto.class);
    }

    public Passenger convertPassengerDtoToPassenger(PassengerDto passengerDto) {
        return modelMapper.map(passengerDto, Passenger.class);
    }

    public LoginDto convertPassengerToLoginDto(Passenger passenger) {
        return modelMapper.map(passenger, LoginDto.class);
    }

    public Passenger convertLoginDtoToPassenger(LoginDto loginDto) {
        return modelMapper.map(loginDto, Passenger.class);
    }

}
