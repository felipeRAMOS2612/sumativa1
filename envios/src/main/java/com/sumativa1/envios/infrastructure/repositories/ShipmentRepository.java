package com.sumativa1.envios.infrastructure.repositories;

import com.sumativa1.envios.domain.contracts.ShipmentContract;
import com.sumativa1.envios.domain.entities.Shipment;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ShipmentRepository implements ShipmentContract {
    private final List<Shipment> shipments = new ArrayList<Shipment>();

    public ShipmentRepository() {
        shipments.add(new Shipment(1L, "ABC123", "Chile", "En tránsito", "Miami, USA"));
        shipments.add(new Shipment(2L, "XYZ789", "Argentina", "Entregado", "Buenos Aires, Argentina"));
        shipments.add(new Shipment(3L, "LMN456", "Perú", "En tránsito", "Lima, Perú"));
        shipments.add(new Shipment(4L, "DEF012", "Colombia", "En tránsito", "Bogotá, Colombia"));
    }

    public List<Shipment> findAll() {
        return shipments;
    }

    public Optional<Shipment> findByTrackingNumber(String trackingNumber) {
        return shipments.stream()
                .filter(s -> s.getTrackingNumber().equalsIgnoreCase(trackingNumber))
                .findFirst();
    }

    public Shipment findById(Long id) {
        return shipments.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Shipment save(Shipment entity) {
        shipments.add(entity);
        return entity;
    }

    public Shipment update(Long id, Shipment entity) {
        for (int i = 0; i < shipments.size(); i++) {
            if (shipments.get(i).getId().equals(id)) {
                shipments.set(i, entity);
                return entity;
            }
        }
        throw new IllegalArgumentException("Shipment with ID " + entity.getId() + " not found.");
    }
}
