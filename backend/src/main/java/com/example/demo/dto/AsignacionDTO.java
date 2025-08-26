package com.example.demo.dto;

import com.example.demo.entity.AsignacionServicio.Estado;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AsignacionDTO {
    private Long id;
    private Long militarId;
    private String militarNombreCompleto;
    private Long servicioId;
    private String servicioCodigo;
    private String servicioDescripcion;
    private LocalDate fechaProgramada;
    private Estado estado;
    private LocalDate fechaRealizacion;
}
