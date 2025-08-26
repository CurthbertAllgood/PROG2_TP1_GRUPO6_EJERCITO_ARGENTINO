package com.example.demo.service.impl;

import com.example.demo.dto.CuartelCreateDTO;
import com.example.demo.dto.CuartelDTO;
import com.example.demo.entity.Compania;
import com.example.demo.entity.Cuartel;
import com.example.demo.mapper.CuartelMapper;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.CuartelRepository;
import com.example.demo.service.CuartelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuartelServiceImpl implements CuartelService {

    private final CuartelRepository cuartelRepository;
    private final CompaniaRepository companiaRepository;

    @Override
    public List<CuartelDTO> getAll() {
        return cuartelRepository.findAll().stream().map(CuartelMapper::toDto).toList();
    }

    @Override
    public CuartelDTO getById(Long id) {
        Cuartel c = cuartelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuartel no encontrado"));
        return CuartelMapper.toDto(c);
    }

    @Transactional
    @Override
    public CuartelDTO create(CuartelCreateDTO dto) {
        if (cuartelRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un cuartel con código " + dto.getCodigo());
        }
        Cuartel c = new Cuartel();
        c.setCodigo(dto.getCodigo());
        c.setNombre(dto.getNombre());
        c.setUbicacion(dto.getUbicacion());

        if (dto.getCompaniaIds() != null && !dto.getCompaniaIds().isEmpty()) {
            var companias = companiaRepository.findAllById(dto.getCompaniaIds());
            if (companias.size() != dto.getCompaniaIds().size()) {
                throw new EntityNotFoundException("Alguna(s) compañía(s) no existe(n)");
            }
            c.setCompanias(new HashSet<>(companias));
        }
        return CuartelMapper.toDto(cuartelRepository.save(c));
    }

    @Transactional
    @Override
    public CuartelDTO update(Long id, CuartelCreateDTO dto) {
        Cuartel c = cuartelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuartel no encontrado"));

        if (!c.getCodigo().equals(dto.getCodigo()) && cuartelRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un cuartel con código " + dto.getCodigo());
        }

        c.setCodigo(dto.getCodigo());
        c.setNombre(dto.getNombre());
        c.setUbicacion(dto.getUbicacion());

        if (dto.getCompaniaIds() != null) {
            var companias = companiaRepository.findAllById(dto.getCompaniaIds());
            if (companias.size() != dto.getCompaniaIds().size()) {
                throw new EntityNotFoundException("Alguna(s) compañía(s) no existe(n)");
            }
            c.setCompanias(new HashSet<>(companias));
        }
        return CuartelMapper.toDto(cuartelRepository.save(c));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
            cuartelRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("No se puede borrar: hay militares o compañías asociados");
        }
    }
}
