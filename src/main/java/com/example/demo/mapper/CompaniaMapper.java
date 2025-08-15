package com.example.demo.mapper;

import com.example.demo.dto.CompaniaDTO;
import com.example.demo.entity.Compania;
import com.example.demo.entity.Cuartel;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface CompaniaMapper {

    @Mapping(target = "cuartelIds", source = "cuarteles", qualifiedByName = "toIds")
    CompaniaDTO toDTO(Compania e);

    @Mapping(target = "cuarteles", ignore = true)
    @Mapping(target = "militares", ignore = true)
    Compania toEntity(CompaniaDTO dto);

    @Named("toIds")
    default List<Long> toIds(List<Cuartel> list) {
        if (list == null) return Collections.emptyList();
        return list.stream().filter(Objects::nonNull).map(Cuartel::getId).toList();
    }
}
