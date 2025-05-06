package com.sumativa1.mascotas.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.sumativa1.mascotas.controllers.UserController;
import com.sumativa1.mascotas.entities.User;
import com.sumativa1.mascotas.resources.UserResource;

@Component
public class UserResourceAssembler extends RepresentationModelAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @SuppressWarnings("null")
    @Override
    public UserResource toModel(User entity) {
        UserResource resource = instantiateModel(entity);
        resource.setId(entity.getId());
        resource.setName(entity.getName());

        resource.add(linkTo(methodOn(UserController.class).getUserById(entity.getId())).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));

        return resource;
    }
}
