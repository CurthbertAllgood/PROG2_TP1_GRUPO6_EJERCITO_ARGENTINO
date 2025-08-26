package com.example.demo.mapper;

import com.example.demo.dto.AsignacionDTO;
import com.example.demo.entity.AsignacionServicio;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AsignacionServicioMapper {

    @Mappings({
            @Mapping(target = "militarId",            source = "militar.id"),
            @Mapping(target = "militarNombreCompleto", expression = "java(src.getMilitar().getNombre() + \" \" + src.getMilitar().getApellido())"),
            @Mapping(target = "servicioId",           source = "servicio.id"),
            @Mapping(target = "servicioCodigo",       source = "servicio.codigo"),
            @Mapping(target = "servicioDescripcion",  source = "servicio.descripcion"),
            @Mapping(target = "fechaProgramada",      source = "fechaProgramada"),
            @Mapping(target = "estado",               source = "estado"),
            @Mapping(target = "fechaRealizacion",     source = "fechaRealizacion")
    })
    AsignacionDTO toDTO(AsignacionServicio src);

    List<AsignacionDTO> toDTOs(List<AsignacionServicio> src);
}
