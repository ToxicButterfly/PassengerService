package com.example.passengerservice.dto;

import lombok.*;

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
