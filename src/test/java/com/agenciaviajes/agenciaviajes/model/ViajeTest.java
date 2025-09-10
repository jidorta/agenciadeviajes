package com.agenciaviajes.agenciaviajes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViajeTest {
    @Test
    void testConstructorAndGetters() {
        LocalDate inicio = LocalDate.of(2025, 5, 10);
        LocalDate fin = LocalDate.of(2025, 5, 20);

        Viaje viaje = new Viaje("París", inicio, fin, 1200.0);

        assertEquals("París", viaje.getDestino());
        assertEquals(inicio, viaje.getFechaInicio());
        assertEquals(fin, viaje.getFechaFin());
        assertEquals(1200.0, viaje.getPrecio());
    }

    @Test
    void testSetters() {
        Viaje viaje = new Viaje(1L, "Roma", 500.0);

        viaje.setId(2L);
        viaje.setDestino("París");
        viaje.setPrecio(750.0);

        assertEquals(2L, viaje.getId());
        assertEquals("París", viaje.getDestino());
        assertEquals(750.0, viaje.getPrecio());
    }



}
