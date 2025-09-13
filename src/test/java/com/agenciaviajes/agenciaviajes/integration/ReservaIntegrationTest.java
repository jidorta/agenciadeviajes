package com.agenciaviajes.agenciaviajes.integration;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.EstadoReserva;
import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.model.Viaje;
import com.agenciaviajes.agenciaviajes.repository.ReservaRepository;
import com.agenciaviajes.agenciaviajes.repository.UsuarioRepository;
import com.agenciaviajes.agenciaviajes.repository.ViajeRepository;
import com.agenciaviajes.agenciaviajes.service.ReservaService;
import com.agenciaviajes.agenciaviajes.service.UsuarioService;
import com.agenciaviajes.agenciaviajes.service.ViajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReservaIntegrationTest {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ViajeService viajeService;

    @Test
    void testGuardarYBuscarReserva(){

       Usuario usuario = new Usuario();
       usuario.setNombre("Ana");
        usuarioService.guardarUsuario(usuario);

        // Creamos viaje
        Viaje viaje = new Viaje();
        viaje.setDestino("Madrid");
        viajeService.crearViaje(viaje);

        // Creamos reserva con usuario y viaje guardados
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setViaje(viaje);
        reserva.setEstado(EstadoReserva.PENDIENTE);

        Reserva guardada = reservaService.guardarReserva(reserva);

        Reserva encontrada = reservaService.buscarPorId(guardada.getId());

        assertNotNull(encontrada);
        assertEquals(usuario.getId(), encontrada.getUsuario().getId());
        assertEquals(viaje.getId(), encontrada.getViaje().getId());

    }

    // Actualizar estado de reserva
    @Test
    void testActualizarEstadoReserva() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Luis");
        usuarioService.guardarUsuario(usuario);

        Viaje viaje = new Viaje();
        viaje.setDestino("Barcelona");
        viajeService.crearViaje(viaje);

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setViaje(viaje);
        reserva.setEstado(EstadoReserva.PENDIENTE);
        reserva = reservaService.guardarReserva(reserva);

        // Cambiamos estado
        reserva.setEstado(EstadoReserva.CONFIRMADA);
        Reserva actualizada = reservaService.guardarReserva(reserva);

        assertEquals(EstadoReserva.CONFIRMADA, actualizada.getEstado());
    }

    @Test
    void testEliminarReserva() {
        // Crear y guardar usuario y viaje
        Usuario usuario = usuarioService.guardarUsuario(
                new Usuario("Ana Gómez", "ana@example.com", "1234", "600987654"));
        Viaje viaje = viajeService.crearViaje(
                new Viaje("París",  LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(7),  500.0));

        // Crear y guardar reserva
        Reserva reserva = new Reserva(usuario, viaje);
        reserva = reservaService.guardarReserva(reserva);

        assertNotNull(reserva.getId(), "La reserva debe tener ID asignado");

        Long idReserva = reserva.getId();

        // Eliminar la reserva
        reservaService.eliminarReserva(idReserva);

        // Verificar que ya no existe
        assertTrue(reservaRepository.findById(idReserva).isEmpty(),
                "La reserva debería haberse eliminado de la base de datos");
    }
}
