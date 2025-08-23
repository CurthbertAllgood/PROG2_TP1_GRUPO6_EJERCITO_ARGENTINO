package com.example.demo.service.impl;

import com.example.demo.dto.CuerpoCreateDTO;
import com.example.demo.dto.CuerpoDTO;
import com.example.demo.entity.Cuerpo;
import com.example.demo.mapper.CuerpoMapper;
import com.example.demo.repository.CuerpoRepository;
import com.example.demo.service.CuerpoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuerpoServiceImpl implements CuerpoService {

    private final CuerpoRepository cuerpoRepository;

    @Override
    public List<CuerpoDTO> getAll() {
        return cuerpoRepository.findAll().stream().map(CuerpoMapper::toDto).toList();
    }

    @Override
    public CuerpoDTO getById(Long id) {
        Cuerpo c = cuerpoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuerpo no encontrado"));
        return CuerpoMapper.toDto(c);
    }

    @Transactional
    @Override
    public CuerpoDTO create(CuerpoCreateDTO dto) {
        if (cuerpoRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un cuerpo con código " + dto.getCodigo());
        }
        Cuerpo c = new Cuerpo();
        c.setCodigo(dto.getCodigo());
        c.setDenominacion(dto.getDenominacion());
        return CuerpoMapper.toDto(cuerpoRepository.save(c));
    }

    @Transactional
    @Override
    public CuerpoDTO update(Long id, CuerpoCreateDTO dto) {
        Cuerpo c = cuerpoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuerpo no encontrado"));

        if (!c.getCodigo().equals(dto.getCodigo()) && cuerpoRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un cuerpo con código " + dto.getCodigo());
        }
        c.setCodigo(dto.getCodigo());
        c.setDenominacion(dto.getDenominacion());
        return CuerpoMapper.toDto(cuerpoRepository.save(c));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
            cuerpoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("No se puede borrar: hay militares asociados a este cuerpo");
        }
    }
}
