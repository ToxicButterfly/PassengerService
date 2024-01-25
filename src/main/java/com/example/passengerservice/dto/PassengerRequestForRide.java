package com.example.passengerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerRequestForRide {

    private Integer passId;
    private Integer coorX;
    private Integer coorY;
    private Integer coor2X;
    private Integer coor2Y;
    private Float passRating;
}
