package com.example.demo.mapper;

import com.example.demo.dto.MilitarDTO;
import com.example.demo.entity.Militar;

public class MilitarMapper {

    public static MilitarDTO toDto(Militar militar) {
        MilitarDTO dto = new MilitarDTO();
        dto.setId(militar.getId());
        dto.setNombre(militar.getNombre());
        dto.setApellido(militar.getApellido());
        dto.setDni(militar.getDni());
        dto.setCodigoMilitar(militar.getCodigoMilitar());
        dto.setCuerpoNombre(militar.getCuerpo().getDenominacion());
        dto.setCompaniaNombre(militar.getCompania().getActividadPrincipal());
        dto.setCuartelNombre(militar.getCuartel().getNombre());
        return dto;
    }
}
