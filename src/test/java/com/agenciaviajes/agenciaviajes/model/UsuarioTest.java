package com.agenciaviajes.agenciaviajes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest {

    @Test
    void testCrearUsuarioYGetters(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana Gómez");
        usuario.setEmail("ana@example.com");
        usuario.setTelefono("600987654");

        assertEquals(1L, usuario.getId());
        assertEquals("Ana Gómez", usuario.getNombre());
        assertEquals("ana@example.com", usuario.getEmail());
        assertEquals("600987654", usuario.getTelefono());
    }
}
