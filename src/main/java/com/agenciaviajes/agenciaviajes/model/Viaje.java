package com.agenciaviajes.agenciaviajes.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="viajes")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;
    private String fechaSalida;
    private String fechaRegreso;
    private Double precio;
    private int plazasDisponibles;
    private String descripcion;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public Viaje() {
    }

    public Viaje(Long id, String destino, String fechaSalida, String fechaRegreso, Double precio, int plazasDisponibles, String descripcion, List<Reserva> reservas) {
        this.id = id;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.precio = precio;
        this.plazasDisponibles = plazasDisponibles;
        this.descripcion = descripcion;
        this.reservas = reservas;
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

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
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
}
