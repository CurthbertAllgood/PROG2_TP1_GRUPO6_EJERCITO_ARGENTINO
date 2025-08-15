package com.example.demo.mapper;

import com.example.demo.dto.ServicioDTO;
import com.example.demo.entity.Servicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
    ServicioDTO toDTO(Servicio e);

    @Mapping(target = "realizados", ignore = true)
    Servicio toEntity(ServicioDTO dto);
}
