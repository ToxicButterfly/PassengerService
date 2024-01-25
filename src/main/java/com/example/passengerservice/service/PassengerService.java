package com.example.passengerservice.service;

import com.example.passengerservice.convert.PassengerDTOConverter;
import com.example.passengerservice.dao.PassengerDAO;
import com.example.passengerservice.dto.LoginDTO;
import com.example.passengerservice.dto.PassengerDTO;
import com.example.passengerservice.dto.PassengerRequestForRide;
import com.example.passengerservice.exception.InvalidLoginException;
import com.example.passengerservice.exception.UserNotFoundException;
import com.example.passengerservice.feign.PassengerInterface;
import com.example.passengerservice.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerDAO passengerDAO;
    @Autowired
    PassengerDTOConverter passengerDTOConverter;
    @Autowired
    PassengerInterface passengerInterface;

    public ResponseEntity<PassengerDTO> register(Passenger passenger) throws InvalidLoginException {
        Optional<Passenger> someone = passengerDAO.findByEmailOrUsername(passenger.getEmail(),passenger.getUsername());
        if(someone.isPresent())
            throw new InvalidLoginException("Username or Email is already taken");
        passenger.setRating(5.0F);
        passengerDAO.save(passenger);
        return new ResponseEntity<>(passengerDTOConverter.convertPassengerToPassengerDTO(passenger), HttpStatus.CREATED);
    }

    public ResponseEntity<List<PassengerDTO>> getAllPassengers() throws UserNotFoundException {
        List<Passenger> passengers = passengerDAO.findAll();
        if(passengers.isEmpty())
            throw new UserNotFoundException("There's no passengers");
        List<PassengerDTO> dtoPassengers = new ArrayList<>();
        for(Passenger passenger: passengers) {
            dtoPassengers.add(passengerDTOConverter.convertPassengerToPassengerDTO(passenger));
        }
        return new ResponseEntity<>(dtoPassengers, HttpStatus.OK);
    }
    public ResponseEntity<PassengerDTO> getPassenger(LoginDTO loginDTO) throws InvalidLoginException {
        Passenger passenger = passengerDAO.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(new Passenger());
        if(passenger.getId()!=null) {
            return new ResponseEntity<>(passengerDTOConverter.convertPassengerToPassengerDTO(passenger), HttpStatus.FOUND);
        }
        else throw new InvalidLoginException("Invalid email or password");
    }

    public ResponseEntity<PassengerDTO> addOrUpdatePassenger(Passenger passenger, int id) {
        passenger.setRating(5.0F);
        if(passengerDAO.findById(id).isPresent()) {
            passenger.setId(id);
            passengerDAO.save(passenger);
            return new ResponseEntity<>(passengerDTOConverter.convertPassengerToPassengerDTO(passenger), HttpStatus.OK);
        }
        else {
            passengerDAO.save(passenger);
            return new ResponseEntity<>(passengerDTOConverter.convertPassengerToPassengerDTO(passenger), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<PassengerDTO> deletePassenger(int id) throws UserNotFoundException {
        Optional<Passenger> passenger = passengerDAO.findById(id);
        if(passenger.isPresent()) {
            passengerDAO.deleteById(id);
            return new ResponseEntity<>(passengerDTOConverter.convertPassengerToPassengerDTO(passenger.get()), HttpStatus.OK);
        }
        else throw new UserNotFoundException("There's no such passenger");
    }

    public ResponseEntity<String> callTaxi(PassengerRequestForRide request) {
        passengerInterface.createTrip(request);
        //Произвести оплату
        return new ResponseEntity<>("Ожидайте водителя", HttpStatus.OK);
    }


}
