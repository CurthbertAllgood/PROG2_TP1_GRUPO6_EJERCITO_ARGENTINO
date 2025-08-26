package com.example.demo.mapper;

import com.example.demo.dto.ServicioDTO;
import com.example.demo.entity.Servicio;

public class ServicioMapper {

    public static ServicioDTO toDto(Servicio s) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId(s.getId());
        dto.setCodigo(s.getCodigo());
        dto.setDescripcion(s.getDescripcion());
        return dto;
    }
}
