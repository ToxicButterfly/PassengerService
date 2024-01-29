package com.example.passengerservice.controller;

import com.example.passengerservice.dto.*;
import com.example.passengerservice.exception.InvalidLoginException;
import com.example.passengerservice.exception.UserNotFoundException;
import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @PostMapping("register")
    public ResponseEntity<PassengerDto> registration(@RequestBody @Valid Passenger passenger) throws InvalidLoginException {
        return passengerService.register(passenger);
    }

    @GetMapping("")
    public ResponseEntity<List<PassengerDto>> getAllPassengers() throws UserNotFoundException {
        return passengerService.getAllPassengers();
    }

    @PostMapping("login")
    public ResponseEntity<PassengerDto> getPassenger(@RequestBody LoginDto loginDTO) throws InvalidLoginException {
        return passengerService.getPassenger(loginDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@RequestBody @Valid Passenger passenger, @PathVariable int id) {
        return passengerService.addOrUpdatePassenger(passenger, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PassengerDto> deletePassenger(@PathVariable int id) throws UserNotFoundException {
        return passengerService.deletePassenger(id);
    }

    @PostMapping("callTaxi")
    public ResponseEntity<String> callTaxi(@RequestBody PassengerRequestForRide request) {
        return passengerService.callTaxi(request);
    }

    @GetMapping("{id}/bank")
    public ResponseEntity<BankDataDto> getBankData(@PathVariable int id) {
        return passengerService.getBankData();
    }

    @GetMapping("{id}/rating")
    public ResponseEntity<RatingResponse> askOpinion(@PathVariable int id) {
        return passengerService.askOpinion(id);
    }

    @PutMapping("{id}/rating")
    public void updateRating(@RequestBody UpdateRatingRequest request, @PathVariable int id) {
        passengerService.updateRating(request, id);
    }
}
