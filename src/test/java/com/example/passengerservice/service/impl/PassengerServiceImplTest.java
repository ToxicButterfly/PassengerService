package com.example.passengerservice.service.impl;

import com.example.passengerservice.convert.PassengerDtoConverter;
import com.example.passengerservice.dto.PassengerDto;
import com.example.passengerservice.dto.PassengersDto;
import com.example.passengerservice.exception.InvalidLoginException;
import com.example.passengerservice.exception.UserNotFoundException;
import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.repo.PassengerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static com.example.passengerservice.util.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PassengerServiceImplTest {

    @Mock
    private PassengerRepo passengerRepo;
    @Mock
    private PassengerDtoConverter passengerDtoConverter;
    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Test
    void registerWhenCredentialsValid() {
        Passenger passenger = getDefaultPassengerToSave();
        doReturn(Optional.empty()).when(passengerRepo).findByEmailOrUsername(DEFAULT_EMAIL, DEFAULT_USERNAME);
        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        PassengerDto response = passengerService.register(passenger);

        assertEquals(getDefaultPassengerDto(), response);
        verify(passengerRepo).findByEmailOrUsername(DEFAULT_EMAIL, DEFAULT_USERNAME);
        verify(passengerRepo).save(any(Passenger.class));
        verify(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
    }

    @Test
    void registerWhenCredentialsNonValid() {
        doReturn(Optional.of(Passenger.class)).when(passengerRepo).findByEmailOrUsername(DEFAULT_EMAIL, DEFAULT_USERNAME);
        assertThrows(InvalidLoginException.class, () -> passengerService.register(getDefaultPassengerToSave()));
    }

    @Test
    void getAllPassengers() {
        doReturn(new ArrayList<>(Arrays.asList(getDefaultPassenger(), getDefaultPassenger()))).when(passengerRepo).findAll();
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        PassengersDto response = passengerService.getAllPassengers();

        assertEquals(new PassengersDto(Arrays.asList(getDefaultPassengerDto(), getDefaultPassengerDto())), response);
        verify(passengerRepo).findAll();
        verify(passengerDtoConverter, times(2)).convertPassengerToPassengerDto(any(Passenger.class));
    }

    @Test
    void getPassengerWhenPassengerExist() {
        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findByEmailAndPassword(DEFAULT_EMAIL, DEFAULT_PASSWORD);
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        PassengerDto response = passengerService.getPassenger(getDefaultLoginDto());

        assertEquals(getDefaultPassengerDto(), response);
        verify(passengerRepo).findByEmailAndPassword(DEFAULT_EMAIL, DEFAULT_PASSWORD);
        verify(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
    }

    @Test
    void getPassengerWhenPassengerNotExist() {
        doReturn(Optional.empty()).when(passengerRepo).findByEmailAndPassword(DEFAULT_EMAIL, DEFAULT_PASSWORD);
        assertThrows(InvalidLoginException.class, () -> passengerService.getPassenger(getDefaultLoginDto()));
        verify(passengerRepo).findByEmailAndPassword(DEFAULT_EMAIL,DEFAULT_PASSWORD);
    }

    @Test
    void addOrUpdatePassengerWhenAdd() {
        doReturn(Optional.empty()).when(passengerRepo).findById(DEFAULT_ID);
        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        PassengerDto response = passengerService.addOrUpdatePassenger(getDefaultPassenger(), DEFAULT_ID);

        assertEquals(getDefaultPassengerDto(), response);
        verify(passengerRepo).findById(DEFAULT_ID);
        verify(passengerRepo).save(any(Passenger.class));
        verify(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
    }

    @Test
    void addOrUpdatePassengerWhenUpdate() {
        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findById(DEFAULT_ID);
        doReturn(getDefaultPassenger()).when(passengerRepo).save(any(Passenger.class));
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        PassengerDto response = passengerService.addOrUpdatePassenger(getDefaultPassenger(), DEFAULT_ID);

        assertEquals(getDefaultPassengerDto(), response);
        verify(passengerRepo).findById(DEFAULT_ID);
        verify(passengerRepo).save(any(Passenger.class));
        verify(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
    }

    @Test
    void deletePassengerWhenPassengerExist() {
        doReturn(Optional.of(getDefaultPassenger())).when(passengerRepo).findById(DEFAULT_ID);
        doNothing().when(passengerRepo).deleteById(DEFAULT_ID);
        doReturn(getDefaultPassengerDto()).when(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));

        PassengerDto response = passengerService.deletePassenger(DEFAULT_ID);

        assertEquals(getDefaultPassengerDto(), response);
        verify(passengerRepo).findById(DEFAULT_ID);
        verify(passengerRepo).deleteById(DEFAULT_ID);
        verify(passengerDtoConverter).convertPassengerToPassengerDto(any(Passenger.class));
    }

    @Test
    void deletePassengerWhenPassengerNotExist() {
        doReturn(Optional.empty()).when(passengerRepo).findById(DEFAULT_ID);
        assertThrows(UserNotFoundException.class, () -> passengerService.deletePassenger(DEFAULT_ID));
    }
}
