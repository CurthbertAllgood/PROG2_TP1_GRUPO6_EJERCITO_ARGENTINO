package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AsignacionCreateDTO {
    @NotNull private Long militarId;
    @NotNull private Long servicioId;
    @NotNull private LocalDate fechaProgramada; // yyyy-MM-dd
}
