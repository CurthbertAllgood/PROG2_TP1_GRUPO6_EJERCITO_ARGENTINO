package com.example.demo.service.impl;

import com.example.demo.dto.MilitarCreateDTO;
import com.example.demo.dto.MilitarDTO;
import com.example.demo.entity.*;
import com.example.demo.mapper.MilitarMapper;
import com.example.demo.repository.*;
import com.example.demo.service.MilitarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilitarServiceImpl implements MilitarService {

    private final MilitarRepository militarRepository;
    private final PersonaRepository personaRepository;
    private final CuerpoRepository cuerpoRepository;
    private final CompaniaRepository companiaRepository;
    private final CuartelRepository cuartelRepository;

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

    @Transactional
    @Override
    public MilitarDTO create(MilitarCreateDTO dto) {
        // Validaciones básicas
        if (personaRepository.existsByDni(dto.getDni())) {
            throw new IllegalArgumentException("Ya existe una persona con DNI " + dto.getDni());
        }
        if (militarRepository.findByCodigoMilitar(dto.getCodigoMilitar()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un militar con código " + dto.getCodigoMilitar());
        }

        // Cargar relaciones requeridas
        Cuerpo cuerpo = cuerpoRepository.findById(dto.getCuerpoId())
                .orElseThrow(() -> new EntityNotFoundException("Cuerpo no encontrado"));
        Compania compania = companiaRepository.findById(dto.getCompaniaId())
                .orElseThrow(() -> new EntityNotFoundException("Compañía no encontrada"));
        Cuartel cuartel = cuartelRepository.findById(dto.getCuartelId())
                .orElseThrow(() -> new EntityNotFoundException("Cuartel no encontrado"));

        // Instanciar la subclase correcta
        Militar militar = switch (dto.getTipo().toUpperCase()) {
            case "SOLDADO" -> new Soldado();
            case "SUBOFICIAL" -> new Suboficial();
            case "OFICIAL" -> new Oficial();
            default -> throw new IllegalArgumentException("Tipo inválido: " + dto.getTipo());
        };

        // Poblar datos comunes
        militar.setNombre(dto.getNombre());
        militar.setApellido(dto.getApellido());
        militar.setDni(dto.getDni());
        militar.setCodigoMilitar(dto.getCodigoMilitar());
        militar.setCuerpo(cuerpo);
        militar.setCompania(compania);
        militar.setCuartel(cuartel);

        Militar saved = militarRepository.save(militar);
        return MilitarMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!militarRepository.existsById(id)) {
            throw new EntityNotFoundException("Militar no encontrado");
        }
        militarRepository.deleteById(id);
    }
}
