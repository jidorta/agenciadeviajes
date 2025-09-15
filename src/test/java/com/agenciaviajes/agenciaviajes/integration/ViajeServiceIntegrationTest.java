package com.agenciaviajes.agenciaviajes.integration;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.Viaje;
import com.agenciaviajes.agenciaviajes.service.ViajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class ViajeServiceIntegrationTest {

    @Autowired
    private ViajeService viajeService;

    @Test
    void testGuardarViaje(){
        Viaje viaje = new Viaje("Roma", LocalDate.now(), LocalDate.now().plusDays(7), 800.0);
        Viaje guardado = viajeService.crearViaje(viaje);

        assertNotNull(guardado.getId());
        assertEquals("Roma", guardado.getDestino());
    }

    @Test
    void testBuscarViajePorId(){
        Viaje viaje = viajeService.crearViaje(
                new Viaje("Londres", LocalDate.now(), LocalDate.now().plusDays(5), 600.0));

        Viaje encontrado = viajeService.buscarPorId(viaje.getId());

        assertNotNull(encontrado);
        assertEquals("Londres",encontrado.getDestino());
    }

    @Test
    void testActualizarViaje(){
        Viaje viaje = viajeService.crearViaje(
                new Viaje("Madrid", LocalDate.now(), LocalDate.now().plusDays(3), 400.0));
            viaje.setDestino("Barcelona");
            Viaje actualizado = viajeService.actualizarViaje(viaje.getId(),viaje);

            assertEquals("Barcelona",actualizado.getDestino() );
    }

    @Test
    void testEliminarViaje(){
        Viaje viaje = viajeService.crearViaje(
                new Viaje("París", LocalDate.now(), LocalDate.now().plusDays(4), 700.0));

        viajeService.eliminarViaje(viaje.getId());
        assertThrows(ResourceNotFoundException.class,
                ()->viajeService.buscarPorId(viaje.getId()),
                "Debe lanzar excepcion al buscar un viaje eliminado");

    }
}
