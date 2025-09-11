package com.agenciaviajes.agenciaviajes.service;

import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana Gómez");
        usuario.setEmail("ana@example.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario guardado = usuarioService.guardarUsuario(usuario);

        assertNotNull(guardado);
        assertEquals("Ana Gómez", guardado.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testBuscarPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Carlos Pérez");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario encontrado = usuarioService.buscarPorId(1L);

        assertNotNull(encontrado);
        assertEquals("Carlos Pérez", encontrado.getNombre());
        verify(usuarioRepository, times(1)).findById(1L);
    }
}
