package com.example.demo.mapper;


import com.example.demo.dto.SoldadoDTO;
import com.example.demo.entity.Soldado;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SoldadoMapper extends PlantillaMapper<Soldado, SoldadoDTO> {

    SoldadoDTO toDTO(Soldado e);

    @Override
    @Mapping(target = "cuartelId",  source = "cuartel.id")
    @Mapping(target = "companiaId", source = "compania.id")
    @Mapping(target = "cuerpoId",   source = "cuerpo.id")
    SoldadoDTO toDto(Soldado e);

    @Override
    @Mapping(target = "cuartel",   ignore = true)
    @Mapping(target = "compania",  ignore = true)
    @Mapping(target = "cuerpo",    ignore = true)
    @Mapping(target = "servicios", ignore = true)
    Soldado toEntity(SoldadoDTO dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SoldadoDTO dto, @MappingTarget Soldado entity);
}
