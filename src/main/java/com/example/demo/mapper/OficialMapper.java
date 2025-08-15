package com.example.demo.mapper;

import com.example.demo.dto.OficialDTO;
import com.example.demo.entity.Oficial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OficialMapper {

    @Mapping(target = "cuartelId",  source = "cuartel.id")
    @Mapping(target = "companiaId", source = "compania.id")
    @Mapping(target = "cuerpoId",   source = "cuerpo.id")
    OficialDTO toDTO(Oficial e);

    @Mapping(target = "id",        source = "id")
    @Mapping(target = "nombre",    source = "nombre")
    @Mapping(target = "apellido",  source = "apellido")
    @Mapping(target = "legajo",    source = "legajo")
    @Mapping(target = "cuartel",   ignore = true)
    @Mapping(target = "compania",  ignore = true)
    @Mapping(target = "cuerpo",    ignore = true)
    @Mapping(target = "servicios", ignore = true)
    Oficial toEntity(OficialDTO dto);
}
