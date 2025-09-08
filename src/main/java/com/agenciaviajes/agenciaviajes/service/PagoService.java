package com.agenciaviajes.agenciaviajes.service;

import com.agenciaviajes.agenciaviajes.exception.ResourceNotFoundException;
import com.agenciaviajes.agenciaviajes.model.Pago;
import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.repository.PagoRepository;
import com.agenciaviajes.agenciaviajes.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }


    public List<Pago> listarTodas(){
        return pagoRepository.findAll();
    }

    public Pago buscarPorId(Long id){
        return pagoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Pago con id " + id + "no encontrado"));
    }

    public Pago crearPago(Pago pago){
        return pagoRepository.save(pago);
    }

    public Pago actualizarPago(Long id , Pago detalles){
        Pago pago = buscarPorId(id);
        pago.setMonto(detalles.getMonto());
        pago.setFechaPago(detalles.getFechaPago());
        pago.setEstado(detalles.getEstado());
        pago.setReserva(detalles.getReserva());
        return pagoRepository.save(pago);
    }

    public void eliminarPago(Long id){
        Pago pago = buscarPorId(id);
        pagoRepository.delete(pago);
    }


}
