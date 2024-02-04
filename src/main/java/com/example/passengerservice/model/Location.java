package com.example.passengerservice.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
