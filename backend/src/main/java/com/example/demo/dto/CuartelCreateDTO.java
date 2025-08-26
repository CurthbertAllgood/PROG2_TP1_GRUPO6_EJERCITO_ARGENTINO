package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;

@Data
public class CuartelCreateDTO {
    @NotBlank private String codigo;
    @NotBlank private String nombre;
    @NotBlank private String ubicacion;

    private Set<Long> companiaIds;
}
