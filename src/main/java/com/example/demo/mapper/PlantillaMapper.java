package com.example.demo.mapper;


public interface PlantillaMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    void updateEntityFromDto(D dto, @org.mapstruct.MappingTarget E entity);
}
