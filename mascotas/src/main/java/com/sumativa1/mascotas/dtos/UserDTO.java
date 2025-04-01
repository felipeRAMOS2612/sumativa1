package com.sumativa1.mascotas.dtos;

import com.sumativa1.mascotas.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private Number roleId;
    private Role role;
}
