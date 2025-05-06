package com.sumativa1.mascotas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "El rol no puede estar vacío")
    private Long roleId;
}
