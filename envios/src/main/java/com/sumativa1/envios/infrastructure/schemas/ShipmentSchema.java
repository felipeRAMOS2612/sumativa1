package com.sumativa1.envios.infrastructure.schemas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "shipments")
public class ShipmentSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String trackingNumber;
    private String destination;
    private String status;
    private String currentLocation;
}
