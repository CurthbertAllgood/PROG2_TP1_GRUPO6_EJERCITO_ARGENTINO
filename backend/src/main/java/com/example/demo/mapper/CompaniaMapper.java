package com.example.demo.mapper;

import com.example.demo.dto.CompaniaDTO;
import com.example.demo.entity.Compania;
import com.example.demo.entity.Cuartel;

public class CompaniaMapper {

    public static CompaniaDTO toDto(Compania c) {
        CompaniaDTO dto = new CompaniaDTO();
        dto.setId(c.getId());
        dto.setNumero(c.getNumero());
        dto.setActividadPrincipal(c.getActividadPrincipal());
        dto.setCuartelIds(c.getCuarteles().stream().map(Cuartel::getId).toList());
        dto.setCuartelNombres(c.getCuarteles().stream().map(Cuartel::getNombre).toList());
        return dto;
    }
}
