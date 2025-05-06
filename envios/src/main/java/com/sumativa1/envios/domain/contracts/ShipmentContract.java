package com.sumativa1.envios.domain.contracts;

import java.util.Optional;

import com.sumativa1.envios.domain.entities.Shipment;

public interface ShipmentContract extends RepositoryContract<Shipment> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
