package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CuartelDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String ubicacion;
    private List<Long> companiaIds;
    private List<String> companiaNombres;
}
