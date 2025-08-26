package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServicioRealizadoDTO {
    private Long id;
    private Long militarId;
    private String militarNombreCompleto;
    private Long servicioId;
    private String servicioCodigo;
    private String servicioDescripcion;
    private LocalDate fecha;
}
