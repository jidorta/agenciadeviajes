package com.agenciaviajes.agenciaviajes.repository;

import com.agenciaviajes.agenciaviajes.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    //Devuelve todas las reservas de un usuario
    List<Reserva> findByUsuarioId(Long usuarioId);

    //Devuelve todas las reservas de un viaje
    List<Reserva>findByViajeId(Long viajeId);
}
