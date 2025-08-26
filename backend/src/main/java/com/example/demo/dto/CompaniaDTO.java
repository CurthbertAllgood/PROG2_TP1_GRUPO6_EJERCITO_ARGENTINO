package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompaniaDTO {
    private Long id;
    private Integer numero;
    private String actividadPrincipal;
    private List<Long> cuartelIds;      // para ediciones futuras
    private List<String> cuartelNombres; // para mostrar en listados
}
