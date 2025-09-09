package com.agenciaviajes.agenciaviajes.service;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.Usuario;
import com.agenciaviajes.agenciaviajes.model.Viaje;
import com.agenciaviajes.agenciaviajes.repository.ViajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {

    private final ViajeRepository viajeRepository;

    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    public List<Viaje> listarTodos(){
        return viajeRepository.findAll();
    }


    public Viaje buscarPorId(Long id){
        return viajeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario con id" + id + "no encontrado"));

    }

    public Viaje crearViaje(Viaje viaje){
        return viajeRepository.save(viaje);
    }

    public Viaje actualizarViaje(Long id, Viaje detalles) {
        Viaje viaje = buscarPorId(id);
        viaje.setDestino(detalles.getDestino());
        viaje.setFechaInicio(detalles.getFechaInicio());
        viaje.setFechaFin(detalles.getFechaFin());
        viaje.setPrecio(detalles.getPrecio());
        return viajeRepository.save(viaje);
    }



    public boolean eliminarViaje(Long id){
        return viajeRepository.findById(id).map(viaje->{
            viajeRepository.delete(viaje);
            return true;
        }).orElse(false);
    }

}
