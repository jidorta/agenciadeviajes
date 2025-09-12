package com.agenciaviajes.agenciaviajes.integration;

import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UsuarioServiceIntegrationTest {


    @Autowired
    private UsuarioService usuarioService;

    @Test
    void testGuardarYBuscarUsuario(){

        //Crear y guardar usuario
        Usuario usuario = new Usuario("Juan Pérez", "juan@example.com", "1234", "600123456");
        Usuario guardado = usuarioService.guardarUsuario(usuario);

        assertNotNull(guardado.getId(), "El usuario guardado debe tener un ID");

        Usuario encontrado = usuarioService.buscarPorId(guardado.getId());
        assertEquals("Juan Pérez", encontrado.getNombre());
        assertEquals("juan@example.com", encontrado.getEmail());
    }

    @Test
    void testBuscarUsuarioPorId_existe(){

        Usuario usuario = new Usuario("Juan Pérez", "juan@example.com", "1234", "600123456");
        Usuario guardardo = usuarioService.guardarUsuario(usuario);

        Usuario encontrado = usuarioService.buscarPorId(guardardo.getId());

        assertNotNull(encontrado);
        assertEquals("juan@example.com", encontrado.getEmail());
    }


}
