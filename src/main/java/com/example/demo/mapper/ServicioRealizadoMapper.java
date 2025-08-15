package com.example.demo.mapper;

import com.example.demo.dto.ServicioRealizadoDTO;
import com.example.demo.entity.ServicioRealizado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicioRealizadoMapper {

    @Mapping(target = "militarId",  source = "militar.id")
    @Mapping(target = "servicioId", source = "servicio.id")
    ServicioRealizadoDTO toDTO(ServicioRealizado e);

    // Asociaciones se setean en el Service
    @Mapping(target = "militar",  ignore = true)
    @Mapping(target = "servicio", ignore = true)
    ServicioRealizado toEntity(ServicioRealizadoDTO dto);
}
