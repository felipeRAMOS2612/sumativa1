package com.sumativa1.envios.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.infrastructure.implementations.ShipmentJpaRepository;
import com.sumativa1.envios.infrastructure.repositories.ShipmentDbRepository;

@SpringBootTest
class ShipmentDbRepositoryTest {

    private ShipmentDbRepository repository;

    @Autowired
    private ShipmentJpaRepository jpaRepository;

    @Test
    void shouldSaveAndFindShipmentByTrackingNumber() {
        // Arrange
        repository = new ShipmentDbRepository(jpaRepository);
        Shipment shipment = new Shipment(null, "TRACK123", "Chile", "En tránsito", "Santiago");

        // Act
        Shipment saved = repository.save(shipment);
        Optional<Shipment> found = repository.findByTrackingNumber("TRACK123");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Chile", found.get().getDestination());
        assertEquals("En tránsito", found.get().getStatus());
    }

    @Test
    void shouldUpdateExistingShipment() {
        repository = new ShipmentDbRepository(jpaRepository);
        Shipment shipment = new Shipment(null, "TRACK456", "Perú", "En tránsito", "Lima");
        Shipment saved = repository.save(shipment);

        Shipment updatedData = new Shipment(saved.getId(), "TRACK456", "Perú", "Entregado", "Arequipa");
        Shipment updated = repository.update(saved.getId(), updatedData);

        assertEquals("Entregado", updated.getStatus());
        assertEquals("Arequipa", updated.getCurrentLocation());
    }
}