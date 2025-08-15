package com.example.demo.mapper;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "militarId", source = "militar.id")
    UsuarioDTO toDTO(Usuario e);

    // passwordHash y militar se resuelven aparte
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "militar",      ignore = true)
    Usuario toEntity(UsuarioDTO dto);
}
