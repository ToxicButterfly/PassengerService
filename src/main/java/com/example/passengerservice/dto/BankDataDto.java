package com.example.passengerservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDataDto {

    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private Float balance;
}
