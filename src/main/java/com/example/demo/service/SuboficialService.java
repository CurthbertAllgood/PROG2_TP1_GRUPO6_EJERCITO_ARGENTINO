package com.example.demo.service;

import com.example.demo.dto.SuboficialDTO;
import com.example.demo.mapper.SuboficialMapper;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.CuartelRepository;
import com.example.demo.repository.CuerpoRepository;
import com.example.demo.repository.SuboficialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuboficialService {
    private final SuboficialRepository suboficialRepo;
    private final CuartelRepository cuartelRepo;
    private final CompaniaRepository companiaRepo;
    private final CuerpoRepository cuerpoRepo;
    private final SuboficialMapper mapper;

    public SuboficialService(SuboficialRepository s, CuartelRepository cu, CompaniaRepository co,
                             CuerpoRepository c, SuboficialMapper m) {
        this.suboficialRepo = s; this.cuartelRepo = cu; this.companiaRepo = co; this.cuerpoRepo = c; this.mapper = m;
    }

    public List<SuboficialDTO> findAll() {
        return suboficialRepo.findAll().stream().map(mapper::toDTO).toList();
    }

    public SuboficialDTO findById(Long id) {
        var e = suboficialRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Suboficial %d no encontrado".formatted(id)));
        return mapper.toDTO(e);
    }

    @Transactional
    public SuboficialDTO create(SuboficialDTO dto) {
        var e = mapper.toEntity(dto);
        e.setCuartel(cuartelRepo.getReferenceById(dto.getCuartelId()));
        e.setCompania(companiaRepo.getReferenceById(dto.getCompaniaId()));
        e.setCuerpo(cuerpoRepo.getReferenceById(dto.getCuerpoId()));
        return mapper.toDTO(suboficialRepo.save(e));
    }

    @Transactional
    public SuboficialDTO update(Long id, SuboficialDTO dto) {
        var e = suboficialRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Suboficial %d no encontrado".formatted(id)));
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setLegajo(dto.getLegajo());
        e.setCuartel(cuartelRepo.getReferenceById(dto.getCuartelId()));
        e.setCompania(companiaRepo.getReferenceById(dto.getCompaniaId()));
        e.setCuerpo(cuerpoRepo.getReferenceById(dto.getCuerpoId()));
        return mapper.toDTO(suboficialRepo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        if (!suboficialRepo.existsById(id)) throw new EntityNotFoundException("Suboficial %d no encontrado".formatted(id));
        suboficialRepo.deleteById(id);
    }
}