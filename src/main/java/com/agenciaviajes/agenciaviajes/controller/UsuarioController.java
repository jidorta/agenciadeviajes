package com.agenciaviajes.agenciaviajes.controller;

import com.agenciaviajes.agenciaviajes.dto.ApiResponse;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;


    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<ApiResponse< List<Usuario>>> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(
                new ApiResponse<>("success", "Lista de usuarios obtenida",usuarios)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>> crearUsuario(@RequestBody Usuario usuario){
        Usuario nuevo =  usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("success", "Usuario creado correctamente", nuevo)
        );
    }
}
