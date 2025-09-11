package com.agenciaviajes.agenciaviajes.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="viaje_id", nullable = false)
    private Viaje viaje;

    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
    private int numerodePlazas;

    @OneToOne(mappedBy = "reserva",cascade = CascadeType.ALL)
    private Pago pago;

    public Reserva() {
    }

    public Reserva(Long id, Usuario usuario, Viaje viaje, LocalDate fechaReserva, EstadoReserva estado, int numerodePlazas, Pago pago) {
        this.id = id;
        this.usuario = usuario;
        this.viaje = viaje;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.numerodePlazas = numerodePlazas;
        this.pago = pago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public int getNumerodePlazas() {
        return numerodePlazas;
    }

    public void setNumerodePlazas(int numerodePlazas) {
        this.numerodePlazas = numerodePlazas;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @Override
    public String toString(){
        return "Reserva{" +
                "id=" + id +
                ", estado=" + estado +
                ", usuario=" + (usuario != null ? usuario.getNombre() : "null") +
                ", viaje=" + (viaje != null ? viaje.getDestino() : "null") +
                '}';
    }
}
