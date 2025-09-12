package com.agenciaviajes.agenciaviajes.integration;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.EstadoReserva;
import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.model.Viaje;
import com.agenciaviajes.agenciaviajes.service.ReservaService;
import com.agenciaviajes.agenciaviajes.service.UsuarioService;
import com.agenciaviajes.agenciaviajes.service.ViajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReservaServiceIntegrationTest {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ViajeService viajeService;

    @Test
    void testGuardarReserva() {

        Usuario usuario = usuarioService.guardarUsuario(
                new Usuario("Ana Gómez", "ana@example.com", "1234", "600987654"));

        Viaje viaje = viajeService.crearViaje(
                new Viaje("Viaje a París", "París", 500.0));

        // Crear reserva
        Reserva reserva = new Reserva(usuario, viaje);

        //Guardar reserva
        Reserva guardada = reservaService.guardarReserva(reserva);

        //Comprobaciones
        assertNotNull(guardada.getId(), "La reserva debe tener un ID asignado");
        assertEquals(usuario.getId(), guardada.getUsuario().getId(), "Usuario debe coincidir");
        assertEquals(viaje.getId(), guardada.getViaje().getId(), "Viaje debe coincidir");
        assertEquals(EstadoReserva.PENDIENTE, guardada.getEstado(), "Estado por defecto debe ser PENDIENTE");

        assertNotNull(guardada.getId(), "La reserva debe tener un ID asignado");

        // 3️⃣ Eliminar reserva
        reservaService.eliminarReserva(guardada.getId());

        // Comprobar que al buscar lanza excepción
        assertThrows(ResourceNotFoundException.class,
                () -> reservaService.obtenerReservaPorId(reserva.getId()));
    }
}
