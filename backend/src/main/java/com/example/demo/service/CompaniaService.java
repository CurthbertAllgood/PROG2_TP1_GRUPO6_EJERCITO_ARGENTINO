package com.example.demo.service;

import com.example.demo.dto.CompaniaCreateDTO;
import com.example.demo.dto.CompaniaDTO;

import java.util.List;

public interface CompaniaService {
    List<CompaniaDTO> getAll();
    CompaniaDTO getById(Long id);
    CompaniaDTO create(CompaniaCreateDTO dto);
    CompaniaDTO update(Long id, CompaniaCreateDTO dto);
    void delete(Long id);
}
