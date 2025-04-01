package com.sumativa1.envios.contracts;

import java.util.Optional;

import com.sumativa1.envios.entities.Shipment;

public interface ShipmentContract extends RepositoryContract<Shipment> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
