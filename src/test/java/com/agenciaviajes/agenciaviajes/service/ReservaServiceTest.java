package com.agenciaviajes.agenciaviajes.service;

import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGuardarReserva() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);

        when(reservaRepository.save(reserva)).thenReturn(reserva);

        Reserva result = reservaService.guardarReserva(reserva);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    void testObtenerReservaPorId() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);

        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));

        Reserva result = reservaService.obtenerReservaPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reservaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodasLasReservas() {
        Reserva r1 = new Reserva();
        r1.setId(1L);
        Reserva r2 = new Reserva();
        r2.setId(2L);

        when(reservaRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Reserva> result = reservaService.obtenerTodasLasReservas();

        assertEquals(2, result.size());
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    void testEliminarReserva() {
        Long id = 1L;

        // Creamos una reserva ficticia que el service encontrará
        Reserva reserva = new Reserva();
        reserva.setId(id);

        // Mockeamos findById para que devuelva la reserva
        when(reservaRepository.findById(id)).thenReturn(Optional.of(reserva));

        // Mockeamos delete
        doNothing().when(reservaRepository).delete(reserva);

        // Llamamos al método
        reservaService.eliminarReserva(id);

        // Verificamos que delete se llamó
        verify(reservaRepository, times(1)).delete(reserva);
    }
}
