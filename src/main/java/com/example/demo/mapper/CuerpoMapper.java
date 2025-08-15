package com.example.demo.mapper;

import com.example.demo.dto.CuerpoDTO;
import com.example.demo.entity.Cuerpo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CuerpoMapper {
    CuerpoDTO toDTO(Cuerpo e);

    // One-to-many con militares se maneja aparte
    Cuerpo toEntity(CuerpoDTO dto);
}
