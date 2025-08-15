package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ServicioDTO {
    private Long id;

    @NotBlank @Size(max = 120) private String nombre;
    @Size(max = 255)           private String descripcion;
}
