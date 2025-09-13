package com.agenciaviajes.agenciaviajes.integration;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.EstadoReserva;
import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.model.Viaje;
import com.agenciaviajes.agenciaviajes.service.ReservaService;
import com.agenciaviajes.agenciaviajes.service.UsuarioService;
import com.agenciaviajes.agenciaviajes.service.ViajeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    private Usuario usuario;
    private Viaje viaje;

    @BeforeEach
    void setUp(){
        usuario = usuarioService.guardarUsuario(new Usuario("Ana Gómez", "ana@example.com", "1234", "600987654"));
        viaje = viajeService.crearViaje(new Viaje("París", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7),  500.0));
    }


    @Test
    void testGuardarReserva() {

        Usuario usuario = usuarioService.guardarUsuario(
                new Usuario("Ana Gómez", "ana@example.com", "1234", "600987654"));

        Viaje viaje = viajeService.crearViaje(
                new Viaje("París", LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(7),  500.0));

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

    @Test
    void testEliminarReserva(){
        Reserva reserva = reservaService.guardarReserva(new Reserva(usuario,viaje));
        Long id = reserva.getId();
        reservaService.eliminarReserva(id);
        assertThrows(ResourceNotFoundException.class, () ->reservaService.obtenerReservaPorId(id));
    }

    @Test
    @Transactional
    void testListarReservas(){
        //Crear y guardar usuario
        Usuario usuario = usuarioService.guardarUsuario(
                new Usuario("Carlos Pérez", "carlos@example.com", "1234", "600111222"));

        // Crear y guardar viajes
        Viaje viaje1 = viajeService.crearViaje(
                new Viaje("Roma", LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(7),  300.0));
        Viaje viaje2 = viajeService.crearViaje(
                new Viaje("Londres", LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(7),  450.0));

        // Crear y guardar reservas
        Reserva reserva1 = reservaService.guardarReserva(new Reserva(usuario,viaje1));
        Reserva reserva2 = reservaService.guardarReserva(new Reserva(usuario,viaje2));

        //Ejecutar
        List<Reserva> reservas = reservaService.listarTodas();

        //Verificar
        assertNotNull(reservas);
        assertTrue(reservas.size()>=2, "Debe haber al menos 2 reservas guardadas");
        assertTrue(reservas.stream().anyMatch(r ->r.getId().equals(reserva1.getId())));
        assertTrue(reservas.stream().anyMatch(r -> r.getId().equals(reserva2.getId())));
    }

    @Test
    void testObtenerReservaPorId_existe(){

        //Crear y guardar usuario y viaje
        Usuario usuario = usuarioService.guardarUsuario(
                new Usuario("Ana Gómez", "ana@example.com", "1234", "600987654"));
        Viaje viaje = viajeService.crearViaje(new Viaje("París", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7),  500.0));

        //Crear y guardar reserva
        Reserva reserva = new Reserva(usuario,viaje);
        reservaService.guardarReserva(reserva);

        //Obtener reserva por ID
        Reserva encontrada = reservaService.obtenerReservaPorId(reserva.getId());

        //Comprobar que se ha encontrado correctamente
        assertNotNull(encontrada, "La reserva debe existir");
        assertEquals(reserva.getId(), encontrada.getId(), "El ID debe coincidir");
        assertEquals(usuario.getId(), encontrada.getUsuario().getId(), "El usuario debe coincidir");
        assertEquals(viaje.getId(), encontrada.getViaje().getId(), "El viaje debe coincidir");
    }

    @Test
    void testObtenerTodasLasReservas(){
        //Crear y guardar usuarios y viajes de prueba
        Usuario usuario1 = usuarioService.guardarUsuario(new Usuario("Ana Gómez", "ana@example.com", "1234", "600987654"));
        Usuario usuario2 = usuarioService.guardarUsuario(new Usuario("Pedro López", "pedro@example.com", "abcd", "600123456"));

        Viaje viaje1 = viajeService.crearViaje(new Viaje( "París", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7),  500.0));
        Viaje viaje2 = viajeService.crearViaje(new Viaje( "Roma", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7),  600.0));

        //Crear y guardar reservas
        Reserva reserva1 = reservaService.guardarReserva(new Reserva(usuario1, viaje1));
        Reserva reserva2 = reservaService.guardarReserva(new Reserva(usuario2, viaje2));

        //Obtener todas las reservas
        List<Reserva> reservas = reservaService.obtenerTodasLasReservas();

        //Comprobaciones
        assertNotNull(reservas, "La lista de reservas no debe ser null");
        assertTrue(reservas.size() >= 2, "Debe haber al menos dos reservas");
        assertTrue(reservas.contains(reserva1), "Debe contener la reserva1");
        assertTrue(reservas.contains(reserva2), "Debe contener la reserva2");
    }

    @Test
    void testActualizarEstadoReserva(){

        //Crear y guardar usuario y viaje
        Usuario usuario = usuarioService.guardarUsuario(new Usuario("Ana Gómez", "ana@example.com", "1234", "600987654"));
        Viaje viaje = viajeService.crearViaje(new Viaje("Paris",  LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7),  500.0));

        //Guardar reserva
        Reserva reserva = reservaService.guardarReserva(new Reserva(usuario,viaje));

        //Cambiar estado
        reserva.setEstado(EstadoReserva.CONFIRMADA);
        Reserva actualizada = reservaService.guardarReserva(reserva);

        //Comprobaciones
        assertNotNull(actualizada);
        assertEquals(EstadoReserva.CONFIRMADA,actualizada.getEstado());


    }

    @Test
    void testObtenerReservasPorUsuario(){

        Usuario usuario = usuarioService.guardarUsuario(
                new Usuario("Carlos López", "carlos@example.com", "abcd", "600123456"));
        Viaje viaje1 = viajeService.crearViaje(new Viaje( "Londres", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7),  400.0));
        Viaje viaje2 = viajeService.crearViaje(new Viaje("Roma", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(7),  450.0));

        //Guardar reservas
        reservaService.guardarReserva(new Reserva(usuario, viaje1));
        reservaService.guardarReserva(new Reserva(usuario, viaje2));

        //Obtener reservas del usuario
        List<Reserva> reservas = reservaService.listarREservasPorUsuario(usuario.getId());

        //Comprobaciones
        assertNotNull(reservas);
        assertEquals(2, reservas.size());
        assertTrue(reservas.stream().allMatch(r ->r.getUsuario().getId().equals(usuario.getId())));

    }


    @Test
    void testActualizarReserva() {
        Usuario usuario = usuarioService.guardarUsuario(new Usuario("Carlos López", "carlos@example.com", "abcd", "600111222"));
        Viaje viaje = viajeService.crearViaje(
                new Viaje("Barcelona", LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 120.0));

        Viaje viaje2 = viajeService.crearViaje(
                new Viaje("Sevilla", LocalDate.now().plusDays(15), LocalDate.now().plusDays(20), 150.0)
        );

        //Crear y guardar reserva
        Reserva reserva =new Reserva(usuario,viaje,2);
        reserva.setEstado(EstadoReserva.CONFIRMADA);
        Reserva guardada = reservaService.guardarReserva(reserva);

        guardada.setNumerodePlazas(4);
        Reserva actualizada = reservaService.actualizarReserva(reserva.getId(), guardada);

        //Verificar
        assertNotNull(actualizada);
        assertEquals("Carlos López", actualizada.getUsuario().getNombre(),
                "El nombre del usuario debe haberse actualizado");
        assertEquals("Barcelona", actualizada.getViaje().getDestino(),
                "El destino del viaje debe haberse actualizado");
        assertEquals(EstadoReserva.CONFIRMADA, actualizada.getEstado(),
                "El estado debe haberse actualizado");
    }
}
