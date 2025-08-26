package com.example.demo.controller;

import com.example.demo.dto.ServicioCreateDTO;
import com.example.demo.dto.ServicioDTO;
import com.example.demo.service.ServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> getAll() {
        return ResponseEntity.ok(servicioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(servicioService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> create(@Valid @RequestBody ServicioCreateDTO dto) {
        ServicioDTO created = servicioService.create(dto);
        return ResponseEntity.created(URI.create("/api/servicios/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> update(@PathVariable Long id, @Valid @RequestBody ServicioCreateDTO dto) {
        return ResponseEntity.ok(servicioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
