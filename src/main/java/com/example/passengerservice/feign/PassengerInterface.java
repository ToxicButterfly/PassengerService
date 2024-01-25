package com.example.passengerservice.feign;

import com.example.passengerservice.dto.PassengerRequestForRide;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("RIDES-SERVICE")
public interface PassengerInterface {
    @PostMapping("api/v1/ride/createTrip")
    public void createTrip(@RequestBody PassengerRequestForRide request);
}

