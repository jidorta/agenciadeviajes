package com.agenciaviajes.agenciaviajes.controller;


import com.agenciaviajes.agenciaviajes.dto.ApiResponse;
import com.agenciaviajes.agenciaviajes.model.Pago;
import com.agenciaviajes.agenciaviajes.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Pago>>>listarPagos(){
        return ResponseEntity.ok(new ApiResponse<>("success","Lista de Pagos", pagoService.listarTodas()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Pago>>obtenerPago(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse<>("success", "Pago encontrado", pagoService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Pago>>crearPago(@RequestBody Pago pago){
        return ResponseEntity.ok(new ApiResponse<>("Success", "Pago creado", pagoService.crearPago(pago)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Pago>>actualizarPago(@PathVariable Long id, @RequestBody Pago pago){
        return ResponseEntity.ok(new ApiResponse<>("Success", "Pago actualizado", pagoService.actualizarPago(id,pago)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>>eliminarPago(@PathVariable Long id){
        pagoService.eliminarPago(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Pago eliminado", null));
    }



}
