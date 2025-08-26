package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MilitarCreateDTO {

    @NotBlank private String nombre;
    @NotBlank private String apellido;
    @NotBlank private String dni;

    @NotBlank private String codigoMilitar;


    @NotBlank private String tipo;

    @NotNull private Long cuerpoId;
    @NotNull private Long companiaId;
    @NotNull private Long cuartelId;
}
