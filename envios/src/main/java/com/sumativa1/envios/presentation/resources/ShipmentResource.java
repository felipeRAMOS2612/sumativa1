package com.sumativa1.envios.presentation.resources;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentResource extends RepresentationModel<ShipmentResource> {
    private Long id;
    private String trackingNumber;
    private String destination;
    private String status;
    private String currentLocation;
}
