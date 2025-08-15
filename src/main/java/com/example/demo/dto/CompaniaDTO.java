package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CompaniaDTO {
    private Long id;

    @NotNull @Positive private Integer numero;
    private String actividadPrincipal;

    // Relación M:N con cuarteles
    private List<Long> cuartelIds;
}
