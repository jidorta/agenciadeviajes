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
        return reservaRepository.save(reserva);
    }

    public Reserva actualizarReserva(Long id, Reserva detalles){
        Reserva reserva = buscarPorId(id);
        reserva.setEstado(detalles.getEstado());
        reserva.setUsuario(detalles.getUsuario());
        reserva.setViaje(detalles.getViaje());
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id){
        Reserva reserva = buscarPorId(id);
        reservaRepository.delete(reserva);
    }

    public Reserva guardarReserva(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    //Obtener reserva por ID
    public Reserva obtenerReservaPorId(Long id){
        return reservaRepository.findById(id)
                .orElse(null);
    }

    public List<Reserva> obtenerTodasLasReservas(){
        return reservaRepository.findAll();
    }

}
