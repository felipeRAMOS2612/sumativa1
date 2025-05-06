package com.sumativa1.envios.controllers;

import com.sumativa1.envios.application.services.ShipmentService;
import com.sumativa1.envios.domain.entities.Shipment;
import com.sumativa1.envios.presentation.assemblers.ShipmentResourceAssembler;
import com.sumativa1.envios.presentation.controllers.ShipmentController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShipmentController.class)
class TestShipmentController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShipmentService shipmentService;

    @MockitoBean
    private ShipmentResourceAssembler assembler;

    @Test
    void shouldReturn200_whenGetAllShipments() throws Exception {
        Shipment mockShipment = new Shipment(1L, "123456", "Destino", "Estado", "Ubicación actual");
        when(shipmentService.getAllShipments()).thenReturn(List.of(mockShipment));

        mockMvc.perform(get("/shipments"))
               .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404_whenTrackingNumberNotFound() throws Exception {
        when(shipmentService.getShipmentByTrackingNumber("XYZ")).thenReturn(Optional.empty());

        mockMvc.perform(get("/shipments/tracking/XYZ"))
               .andExpect(status().isNotFound());
    }
}
