package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;

@Data
public class CompaniaCreateDTO {
    @NotNull private Integer numero;
    @NotBlank private String actividadPrincipal;

    private Set<Long> cuartelIds;
}
