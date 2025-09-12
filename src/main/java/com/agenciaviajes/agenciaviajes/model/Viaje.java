package com.agenciaviajes.agenciaviajes.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="viajes")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
    private Double precio;
    private int plazasDisponibles;
    private String descripcion;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public Viaje() {
    }
    public Viaje(String destino, LocalDate fechaInicio, LocalDate fechaFin, Double precio) {
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
    }
    public Viaje(Long id, String destino, LocalDate fechaInicio, LocalDate fechaFin, Double precio, int plazasDisponibles, String descripcion, List<Reserva> reservas) {
        this.id = id;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.plazasDisponibles = plazasDisponibles;
        this.descripcion = descripcion;
        this.reservas = reservas;
    }

    public Viaje(long l, String londres, LocalDate of, LocalDate of1, double v) {
    }
    public Viaje( String londres, LocalDate of, LocalDate of1, double v) {
    }

    public Viaje(long l, String parís, double v) {
    }

    public Viaje(String viajeAParís, String parís, double v) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "Viaje{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", precio=" + precio +
                '}';
    }
}
