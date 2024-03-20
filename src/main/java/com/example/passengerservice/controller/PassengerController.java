package com.example.passengerservice.controller;

import com.example.passengerservice.dto.*;
import com.example.passengerservice.dto.response.CallTaxiResponse;
import com.example.passengerservice.dto.response.RatingResponse;
import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.service.PassengerForRidesService;
import com.example.passengerservice.service.PassengerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/passengers")
public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerForRidesService ridesService;

    @PostMapping
    public ResponseEntity<PassengerDto> registration(@RequestBody @Valid Passenger passenger) {
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerService.register(passenger));
    }

    @GetMapping
    public ResponseEntity<PassengersDto> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @PostMapping("login")
    public ResponseEntity<PassengerDto> getPassenger(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(passengerService.getPassenger(loginDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@RequestBody @Valid Passenger passenger, @PathVariable int id) {
        return ResponseEntity.ok(passengerService.addOrUpdatePassenger(passenger, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PassengerDto> deletePassenger(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(passengerService.deletePassenger(id));
    }

    @PostMapping("callTaxi")
    @CircuitBreaker(name="callTaxi", fallbackMethod = "fallbackCallTaxi")
    public ResponseEntity<CallTaxiResponse> callTaxi(@RequestBody PassengerRequestForRide request) {
        return ResponseEntity.ok(ridesService.callTaxi(request));
    }

    @GetMapping("{id}/bank")
    public ResponseEntity<BankDataDto> getBankData(@PathVariable int id) {
        return ResponseEntity.ok(passengerService.getBankData());
    }

    @GetMapping("{id}/rating")
    public ResponseEntity<RatingResponse> askOpinion(@PathVariable int id) {
        return ResponseEntity.ok(passengerService.askOpinion(id));
    }

    @PutMapping("{id}/rating")
    public void updateRating(@RequestBody UpdateRatingRequest request, @PathVariable int id) {
        passengerService.updateRating(request, id);
    }

    public ResponseEntity<CallTaxiResponse> fallbackCallTaxi(Throwable throwable){
        return ResponseEntity.internalServerError().body(new CallTaxiResponse("Сервер упал, подождите"));
    }
}
