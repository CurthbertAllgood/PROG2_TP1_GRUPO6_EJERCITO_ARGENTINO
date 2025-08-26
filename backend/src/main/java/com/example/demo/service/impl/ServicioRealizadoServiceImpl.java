package com.example.demo.service.impl;

import com.example.demo.dto.ServicioRealizadoCreateDTO;
import com.example.demo.dto.ServicioRealizadoDTO;
import com.example.demo.entity.Militar;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.ServicioRealizado;
import com.example.demo.mapper.ServicioRealizadoMapper;
import com.example.demo.repository.MilitarRepository;
import com.example.demo.repository.ServicioRealizadoRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.ServicioRealizadoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioRealizadoServiceImpl implements ServicioRealizadoService {

    private final ServicioRealizadoRepository realizadoRepo;
    private final ServicioRepository servicioRepo;
    private final MilitarRepository militarRepo;

    @Transactional
    @Override
    public ServicioRealizadoDTO create(ServicioRealizadoCreateDTO dto) {
        Militar militar = militarRepo.findById(dto.getMilitarId())
                .orElseThrow(() -> new EntityNotFoundException("Militar no encontrado"));
        Servicio servicio = servicioRepo.findById(dto.getServicioId())
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));

        if (realizadoRepo.existsByMilitarIdAndServicioIdAndFecha(
                militar.getId(), servicio.getId(), dto.getFecha())) {
            throw new IllegalArgumentException("Ya existe un registro para ese militar/servicio/fecha");
        }

        ServicioRealizado r = new ServicioRealizado();
        r.setMilitar(militar);
        r.setServicio(servicio);
        r.setFecha(dto.getFecha());
        return ServicioRealizadoMapper.toDto(realizadoRepo.save(r));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!realizadoRepo.existsById(id)) {
            throw new EntityNotFoundException("Registro no encontrado");
        }
        realizadoRepo.deleteById(id);
    }

    @Override
    public List<ServicioRealizadoDTO> findByMilitar(Long militarId) {
        return realizadoRepo.findAll().stream()
                .filter(r -> r.getMilitar().getId().equals(militarId))
                .map(ServicioRealizadoMapper::toDto)
                .toList();
    }

    @Override
    public List<ServicioRealizadoDTO> findByFechaBetween(LocalDate desde, LocalDate hasta) {
        return realizadoRepo.findAll().stream()
                .filter(r -> !r.getFecha().isBefore(desde) && !r.getFecha().isAfter(hasta))
                .map(ServicioRealizadoMapper::toDto)
                .toList();
    }
}
