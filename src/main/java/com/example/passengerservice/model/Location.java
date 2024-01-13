package com.example.passengerservice.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Location {
    @NotEmpty
    @Size(min = 1, max = 10000)
    private Integer coorX;
    @NotEmpty
    @Size(min = 1, max = 10000)
    private Integer coorY;
    @NotEmpty
    @Size(min = 1, max = 10000)
    private Integer coor2X;
    @NotEmpty
    @Size(min = 1, max = 10000)
    private Integer coor2Y;
}
