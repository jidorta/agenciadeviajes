package com.agenciaviajes.agenciaviajes.controller;

import com.agenciaviajes.agenciaviajes.dto.ApiResponse;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>> listarUsuarios() {
        return ResponseEntity.ok(new ApiResponse<>("success", "Lista de usuarios obtenida", usuarioService.listarTodos()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>("success", "Usuario encontrado", usuarioService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(new ApiResponse<>("Success", "Usuario creado", usuarioService.crearUsuario(usuario)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(new ApiResponse<>("success", "Usuario actualizado", usuarioService.actualizarUsuario(id, usuario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Usuario eliminado", null));
    }
}
































}
