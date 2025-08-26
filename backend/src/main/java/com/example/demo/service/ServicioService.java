package com.example.demo.service;

import com.example.demo.dto.ServicioCreateDTO;
import com.example.demo.dto.ServicioDTO;

import java.util.List;

public interface ServicioService {
    List<ServicioDTO> getAll();
    ServicioDTO getById(Long id);
    ServicioDTO create(ServicioCreateDTO dto);
    ServicioDTO update(Long id, ServicioCreateDTO dto);
    void delete(Long id);
}
