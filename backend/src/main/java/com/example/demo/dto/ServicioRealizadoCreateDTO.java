package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ServicioRealizadoCreateDTO {
    @NotNull private Long militarId;
    @NotNull private Long servicioId;
    @NotNull private LocalDate fecha;   // yyyy-MM-dd
}
