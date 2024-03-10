package com.example.passengerservice.repo;

import com.example.passengerservice.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepo extends JpaRepository<Passenger, Integer> {
    Optional<Passenger> findByEmailOrUsername(String email, String username);
}
