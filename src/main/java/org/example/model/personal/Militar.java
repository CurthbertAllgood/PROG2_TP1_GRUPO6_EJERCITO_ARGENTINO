package org.example.model.personal;

import org.example.model.infraestructura.Compania;
import org.example.model.infraestructura.Cuartel;
import org.example.model.infraestructura.Cuerpo;
import org.example.model.servicio.ServicioRealizado;

import java.util.ArrayList;
import java.util.List;

public class Militar extends Persona {

    protected Cuerpo cuerpo;
    protected Compania compania;
    protected Cuartel cuartel;
    protected List<ServicioRealizado> serviciosRealizados;

    public Militar(String nombre, String apellidos) {
        super(nombre, apellidos);
        this.serviciosRealizados = new ArrayList<>();
    }


    public Cuerpo getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Cuerpo cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public Cuartel getCuartel() {
        return cuartel;
    }

    public void setCuartel(Cuartel cuartel) {
        this.cuartel = cuartel;
    }

    public List<ServicioRealizado> getServiciosRealizados() {
        return serviciosRealizados;
    }

    public void setServiciosRealizados(List<ServicioRealizado> serviciosRealizados) {
        this.serviciosRealizados = serviciosRealizados;
    }

    public void agregarServicioRealizado(ServicioRealizado servicio) {
        this.serviciosRealizados.add(servicio);
    }
}
