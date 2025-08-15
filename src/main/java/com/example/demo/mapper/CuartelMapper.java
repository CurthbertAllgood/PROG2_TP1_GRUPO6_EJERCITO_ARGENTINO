package com.example.demo.mapper;

import com.example.demo.dto.CuartelDTO;
import com.example.demo.entity.Cuartel;
import com.example.demo.entity.Compania;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface CuartelMapper {

    @Mapping(target = "companiaIds", source = "companias", qualifiedByName = "toIds")
    CuartelDTO toDTO(Cuartel e);

    // Ignoramos M:N; se resuelve en el Service
    @Mapping(target = "companias", ignore = true)
    Cuartel toEntity(CuartelDTO dto);

    @Named("toIds")
    default List<Long> toIds(List<Compania> list) {
        if (list == null) return Collections.emptyList();
        return list.stream().filter(Objects::nonNull).map(Compania::getId).toList();
    }
}
