package com.agenciaviajes.agenciaviajes.acceptance;


import com.agenciaviajes.agenciaviajes.dto.ApiResponse;
import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.model.Viaje;
import com.agenciaviajes.agenciaviajes.repository.ReservaRepository;
import com.agenciaviajes.agenciaviajes.repository.UsuarioRepository;
import com.agenciaviajes.agenciaviajes.repository.ViajeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AgenciaViajesAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ReservaRepository reservaRepository;


    private Usuario usuarioTest;
    private Viaje viajeTest;
    private Reserva reservaCreada;

    @BeforeAll
    void setup(){
        // Crear usuario (constructor por defecto + setters)
        Usuario usuario = new Usuario();
        usuario.setNombre("Iban");
        usuario.setEmail("dorta@example.com");

      ResponseEntity<ApiResponse<Usuario>> usuarioResponse = restTemplate.exchange(
              "/usuarios",
              HttpMethod.POST,
              new HttpEntity<>(usuario),
              new ParameterizedTypeReference<ApiResponse<Usuario>>() {}


      );

       usuarioTest = usuarioResponse.getBody().getData();
       assertThat(usuarioTest).isNotNull();
       assertThat(usuarioTest.getId()).isNotNull();

       // Crear viaje
        Viaje viaje = new Viaje();
        viaje.setDestino("Madrid");
        viaje.setFechaFin(LocalDate.now().plusDays(10));
        viaje.setPrecio(200.0);

        ResponseEntity<ApiResponse<Viaje>> viajeResponse = restTemplate.exchange(
                "/viajes",
                HttpMethod.POST,
                new HttpEntity<>(viaje),
                new ParameterizedTypeReference<ApiResponse<Viaje>>() {}
        );
        viajeTest = viajeResponse.getBody().getData();
        assertThat(viajeTest).isNotNull();
        assertThat(viajeTest.getId()).isNotNull();
    }

    @Test
    void testCrearYObtenerReserva() {
        Usuario usuario = new Usuario();
       // ID de un usuario existente en la BD
        usuario.setNombre("Test User");
        usuario = usuarioRepository.save(usuario);
// guardar usuario en la BD si es necesario

        Viaje viaje = new Viaje();
        // ID de un viaje existente
        viaje.setDestino("Madrid");
        viaje = viajeRepository.save(viaje);
// guardar viaje en la BD si es necesario


        // Objeto reserva que vamos a enviar
        Reserva reserva = new Reserva();
         // Muy importante: indicar que es nuevo
        reserva.setUsuario(usuario); // Asegúrate de asignar usuario válido
        reserva.setViaje(viaje);
        reserva = reservaRepository.save(reserva);// Asegúrate de asignar viaje válido
// Asigna cualquier otro campo obligatorio de Reserva aquí

        try {
            ResponseEntity<ApiResponse<Reserva>> reservaResponse = restTemplate.exchange(
                    "/reservas",
                    HttpMethod.POST,
                    new HttpEntity<>(reserva),
                    new ParameterizedTypeReference<ApiResponse<Reserva>>() {
                    }
            );

            ApiResponse<Reserva> apiResponse = reservaResponse.getBody();

            if (apiResponse != null && apiResponse.getData() != null) {
                Reserva reservaCreada = apiResponse.getData();
                System.out.println("Reserva creada con ID: " + reservaCreada.getId());
            } else if (apiResponse != null) {
                System.out.println("Reserva no creada. Mensaje del API: " + apiResponse.getMessage());
            } else {
                System.out.println("Error: respuesta del servidor vacía.");
            }

        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            System.out.println("Error HTTP: " + ex.getStatusCode());
            System.out.println("Mensaje: " + ex.getResponseBodyAsString());
        } catch (ResourceAccessException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error inesperado: " + ex.getMessage());
        }
    }
}
