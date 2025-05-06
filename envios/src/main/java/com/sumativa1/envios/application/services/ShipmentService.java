package com.sumativa1.envios.application.services;

import com.sumativa1.envios.application.mappers.ShipmentMapper;
import com.sumativa1.envios.domain.contracts.ShipmentContract;
import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.presentation.assemblers.ShipmentResourceAssembler;
import com.sumativa1.envios.presentation.dtos.ShipmentRequest;
import com.sumativa1.envios.presentation.resources.ShipmentResource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    private final ShipmentMapper shipmentMapper;
    private final ShipmentContract shipmentRepository;
    private final ShipmentResourceAssembler assembler;

    public ShipmentService(ShipmentContract shipmentRepository, ShipmentMapper shipmentMapper, ShipmentResourceAssembler assembler) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentMapper = shipmentMapper;
        this.assembler = assembler;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ShipmentResource> getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber).map(assembler::toModel);
    }

    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

    public Shipment saveShipment(ShipmentRequest shipment) {
        Shipment shipmentEntity = shipmentMapper.toEntity(shipment);
        if(shipmentRepository.findByTrackingNumber(shipmentEntity.getTrackingNumber()).isPresent()) {
            shipmentEntity.setTrackingNumber(shipmentMapper.generateRandomTrackingNumber());
        }
        return shipmentRepository.save(shipmentEntity);
    }

    public Shipment updateShipment(Long id, Shipment shipment) {
        return shipmentRepository.update(id, shipment);
    }
}
