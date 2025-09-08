package com.agenciaviajes.agenciaviajes.controller;

import com.agenciaviajes.agenciaviajes.dto.ApiResponse;
import com.agenciaviajes.agenciaviajes.model.Viaje;
import com.agenciaviajes.agenciaviajes.repository.ViajeRepository;
import com.agenciaviajes.agenciaviajes.service.ViajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Viaje>>>listarViajes(){
        return ResponseEntity.ok(new ApiResponse<>("success", "Lista de viajes obtenida",viajeService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Viaje>> obtenerViaje(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>("success", "Viaje encontrado", viajeService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Viaje>>crearViaje(@RequestBody Viaje viaje){
        return ResponseEntity.ok(new ApiResponse<>("success","Viaje creado", viajeService.crearViaje(viaje)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Viaje>>actualizarViaje(@PathVariable Long id, @RequestBody Viaje viaje ){
        return ResponseEntity.ok(new ApiResponse<>("success","Viaje actualizado", viajeService.actualizarViaje(id,viaje)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> eliminarViaje(@PathVariable Long id) {
        viajeService.eliminarViaje(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Viaje eliminado", null));
    }

}
