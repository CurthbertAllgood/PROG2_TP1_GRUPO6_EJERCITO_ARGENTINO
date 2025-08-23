package com.example.demo.mapper;

import com.example.demo.dto.CuartelDTO;
import com.example.demo.entity.Cuartel;
import com.example.demo.entity.Compania;

public class CuartelMapper {

    public static CuartelDTO toDto(Cuartel c) {
        CuartelDTO dto = new CuartelDTO();
        dto.setId(c.getId());
        dto.setCodigo(c.getCodigo());
        dto.setNombre(c.getNombre());
        dto.setUbicacion(c.getUbicacion());
        dto.setCompaniaIds(c.getCompanias().stream().map(Compania::getId).toList());
        dto.setCompaniaNombres(c.getCompanias().stream().map(Compania::getActividadPrincipal).toList());
        return dto;
    }
}
