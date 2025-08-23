package com.example.demo.controller;

import com.example.demo.dto.CuerpoCreateDTO;
import com.example.demo.dto.CuerpoDTO;
import com.example.demo.service.CuerpoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuerpos")
public class CuerpoController {

    private final CuerpoService cuerpoService;

    @GetMapping
    public ResponseEntity<List<CuerpoDTO>> getAll() {
        return ResponseEntity.ok(cuerpoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuerpoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cuerpoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CuerpoDTO> create(@Valid @RequestBody CuerpoCreateDTO dto) {
        CuerpoDTO created = cuerpoService.create(dto);
        return ResponseEntity.created(URI.create("/api/cuerpos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuerpoDTO> update(@PathVariable Long id, @Valid @RequestBody CuerpoCreateDTO dto) {
        return ResponseEntity.ok(cuerpoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuerpoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
