package com.example.demo.service;

import com.example.demo.dto.CuerpoCreateDTO;
import com.example.demo.dto.CuerpoDTO;

import java.util.List;

public interface CuerpoService {
    List<CuerpoDTO> getAll();
    CuerpoDTO getById(Long id);
    CuerpoDTO create(CuerpoCreateDTO dto);
    CuerpoDTO update(Long id, CuerpoCreateDTO dto);
    void delete(Long id);
}
