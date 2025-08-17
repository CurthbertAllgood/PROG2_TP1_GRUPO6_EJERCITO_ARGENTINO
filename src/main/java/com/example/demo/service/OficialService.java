package com.example.demo.service;

import com.example.demo.dto.OficialDTO;
import com.example.demo.mapper.OficialMapper;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.CuartelRepository;
import com.example.demo.repository.CuerpoRepository;
import com.example.demo.repository.OficialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OficialService {
    private final OficialRepository oficialRepo;
    private final CuartelRepository cuartelRepo;
    private final CompaniaRepository companiaRepo;
    private final CuerpoRepository cuerpoRepo;
    private final OficialMapper mapper;

    public OficialService(OficialRepository o, CuartelRepository cu, CompaniaRepository co,
                          CuerpoRepository c, OficialMapper m) {
        this.oficialRepo = o; this.cuartelRepo = cu; this.companiaRepo = co; this.cuerpoRepo = c; this.mapper = m;
    }

    public List<OficialDTO> findAll() {
        return oficialRepo.findAll().stream().map(mapper::toDTO).toList();
    }

    public OficialDTO findById(Long id) {
        var e = oficialRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Oficial %d no encontrado".formatted(id)));
        return mapper.toDTO(e);
    }

    @Transactional
    public OficialDTO create(OficialDTO dto) {
        var e = mapper.toEntity(dto);
        e.setCuartel(cuartelRepo.getReferenceById(dto.getCuartelId()));
        e.setCompania(companiaRepo.getReferenceById(dto.getCompaniaId()));
        e.setCuerpo(cuerpoRepo.getReferenceById(dto.getCuerpoId()));
        return mapper.toDTO(oficialRepo.save(e));
    }

    @Transactional
    public OficialDTO update(Long id, OficialDTO dto) {
        var e = oficialRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Oficial %d no encontrado".formatted(id)));
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setLegajo(dto.getLegajo());
        e.setCuartel(cuartelRepo.getReferenceById(dto.getCuartelId()));
        e.setCompania(companiaRepo.getReferenceById(dto.getCompaniaId()));
        e.setCuerpo(cuerpoRepo.getReferenceById(dto.getCuerpoId()));
        return mapper.toDTO(oficialRepo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        if (!oficialRepo.existsById(id)) throw new EntityNotFoundException("Oficial %d no encontrado".formatted(id));
        oficialRepo.deleteById(id);
    }
}