package com.example.passengerservice.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
