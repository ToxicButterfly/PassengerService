package com.example.passengerservice.service;

import com.example.passengerservice.dto.*;
import com.example.passengerservice.dto.response.RatingResponse;
import com.example.passengerservice.exception.InvalidLoginException;
import com.example.passengerservice.exception.UserNotFoundException;
import com.example.passengerservice.model.Passenger;

public interface PassengerService {
    PassengerDto register(PassengerCreateRequest request);
    PassengersDto getAllPassengers();
    PassengerDto getPassenger(int id);
    PassengerDto addOrUpdatePassenger(Passenger passenger, int id);
    PassengerDto deletePassenger(int id);
    BankDataDto getBankData();
    RatingResponse askOpinion(int id);
    void updateRating(UpdateRatingRequest request, Integer id);
}
