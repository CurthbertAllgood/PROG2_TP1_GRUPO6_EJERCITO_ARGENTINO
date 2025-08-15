package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CuerpoDTO {
    private Long id;

    @NotBlank @Size(max = 120) private String denominacion;
}
