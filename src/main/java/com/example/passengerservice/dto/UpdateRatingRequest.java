package com.example.passengerservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRatingRequest {
    private Float rating;

}
