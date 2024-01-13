package com.example.passengerservice.convert;

import com.example.passengerservice.dto.LoginDTO;
import com.example.passengerservice.dto.PassengerDTO;
import com.example.passengerservice.model.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassengerDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PassengerDTO convertPassengerToPassengerDTO(Passenger passenger) {
        return modelMapper.map(passenger, PassengerDTO.class);
    }

    public Passenger convertPassengerDTOToPassenger(PassengerDTO passengerDTO) {
        return modelMapper.map(passengerDTO, Passenger.class);
    }

    public LoginDTO convertPassengerToLoginDTO(Passenger passenger) {
        return modelMapper.map(passenger, LoginDTO.class);
    }

    public Passenger convertLoginDTOToPassenger(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Passenger.class);
    }

}
