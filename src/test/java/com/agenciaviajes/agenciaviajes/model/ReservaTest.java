package com.agenciaviajes.agenciaviajes.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    @Test
    void testCambioDeEstado() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana Gómez");

        Viaje viaje = new Viaje();
        viaje.setId(10L);
        viaje.setDestino("París");

        Reserva reserva = new Reserva();
        reserva.setId(100L);
        reserva.setUsuario(usuario);
        reserva.setViaje(viaje);
        reserva.setEstado(EstadoReserva.PENDIENTE);

        // Verificamos estado inicial
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());

        // Cambiamos el estado
        reserva.setEstado(EstadoReserva.CONFIRMADA);

        // Verificamos estado cambiado
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana Gómez");

        Viaje viaje = new Viaje();
        viaje.setId(10L);
        viaje.setDestino("París");

        Reserva reserva = new Reserva();
        reserva.setId(100L);
        reserva.setEstado(EstadoReserva.PENDIENTE);
        reserva.setUsuario(usuario);
        reserva.setViaje(viaje);
        String esperado = "Reserva{id=100, estado=PENDIENTE, usuario=Ana Gómez, viaje=París}";
        assertEquals(esperado, reserva.toString());
    }
}
