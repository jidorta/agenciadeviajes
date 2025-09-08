package com.agenciaviajes.agenciaviajes.service;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario con id" + id + "no encontrado"));

    }

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario detalles){
        Usuario usuario = buscarPorId(id);
        usuario.setNombre(detalles.getNombre());
        usuario.setEmail(detalles.getEmail());
        usuario.setTelefono(detalles.getTelefono());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id){
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }


}
