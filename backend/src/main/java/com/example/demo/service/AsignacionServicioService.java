package com.example.demo.service;

import com.example.demo.dto.AsignacionCreateDTO;
import com.example.demo.dto.AsignacionDTO;

import java.time.LocalDate;
import java.util.List;

public interface AsignacionServicioService {
    AsignacionDTO crear(AsignacionCreateDTO dto, String actorUsername);
    List<AsignacionDTO> listar(Long militarId, String estadoOpt, String actorRole, Long actorPersonaId);
    AsignacionDTO marcarRealizado(Long id, LocalDate fecha, String actorRole, Long actorPersonaId);
}
