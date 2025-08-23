package com.example.demo.service.impl;

import com.example.demo.dto.ServicioCreateDTO;
import com.example.demo.dto.ServicioDTO;
import com.example.demo.entity.Servicio;
import com.example.demo.mapper.ServicioMapper;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.ServicioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    @Override
    public List<ServicioDTO> getAll() {
        return servicioRepository.findAll().stream().map(ServicioMapper::toDto).toList();
    }

    @Override
    public ServicioDTO getById(Long id) {
        Servicio s = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        return ServicioMapper.toDto(s);
    }

    @Transactional
    @Override
    public ServicioDTO create(ServicioCreateDTO dto) {
        if (servicioRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un servicio con código " + dto.getCodigo());
        }
        Servicio s = new Servicio();
        s.setCodigo(dto.getCodigo());
        s.setDescripcion(dto.getDescripcion());
        return ServicioMapper.toDto(servicioRepository.save(s));
    }

    @Transactional
    @Override
    public ServicioDTO update(Long id, ServicioCreateDTO dto) {
        Servicio s = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        // si cambia el código, validá unicidad
        if (!s.getCodigo().equals(dto.getCodigo()) && servicioRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un servicio con código " + dto.getCodigo());
        }
        s.setCodigo(dto.getCodigo());
        s.setDescripcion(dto.getDescripcion());
        return ServicioMapper.toDto(servicioRepository.save(s));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
            servicioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("No se puede borrar: hay servicios realizados asociados");
        }
    }
}
