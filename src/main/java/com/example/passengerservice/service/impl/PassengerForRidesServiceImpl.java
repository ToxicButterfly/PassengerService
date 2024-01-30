package com.example.passengerservice.service.impl;

import com.example.passengerservice.dto.PassengerRequestForRide;
import com.example.passengerservice.dto.response.CallTaxiResponse;
import com.example.passengerservice.feign.RideFeignInterface;
import com.example.passengerservice.service.PassengerForRidesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerForRidesServiceImpl implements PassengerForRidesService {

    final RideFeignInterface rideFeignInterface;

    public CallTaxiResponse callTaxi(PassengerRequestForRide request) {
        rideFeignInterface.createTrip(request);
        return new CallTaxiResponse("Ожидайте водителя");
    }

}
