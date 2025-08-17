package com.example.demo.service;


import com.example.demo.service.impl.PlantillaCRUDService;
import com.example.demo.dto.SoldadoDTO;
import com.example.demo.entity.Soldado;
import com.example.demo.mapper.SoldadoMapper;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.CuartelRepository;
import com.example.demo.repository.CuerpoRepository;
import com.example.demo.repository.SoldadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SoldadoService extends PlantillaCRUDService<Soldado, SoldadoDTO, Long> {

    private final CuartelRepository cuartelRepo;
    private final CompaniaRepository companiaRepo;
    private final CuerpoRepository cuerpoRepo;

    public SoldadoService(
            SoldadoRepository repo,
            CuartelRepository cuartelRepo,
            CompaniaRepository companiaRepo,
            CuerpoRepository cuerpoRepo,
            SoldadoMapper mapper
    ) {
        super(repo, mapper);
        this.cuartelRepo = cuartelRepo;
        this.companiaRepo = companiaRepo;
        this.cuerpoRepo = cuerpoRepo;
    }

    @Override
    protected void beforeSave(SoldadoDTO dto, Soldado e, boolean creating) {
        e.setCuartel(cuartelRepo.findById(dto.getCuartelId())
                .orElseThrow(() -> new EntityNotFoundException("Cuartel %d no existe".formatted(dto.getCuartelId()))));
        e.setCompania(companiaRepo.findById(dto.getCompaniaId())
                .orElseThrow(() -> new EntityNotFoundException("Compañía %d no existe".formatted(dto.getCompaniaId()))));
        e.setCuerpo(cuerpoRepo.findById(dto.getCuerpoId())
                .orElseThrow(() -> new EntityNotFoundException("Cuerpo %d no existe".formatted(dto.getCuerpoId()))));
    }
}
