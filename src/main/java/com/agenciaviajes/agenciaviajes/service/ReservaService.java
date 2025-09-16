package com.agenciaviajes.agenciaviajes.service;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva buscarPorId(Long id){
        return reservaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Reserva con id " + id + "no encontrada" ));
    }

  public List<Reserva>listarTodas(){
        return reservaRepository.findAll();
  }

    public Reserva crearReserva(Reserva reserva){
        System.out.println("Reserva a guardar" + reserva.getId());
        Reserva guardada = reservaRepository.save(reserva);
        System.out.println("Reserva guardada -> " + guardada.getId());
        return guardada;
    }

    public Reserva actualizarReserva(Long id, Reserva detalles){
        Reserva reserva = buscarPorId(id);
        reserva.setEstado(detalles.getEstado());
        reserva.setUsuario(detalles.getUsuario());
        reserva.setViaje(detalles.getViaje());
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id){
       Reserva reserva = obtenerReservaPorId(id);
        reservaRepository.delete(reserva);
    }

    public Reserva guardarReserva(Reserva reserva){
        // Asegurarse de que usuario y viaje no sean nulos y ya persistidos
        if (reserva.getUsuario() == null || reserva.getUsuario().getId() == null) {
            throw new IllegalArgumentException("El usuario debe estar persistido");
        }
        if (reserva.getViaje() == null || reserva.getViaje().getId() == null) {
            throw new IllegalArgumentException("El viaje debe estar persistido");
        }

        // Guardar reserva
        return reservaRepository.save(reserva); // devuelve el objeto con ID asignad
    }

    //Obtener reserva por ID
    public Reserva obtenerReservaPorId(Long id){
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reserva con id " + id + " no encontrada"));
    }

    public List<Reserva> obtenerTodasLasReservas(){
        return reservaRepository.findAll();
    }

    public List<Reserva> listarREservasPorUsuario(Long usuarioId){
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    //Listar todas las reservas de un viaje
    public List<Reserva>listarReservasPorViaje(Long viajeId){
        return reservaRepository.findByViajeId(viajeId);
    }

}
