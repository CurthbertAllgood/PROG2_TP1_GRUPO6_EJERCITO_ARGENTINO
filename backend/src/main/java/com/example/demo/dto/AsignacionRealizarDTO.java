package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AsignacionRealizarDTO {
    // Opcional: si viene null, el server usa la fecha actual
    private LocalDate fechaRealizacion;
}
