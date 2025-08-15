package com.example.demo.mapper;

import com.example.demo.dto.SuboficialDTO;
import com.example.demo.entity.Suboficial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuboficialMapper {

    @Mapping(target = "cuartelId",  source = "cuartel.id")
    @Mapping(target = "companiaId", source = "compania.id")
    @Mapping(target = "cuerpoId",   source = "cuerpo.id")
    SuboficialDTO toDTO(Suboficial e);

    @Mapping(target = "id",        source = "id")
    @Mapping(target = "nombre",    source = "nombre")
    @Mapping(target = "apellido",  source = "apellido")
    @Mapping(target = "legajo",    source = "legajo")
    @Mapping(target = "cuartel",   ignore = true)
    @Mapping(target = "compania",  ignore = true)
    @Mapping(target = "cuerpo",    ignore = true)
    @Mapping(target = "servicios", ignore = true)
    Suboficial toEntity(SuboficialDTO dto);
}
