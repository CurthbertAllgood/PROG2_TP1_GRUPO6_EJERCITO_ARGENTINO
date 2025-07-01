package org.example.model.servicio;

import org.example.model.personal.Militar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServicioRealizado {
    private Militar soldado;
    private LocalDateTime fecha;
    private Servicio servicio;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public ServicioRealizado(Militar soldado, LocalDateTime fecha, Servicio servicio) {
        this.soldado = soldado;
        this.fecha = fecha;
        this.servicio = servicio;
    }

    public Militar getSoldado() {
        return soldado;
    }

    public void setSoldado(Militar soldado) {
        this.soldado = soldado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getFechaFormateada() {
        return fecha.format(FORMATO);
    }

    @Override
    public String toString() {
        return "ServicioRealizado{" +
                "soldado=" + soldado.getNombre() + " " + soldado.getApellidos() +
                ", fecha=" + getFechaFormateada() +
                ", servicio=" + servicio.getDescripcionServicio() +
                '}';
    }
}
