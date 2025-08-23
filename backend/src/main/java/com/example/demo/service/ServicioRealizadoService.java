package com.example.demo.service;

import com.example.demo.dto.ServicioRealizadoCreateDTO;
import com.example.demo.dto.ServicioRealizadoDTO;

import java.time.LocalDate;
import java.util.List;

public interface ServicioRealizadoService {
    ServicioRealizadoDTO create(ServicioRealizadoCreateDTO dto);
    void delete(Long id);
    List<ServicioRealizadoDTO> findByMilitar(Long militarId);
    List<ServicioRealizadoDTO> findByFechaBetween(LocalDate desde, LocalDate hasta);
}
