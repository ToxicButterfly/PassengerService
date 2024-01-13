package com.example.passengerservice.feign;

import com.example.passengerservice.model.Location;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("RIDES-SERVICE")
public interface PassengerInterface {
    @PostMapping("api/v1/ride/createTrip/{id}")
    public void createTrip(@RequestBody Location location, @PathVariable int id);
}

