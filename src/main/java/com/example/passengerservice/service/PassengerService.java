package com.example.passengerservice.service;

import com.example.passengerservice.convert.PassengerDtoConverter;
import com.example.passengerservice.dao.PassengerRepo;
import com.example.passengerservice.dto.*;
import com.example.passengerservice.exception.InvalidLoginException;
import com.example.passengerservice.exception.UserNotFoundException;
import com.example.passengerservice.feign.PassengerInterface;
import com.example.passengerservice.model.Passenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerService {

    final PassengerRepo passengerRepo;
    final PassengerDtoConverter passengerDtoConverter;
    final PassengerInterface passengerInterface;

    public ResponseEntity<PassengerDto> register(Passenger passenger) throws InvalidLoginException {
        Optional<Passenger> someone = passengerRepo.findByEmailOrUsername(passenger.getEmail(),passenger.getUsername());
        if (someone.isPresent()) {
            throw new InvalidLoginException("Username or Email is already taken");
        }
        passenger.setRating(5.0F);
        passengerRepo.save(passenger);
        log.info("Passenger {} just registered", passenger.getUsername());
        return new ResponseEntity<>(passengerDtoConverter.convertPassengerToPassengerDto(passenger), HttpStatus.CREATED);
    }

    public ResponseEntity<List<PassengerDto>> getAllPassengers() throws UserNotFoundException {
        List<Passenger> passengers = passengerRepo.findAll();
        if (passengers.isEmpty()) {
            throw new UserNotFoundException("There's no passengers");
        }
        List<PassengerDto> dtoPassengers = new ArrayList<>();
        for (Passenger passenger: passengers) {
            dtoPassengers.add(passengerDtoConverter.convertPassengerToPassengerDto(passenger));
        }
        return new ResponseEntity<>(dtoPassengers, HttpStatus.OK);
    }
    public ResponseEntity<PassengerDto> getPassenger(LoginDto loginDTO) throws InvalidLoginException {
        Passenger passenger = passengerRepo.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(new Passenger());
        if (passenger.getId()!=null) {
            return new ResponseEntity<>(passengerDtoConverter.convertPassengerToPassengerDto(passenger), HttpStatus.FOUND);
        }
        else throw new InvalidLoginException("Invalid email or password");
    }

    public ResponseEntity<PassengerDto> addOrUpdatePassenger(Passenger passenger, int id) {
        passenger.setRating(5.0F);
        if (passengerRepo.findById(id).isPresent()) {
            passenger.setId(id);
            passengerRepo.save(passenger);
            return new ResponseEntity<>(passengerDtoConverter.convertPassengerToPassengerDto(passenger), HttpStatus.OK);
        } else {
            passengerRepo.save(passenger);
            return new ResponseEntity<>(passengerDtoConverter.convertPassengerToPassengerDto(passenger), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<PassengerDto> deletePassenger(int id) throws UserNotFoundException {
        Optional<Passenger> passenger = passengerRepo.findById(id);
        if (passenger.isPresent()) {
            passengerRepo.deleteById(id);
            return new ResponseEntity<>(passengerDtoConverter.convertPassengerToPassengerDto(passenger.get()), HttpStatus.OK);
        }
        else throw new UserNotFoundException("There's no such passenger");
    }

    public ResponseEntity<String> callTaxi(PassengerRequestForRide request) {
        passengerInterface.createTrip(request);
        return new ResponseEntity<>("Ожидайте водителя", HttpStatus.OK);
    }


    public ResponseEntity<BankDataDto> getBankData() {
        BankDataDto data = BankDataDto.builder()
                .cvv("123")
                .cardNumber("1234567890123456")
                .expirationDate("12/26")
                .balance(90000F)
                .build();
        log.info("Sending bank data");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public ResponseEntity<RatingResponse> askOpinion(int id) {
        Random random = new Random();
        int r = 1 + random.nextInt(5);
        log.info("Driver rated {} points", r);
        return new ResponseEntity<>(new RatingResponse(r), HttpStatus.OK);
    }

    public void updateRating(UpdateRatingRequest request, Integer id) {
        System.out.println(request.getRating());
        System.out.println(id);
        Passenger passenger = passengerRepo.findById(id).get();
        passenger.setRating(request.getRating());
        passengerRepo.save(passenger);
    }
}
