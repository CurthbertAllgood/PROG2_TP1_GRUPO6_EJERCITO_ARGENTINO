package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;

@Data
public class CuartelCreateDTO {
    @NotBlank private String codigo;
    @NotBlank private String nombre;
    @NotBlank private String ubicacion;

    // opcional: asociar compañías al crear/editar
    private Set<Long> companiaIds;
}
