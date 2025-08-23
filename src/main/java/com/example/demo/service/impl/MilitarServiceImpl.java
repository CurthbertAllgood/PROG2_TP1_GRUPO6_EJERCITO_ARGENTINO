package com.example.demo.service.impl;

import com.example.demo.dto.MilitarDTO;
import com.example.demo.entity.Militar;
import com.example.demo.mapper.MilitarMapper;
import com.example.demo.repository.MilitarRepository;
import com.example.demo.service.MilitarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilitarServiceImpl implements MilitarService {

    private final MilitarRepository militarRepository;

    @Override
    public List<MilitarDTO> getAll() {
        return militarRepository.findAll().stream()
                .map(MilitarMapper::toDto)
                .toList();
    }

    @Override
    public MilitarDTO getById(Long id) {
        Militar militar = militarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Militar no encontrado"));
        return MilitarMapper.toDto(militar);
    }

    @Override
    public MilitarDTO create(MilitarDTO dto) {
        // Acá deberías validar y construir el objeto Militar desde el DTO
        // Esto se deja como placeholder por ahora
        throw new UnsupportedOperationException("Crear militar aún no implementado");
    }

    @Override
    public void delete(Long id) {
        if (!militarRepository.existsById(id)) {
            throw new EntityNotFoundException("Militar no encontrado");
        }
        militarRepository.deleteById(id);
    }
}
