package com.example.demo.controller;

import com.example.demo.dto.CompaniaCreateDTO;
import com.example.demo.dto.CompaniaDTO;
import com.example.demo.service.CompaniaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companias")
public class CompaniaController {

    private final CompaniaService companiaService;

    @GetMapping
    public ResponseEntity<List<CompaniaDTO>> getAll() {
        return ResponseEntity.ok(companiaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompaniaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(companiaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CompaniaDTO> create(@Valid @RequestBody CompaniaCreateDTO dto) {
        CompaniaDTO created = companiaService.create(dto);
        return ResponseEntity.created(URI.create("/api/companias/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompaniaDTO> update(@PathVariable Long id, @Valid @RequestBody CompaniaCreateDTO dto) {
        return ResponseEntity.ok(companiaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
