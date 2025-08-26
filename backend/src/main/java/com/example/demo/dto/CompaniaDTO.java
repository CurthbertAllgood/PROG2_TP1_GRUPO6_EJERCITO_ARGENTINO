package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompaniaDTO {
    private Long id;
    private Integer numero;
    private String actividadPrincipal;
    private List<Long> cuartelIds;
    private List<String> cuartelNombres; 
}
