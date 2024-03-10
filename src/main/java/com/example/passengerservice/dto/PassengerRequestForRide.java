package com.example.passengerservice.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRequestForRide {
    private Integer passId;
    private Integer coorX;
    private Integer coorY;
    private Integer coor2X;
    private Integer coor2Y;
    private Float passRating;

}
