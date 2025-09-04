package com.agenciaviajes.agenciaviajes.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="reserva_id")
    private Reserva reserva;

    private LocalDate fechaPago;
    private double monto;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    public Pago() {
    }

    public Pago(Long id, Reserva reserva, LocalDate fechaPago, double monto, EstadoPago estado) {
        this.id = id;
        this.reserva = reserva;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }
}
