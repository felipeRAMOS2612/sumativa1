package com.sumativa1.envios.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.sumativa1.envios.domain.contracts.ShipmentContract;
import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.infrastructure.implementations.ShipmentJpaRepository;
import com.sumativa1.envios.infrastructure.schemas.ShipmentSchema;


@Repository
@Primary
public class ShipmentDbRepository implements ShipmentContract {

    private final ShipmentJpaRepository jpa;

    public ShipmentDbRepository(ShipmentJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Shipment> findAll() {
        return jpa.findAll().stream()
                  .map(this::toEntity)
                  .toList();
    }

    @Override
    public Shipment findById(Long id) {
        return jpa.findById(id)
                  .map(this::toEntity)
                  .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    @Override
    public Optional<Shipment> findByTrackingNumber(String trackingNumber) {
        return jpa.findByTrackingNumber(trackingNumber)
                  .map(this::toEntity);
    }

    @Override
    public Shipment save(Shipment entity) {
        ShipmentSchema schema = toSchema(entity);
        return toEntity(jpa.save(schema));
    }

    @Override
    public Shipment update(Long id, Shipment entity) {
        if (!jpa.existsById(id)) throw new RuntimeException("Not found");
        ShipmentSchema updated = toSchema(entity);
        updated.setId(id);
        return toEntity(jpa.save(updated));
    }

    // Métodos de mapeo
    private Shipment toEntity(ShipmentSchema schema) {
        return new Shipment(
            schema.getId(),
            schema.getTrackingNumber(),
            schema.getDestination(),
            schema.getStatus(),
            schema.getCurrentLocation()
        );
    }

    private ShipmentSchema toSchema(Shipment entity) {
        ShipmentSchema schema = new ShipmentSchema();
        schema.setId(entity.getId());
        schema.setTrackingNumber(entity.getTrackingNumber());
        schema.setDestination(entity.getDestination());
        schema.setStatus(entity.getStatus());
        schema.setCurrentLocation(entity.getCurrentLocation());
        return schema;
    }
}

