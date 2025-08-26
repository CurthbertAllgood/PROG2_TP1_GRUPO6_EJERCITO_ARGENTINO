package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServicioCreateDTO {
    @NotBlank private String codigo;
    @NotBlank private String descripcion;
}
