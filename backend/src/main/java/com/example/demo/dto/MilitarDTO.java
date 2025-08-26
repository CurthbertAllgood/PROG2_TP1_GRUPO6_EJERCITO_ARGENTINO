package com.example.demo.dto;

import lombok.Data;

@Data
public class MilitarDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String codigoMilitar;
    private String cuerpoNombre;
    private String companiaNombre;
    private String cuartelNombre;
}
