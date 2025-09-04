package com.agenciaviajes.agenciaviajes.repository;

import com.agenciaviajes.agenciaviajes.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<Viaje,Long> {
}
