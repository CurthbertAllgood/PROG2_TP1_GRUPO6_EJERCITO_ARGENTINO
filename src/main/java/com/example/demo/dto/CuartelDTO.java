package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CuartelDTO {
    private Long id;

    @NotBlank @Size(max = 120) private String nombre;
    @Size(max = 200)           private String ubicacion;

    // Relación M:N con compañía (opcional exponerla en listado)
    private List<Long> companiaIds;
}
