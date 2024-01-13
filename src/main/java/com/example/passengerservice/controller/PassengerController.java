package com.example.passengerservice.controller;

import com.example.passengerservice.dto.LoginDTO;
import com.example.passengerservice.dto.PassengerDTO;
import com.example.passengerservice.exception.InvalidLoginException;
import com.example.passengerservice.exception.UserNotFoundException;
import com.example.passengerservice.model.Location;
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
    public ResponseEntity<PassengerDTO> registration(@RequestBody @Valid Passenger passenger) throws InvalidLoginException {
        return passengerService.register(passenger);
    }

    @GetMapping("")
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() throws UserNotFoundException {
        return passengerService.getAllPassengers();
    }

    @PostMapping("login")
    public ResponseEntity<PassengerDTO> getPassenger(@RequestBody LoginDTO loginDTO) throws InvalidLoginException {
        return passengerService.getPassenger(loginDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<PassengerDTO> updatePassenger(@RequestBody @Valid Passenger passenger, @PathVariable int id) {
        return passengerService.addOrUpdatePassenger(passenger, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PassengerDTO> deletePassenger(@PathVariable int id) throws UserNotFoundException {
        return passengerService.deletePassenger(id);
    }

    @PostMapping("callTaxi/{id}")
    public ResponseEntity<String> callTaxi(@RequestBody Location location,@PathVariable int id) {
        return passengerService.callTaxi(location, id);
    }
}
