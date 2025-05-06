package com.sumativa1.envios.infrastructure.implementations;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa1.envios.infrastructure.schemas.ShipmentSchema;

public interface ShipmentJpaRepository extends JpaRepository<ShipmentSchema, Long> {
    Optional<ShipmentSchema> findByTrackingNumber(String trackingNumber);
}