package com.example.passengerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private Integer id;
    private String fullName;
    private String username;
    private String email;

}
