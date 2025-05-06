package com.sumativa1.envios.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.sumativa1.envios.application.mappers.ShipmentMapper;
import com.sumativa1.envios.application.services.ShipmentService;
import com.sumativa1.envios.domain.contracts.ShipmentContract;
import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.presentation.assemblers.ShipmentResourceAssembler;
import com.sumativa1.envios.presentation.resources.ShipmentResource;


@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock
    private ShipmentContract shipmentRepository;

    @Mock
    private ShipmentMapper shipmentMapper;

    @Mock
    private ShipmentResourceAssembler assembler;

    @InjectMocks
    private ShipmentService service;

    @Test
    void shouldReturnAllShipments_whenGetAllShipmentsCalled() {
        List<Shipment> fakeList = List.of(new Shipment(1L, "123", "Puerto Montt", "Procesando", "Puerto Varas"));
        when(shipmentRepository.findAll()).thenReturn(fakeList);

        List<Shipment> result = service.getAllShipments();

        assertEquals(1, result.size());
        assertEquals("123", result.get(0).getTrackingNumber());
    }

    @Test
    void shouldReturnShipment_whenGetShipmentByTrackingNumberCalled() {
        Shipment fake = new Shipment(1L, "123", "Puerto Montt", "Procesando", "Puerto Varas");
        when(shipmentRepository.findByTrackingNumber("123")).thenReturn(Optional.of(fake));
        when(assembler.toModel(fake)).thenReturn(new ShipmentResource());

        Optional<ShipmentResource> result = service.getShipmentByTrackingNumber("123");

        assertTrue(result.isPresent());
    }
}