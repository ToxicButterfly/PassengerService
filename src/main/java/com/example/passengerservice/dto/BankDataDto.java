package com.example.passengerservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDataDto {

    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private Float balance;
}
