package com.example.demo.controller;

import com.example.demo.dto.SoldadoDTO;
import com.example.demo.service.SoldadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/soldados")
public class SoldadoController {

    private final SoldadoService service;
    public SoldadoController(SoldadoService service) { this.service = service; }

    @GetMapping
    public List<SoldadoDTO> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<SoldadoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SoldadoDTO> create(@RequestBody @Valid SoldadoDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoldadoDTO> update(@PathVariable Long id, @RequestBody @Valid SoldadoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
