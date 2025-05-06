package com.sumativa1.envios.application.mappers;

import org.springframework.stereotype.Component;
import com.sumativa1.envios.common.mappers.EntityMapper;
import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.presentation.dtos.ShipmentRequest;

@Component
public class ShipmentMapper extends EntityMapper<ShipmentRequest, Shipment> {

    @Override
    public Shipment toEntity(ShipmentRequest dto) {
        return new Shipment(null, generateRandomTrackingNumber(), dto.getDestination(), "Procesado", dto.getCurrent_location());
    }

    @Override
    public ShipmentRequest toDto(Shipment entity) {
        ShipmentRequest dto = new ShipmentRequest();
        dto.setDestination(entity.getDestination());
        dto.setCurrent_location(entity.getCurrentLocation());
        return dto;
    }
    
    public String generateRandomTrackingNumber() {
        int number = (int)(Math.random() * 1_0000_0000);
        return String.format("%08d", number);
    }
}
