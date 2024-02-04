package com.example.passengerservice.service.impl;

import com.example.passengerservice.dto.PassengerRequestForRide;
import com.example.passengerservice.dto.response.CallTaxiResponse;
import com.example.passengerservice.feign.RideFeignInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.passengerservice.util.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class PassengerForRidesServiceTest {
    @Mock
    public RideFeignInterface rideFeignInterface;
    @InjectMocks
    public PassengerForRidesServiceImpl passengerForRidesService;

    @Test
    void callTaxi() {
        PassengerRequestForRide request = getDefaultPassengerRequestForRide();

        CallTaxiResponse response = passengerForRidesService.callTaxi(request);

        assertEquals(new CallTaxiResponse("Ожидайте водителя"), response);
        verify(rideFeignInterface).createTrip(any(PassengerRequestForRide.class));
    }
}
