package com.example.demo.mapper;

import com.example.demo.dto.ServicioRealizadoDTO;
import com.example.demo.entity.ServicioRealizado;

public class ServicioRealizadoMapper {

    public static ServicioRealizadoDTO toDto(ServicioRealizado r) {
        ServicioRealizadoDTO dto = new ServicioRealizadoDTO();
        dto.setId(r.getId());
        dto.setFecha(r.getFecha());
        dto.setMilitarId(r.getMilitar().getId());
        dto.setMilitarNombreCompleto(r.getMilitar().getNombre() + " " + r.getMilitar().getApellido());
        dto.setServicioId(r.getServicio().getId());
        dto.setServicioCodigo(r.getServicio().getCodigo());
        dto.setServicioDescripcion(r.getServicio().getDescripcion());
        return dto;
    }
}
