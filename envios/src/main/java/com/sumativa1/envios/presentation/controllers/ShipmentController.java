package com.sumativa1.envios.presentation.controllers;

import com.sumativa1.envios.application.services.ShipmentService;
import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.presentation.assemblers.ShipmentResourceAssembler;
import com.sumativa1.envios.presentation.dtos.ShipmentRequest;
import com.sumativa1.envios.presentation.resources.ShipmentResource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;
    private final ShipmentResourceAssembler assembler;

    public ShipmentController(ShipmentService shipmentService, ShipmentResourceAssembler assembler) {
        this.shipmentService = shipmentService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResource>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        List<ShipmentResource> resources = shipments.stream()
            .map(assembler::toModel)
            .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("tracking/{trackingNumber}")
    public ResponseEntity<ShipmentResource> getShipment(@PathVariable String trackingNumber) {
        return shipmentService.getShipmentByTrackingNumber(trackingNumber).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public Shipment getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }

    @PostMapping
    public Shipment postMethodName(@Valid @RequestBody ShipmentRequest shipment) {
        return shipmentService.saveShipment(shipment);
    }

    @PutMapping("/{id}")
    public Shipment updateShipment(@PathVariable Long id, @RequestBody Shipment shipment) {
        return shipmentService.updateShipment(id, shipment);
    }
    
}
