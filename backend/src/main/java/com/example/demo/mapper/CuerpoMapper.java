package com.example.demo.mapper;

import com.example.demo.dto.CuerpoDTO;
import com.example.demo.entity.Cuerpo;

public class CuerpoMapper {
    public static CuerpoDTO toDto(Cuerpo c) {
        CuerpoDTO dto = new CuerpoDTO();
        dto.setId(c.getId());
        dto.setCodigo(c.getCodigo());
        dto.setDenominacion(c.getDenominacion());
        return dto;
    }
}
