package com.example.passengerservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.passengerservice.util.Messages.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = EMPTY_NAME_MESSAGE)
    private String fullName;
    @NotEmpty(message = EMPTY_USERNAME_MESSAGE)
    private String username;
    @Email(message = EMPTY_EMAIL_MESSAGE)
    private String email;
    @Size(min = 6, message = NON_VALID_PASSWORD_MESSAGE)
    private String password;
    private Float rating;

}
