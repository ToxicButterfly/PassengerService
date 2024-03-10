package com.example.passengerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.passengerservice.util.Messages.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerCreateRequest {
    @NotEmpty(message = EMPTY_NAME_MESSAGE)
    private String fullName;
    @NotEmpty(message = EMPTY_USERNAME_MESSAGE)
    private String username;
    @Email(message = EMPTY_EMAIL_MESSAGE)
    private String email;
}
