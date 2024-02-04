package com.example.passengerservice.service.impl;

import com.example.passengerservice.convert.PassengerDtoConverter;
import com.example.passengerservice.dto.response.RatingResponse;
import com.example.passengerservice.repo.PassengerRepo;
import com.example.passengerservice.dto.*;
import com.example.passengerservice.exception.InvalidLoginException;
import com.example.passengerservice.exception.UserNotFoundException;
import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.service.PassengerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.example.passengerservice.util.Messages.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    final PassengerRepo passengerRepo;
    final PassengerDtoConverter passengerDtoConverter;

    @SneakyThrows
    public PassengerDto register(Passenger passenger) {
        Optional<Passenger> someone = passengerRepo.findByEmailOrUsername(passenger.getEmail(),passenger.getUsername());
        if (someone.isPresent()) {
            throw new InvalidLoginException(INVALID_REGISTRATION_MESSAGE);
        }
        passenger.setRating(5.0F);
        passengerRepo.save(passenger);
        log.info("Passenger {} just registered", passenger.getUsername());
        return passengerDtoConverter.convertPassengerToPassengerDto(passenger);
    }

    @SneakyThrows
    public PassengersDto getAllPassengers() {
        return new PassengersDto(passengerRepo.findAll()
                .stream()
                .map(passengerDtoConverter::convertPassengerToPassengerDto)
                .toList());
    }

    @SneakyThrows
    public PassengerDto getPassenger(LoginDto loginDto) {
        Passenger passenger = passengerRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .orElseThrow(() -> new InvalidLoginException(INVALID_LOGIN_MESSAGE));
        return passengerDtoConverter.convertPassengerToPassengerDto(passenger);

    }

    public PassengerDto addOrUpdatePassenger(Passenger passenger, int id) {
        passenger.setRating(5.0F);
        if (passengerRepo.findById(id).isPresent()) {
            passenger.setId(id);
        }
        passengerRepo.save(passenger);
        return passengerDtoConverter.convertPassengerToPassengerDto(passenger);
    }

    @SneakyThrows
    public PassengerDto deletePassenger(int id) {
        Passenger passenger = passengerRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(PASSENGER_NOT_FOUND_MESSAGE));
        passengerRepo.deleteById(id);
        return passengerDtoConverter.convertPassengerToPassengerDto(passenger);

    }

    public BankDataDto getBankData() {
        BankDataDto data = BankDataDto.builder()
                .cvv(CVV)
                .cardNumber(CARD_NUMBER)
                .expirationDate(EXPIRATION_DATE)
                .balance(BALANCE)
                .build();
        log.info("Sending bank data");
        return data;
    }

    public RatingResponse askOpinion(int id) {
        Random random = new Random();
        int r = 1 + random.nextInt(5);
        log.info("Driver rated {} points", r);
        return new RatingResponse(r);
    }

    public void updateRating(UpdateRatingRequest request, Integer id) {
        Passenger passenger = passengerRepo.findById(id).get();
        passenger.setRating(request.getRating());
        passengerRepo.save(passenger);
        log.info("Rating of user with id {} has been updated to {}", id, request.getRating());
    }
}
