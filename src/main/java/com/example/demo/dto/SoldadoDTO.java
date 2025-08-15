package com.example.demo.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SoldadoDTO {
    private Long id;

    @NotBlank @Size(max = 100) private String nombre;
    @NotBlank @Size(max = 100) private String apellido;
    @NotBlank @Size(max = 40)  private String legajo;

    @NotNull private Long cuartelId;
    @NotNull private Long companiaId;
    @NotNull private Long cuerpoId;
}
