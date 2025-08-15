package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ServicioRealizadoDTO {
    private Long id;

    @NotNull private Long militarId;
    @NotNull private Long servicioId;

    private LocalDate fecha;           // opcional, default hoy si querés en el service
    @Positive private Integer horas;   // opcional
    private String observaciones;      // max 255 (validalo si querés)
}
