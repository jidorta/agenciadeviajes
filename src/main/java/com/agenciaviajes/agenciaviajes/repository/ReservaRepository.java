package com.agenciaviajes.agenciaviajes.repository;

import com.agenciaviajes.agenciaviajes.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
}
