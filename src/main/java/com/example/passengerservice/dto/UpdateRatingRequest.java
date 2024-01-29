package com.example.passengerservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRatingRequest {
    private Float rating;
}
