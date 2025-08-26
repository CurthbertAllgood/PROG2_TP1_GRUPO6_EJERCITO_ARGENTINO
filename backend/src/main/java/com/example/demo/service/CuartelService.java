package com.example.demo.service;

import com.example.demo.dto.CuartelCreateDTO;
import com.example.demo.dto.CuartelDTO;

import java.util.List;

public interface CuartelService {
    List<CuartelDTO> getAll();
    CuartelDTO getById(Long id);
    CuartelDTO create(CuartelCreateDTO dto);
    CuartelDTO update(Long id, CuartelCreateDTO dto);
    void delete(Long id);
}
