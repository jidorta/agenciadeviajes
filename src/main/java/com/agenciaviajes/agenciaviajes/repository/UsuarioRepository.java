package com.agenciaviajes.agenciaviajes.repository;

import com.agenciaviajes.agenciaviajes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
