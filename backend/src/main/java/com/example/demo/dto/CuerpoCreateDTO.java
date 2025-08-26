package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CuerpoCreateDTO {
    @NotBlank private String codigo;
    @NotBlank private String denominacion;
}
