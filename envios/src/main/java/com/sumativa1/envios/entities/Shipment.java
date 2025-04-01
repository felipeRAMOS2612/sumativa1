package com.sumativa1.envios.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Shipment {
    private Long id;
    private String trackingNumber;
    private String destination;
    private String status;
    private String currentLocation;
}