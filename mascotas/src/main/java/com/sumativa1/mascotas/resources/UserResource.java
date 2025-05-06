package com.sumativa1.mascotas.resources;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource extends RepresentationModel<UserResource> {
    private Long id;
    private String name;
    private Long roleId;
}
