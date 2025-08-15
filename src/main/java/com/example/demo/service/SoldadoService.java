package com.example.demo.service;

import com.example.demo.dto.SoldadoDTO;
import com.example.demo.entity.Soldado;
import com.example.demo.mapper.SoldadoMapper;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.CuartelRepository;
import com.example.demo.repository.CuerpoRepository;
import com.example.demo.repository.SoldadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SoldadoService {
    private final SoldadoRepository soldadoRepo;
    private final CuartelRepository cuartelRepo;
    private final CompaniaRepository companiaRepo;
    private final CuerpoRepository cuerpoRepo;
    private final SoldadoMapper mapper;

    public SoldadoService(SoldadoRepository s, CuartelRepository cu, CompaniaRepository co,
                          CuerpoRepository c, SoldadoMapper m) {
        this.soldadoRepo = s; this.cuartelRepo = cu; this.companiaRepo = co; this.cuerpoRepo = c; this.mapper = m;
    }

    public List<SoldadoDTO> findAll() {
        return soldadoRepo.findAll().stream().map(mapper::toDTO).toList();
    }

    public SoldadoDTO findById(Long id) {
        var e = soldadoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Soldado %d no encontrado".formatted(id)));
        return mapper.toDTO(e);
    }

    @Transactional
    public SoldadoDTO create(SoldadoDTO dto) {
        var e = mapper.toEntity(dto);
        e.setCuartel(cuartelRepo.getReferenceById(dto.getCuartelId()));
        e.setCompania(companiaRepo.getReferenceById(dto.getCompaniaId()));
        e.setCuerpo(cuerpoRepo.getReferenceById(dto.getCuerpoId()));
        return mapper.toDTO(soldadoRepo.save(e));
    }

    @Transactional
    public SoldadoDTO update(Long id, SoldadoDTO dto) {
        var e = soldadoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Soldado %d no encontrado".formatted(id)));
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setLegajo(dto.getLegajo());
        e.setCuartel(cuartelRepo.getReferenceById(dto.getCuartelId()));
        e.setCompania(companiaRepo.getReferenceById(dto.getCompaniaId()));
        e.setCuerpo(cuerpoRepo.getReferenceById(dto.getCuerpoId()));
        return mapper.toDTO(soldadoRepo.save(e));
    }

    @Transactional
    public void delete(Long id) {
        if (!soldadoRepo.existsById(id)) throw new EntityNotFoundException("Soldado %d no encontrado".formatted(id));
        soldadoRepo.deleteById(id);
    }
}
