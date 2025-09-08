package com.agenciaviajes.agenciaviajes.controller;


import com.agenciaviajes.agenciaviajes.dto.ApiResponse;
import com.agenciaviajes.agenciaviajes.model.Reserva;
import com.agenciaviajes.agenciaviajes.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Reserva>>>listarReservas(){
        return ResponseEntity.ok(new ApiResponse<>("success", "Lista de reservas", reservaService.listarTodas()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Reserva>>obtenerReserva(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse<>("success", "Reserva encontrada",reservaService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Reserva>>crearReserva(@RequestBody Reserva reserva){
        return ResponseEntity.ok(new ApiResponse<>("success", "Reserva creada", reservaService.crearReserva(reserva)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Reserva>>actualizarReserva(@PathVariable Long id,@RequestBody Reserva reserva){
        return ResponseEntity.ok(new ApiResponse<>("success", "Reserva actualizada", reservaService.actualizarReserva(id,reserva)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>>eliminarReserva(@PathVariable Long id){
        reservaService.eliminarReserva(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reserva eliminada", null));
    }
























}
