package com.sumativa1.envios.presentation.assemblers;

import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.presentation.resources.ShipmentResource;
import com.sumativa1.envios.presentation.controllers.ShipmentController;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ShipmentResourceAssembler extends RepresentationModelAssemblerSupport<Shipment, ShipmentResource> {

    public ShipmentResourceAssembler() {
        super(ShipmentController.class, ShipmentResource.class);
    }

    @SuppressWarnings("null")
    @Override
    public ShipmentResource toModel(Shipment entity) {
        ShipmentResource resource = instantiateModel(entity);
        resource.setId(entity.getId());
        resource.setTrackingNumber(entity.getTrackingNumber());
        resource.setDestination(entity.getDestination());
        resource.setStatus(entity.getStatus());
        resource.setCurrentLocation(entity.getCurrentLocation());

        resource.add(linkTo(methodOn(ShipmentController.class).getShipmentById(entity.getId())).withSelfRel());
        resource.add(linkTo(methodOn(ShipmentController.class).getAllShipments()).withRel("all-shipments"));
        resource.add(linkTo(methodOn(ShipmentController.class).getShipment(entity.getTrackingNumber())).withRel("by-tracking-number"));

        return resource;
    }
}
