package com.example.passengerservice.feign;

import com.example.passengerservice.dto.PassengerRequestForRide;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${feign.client.rides.name}", url = "${feign.client.rides.url}", path = "${feign.client.rides.path}")
public interface RideFeignInterface {
    @PostMapping("createTrip")
    public void createTrip(@RequestBody PassengerRequestForRide request);
}

