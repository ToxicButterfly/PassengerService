package com.example.passengerservice.service;

import com.example.passengerservice.dto.PassengerRequestForRide;
import com.example.passengerservice.dto.response.CallTaxiResponse;

public interface PassengerForRidesService {

    CallTaxiResponse callTaxi(PassengerRequestForRide request);
}
