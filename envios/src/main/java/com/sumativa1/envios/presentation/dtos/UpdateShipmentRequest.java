package com.sumativa1.envios.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateShipmentRequest {
    @NotBlank
    @Pattern(regexp = "Enviado|Recibido")
    private String status;
}
