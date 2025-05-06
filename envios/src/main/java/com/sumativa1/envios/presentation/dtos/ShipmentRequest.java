package com.sumativa1.envios.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShipmentRequest {
    @NotBlank
    @Size(max = 255)
    private String destination;

    @NotBlank
    @Size(max = 255)
    private String current_location;
}