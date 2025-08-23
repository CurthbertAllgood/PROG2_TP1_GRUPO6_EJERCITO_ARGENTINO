package com.example.demo.service.impl;

import com.example.demo.dto.CompaniaCreateDTO;
import com.example.demo.dto.CompaniaDTO;
import com.example.demo.entity.Compania;
import com.example.demo.entity.Cuartel;
import com.example.demo.mapper.CompaniaMapper;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.CuartelRepository;
import com.example.demo.service.CompaniaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompaniaServiceImpl implements CompaniaService {

    private final CompaniaRepository companiaRepository;
    private final CuartelRepository cuartelRepository;

    @Override
    public List<CompaniaDTO> getAll() {
        return companiaRepository.findAll().stream().map(CompaniaMapper::toDto).toList();
    }

    @Override
    public CompaniaDTO getById(Long id) {
        Compania c = companiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compañía no encontrada"));
        return CompaniaMapper.toDto(c);
    }

    @Transactional
    @Override
    public CompaniaDTO create(CompaniaCreateDTO dto) {
        Compania c = new Compania();
        c.setNumero(dto.getNumero());
        c.setActividadPrincipal(dto.getActividadPrincipal());
        if (dto.getCuartelIds() != null && !dto.getCuartelIds().isEmpty()) {
            List<Cuartel> cuarteles = cuartelRepository.findAllById(dto.getCuartelIds());
            if (cuarteles.size() != dto.getCuartelIds().size()) {
                throw new EntityNotFoundException("Alguno(s) de los cuarteles no existe(n)");
            }
            c.setCuarteles(new HashSet<>(cuarteles));
        }
        return CompaniaMapper.toDto(companiaRepository.save(c));
    }

    @Transactional
    @Override
    public CompaniaDTO update(Long id, CompaniaCreateDTO dto) {
        Compania c = companiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compañía no encontrada"));
        c.setNumero(dto.getNumero());
        c.setActividadPrincipal(dto.getActividadPrincipal());
        if (dto.getCuartelIds() != null) {
            List<Cuartel> cuarteles = cuartelRepository.findAllById(dto.getCuartelIds());
            if (cuarteles.size() != dto.getCuartelIds().size()) {
                throw new EntityNotFoundException("Alguno(s) de los cuarteles no existe(n)");
            }
            c.setCuarteles(new HashSet<>(cuarteles));
        }
        return CompaniaMapper.toDto(companiaRepository.save(c));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
            companiaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("No se puede borrar: hay militares asociados o relaciones con cuarteles");
        }
    }
}
