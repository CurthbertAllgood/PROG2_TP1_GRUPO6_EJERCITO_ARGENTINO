package com.example.demo.controller;

import com.example.demo.dto.CuartelCreateDTO;
import com.example.demo.dto.CuartelDTO;
import com.example.demo.service.CuartelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuarteles")
public class CuartelController {

    private final CuartelService cuartelService;

    @GetMapping
    public ResponseEntity<List<CuartelDTO>> getAll() {
        return ResponseEntity.ok(cuartelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuartelDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cuartelService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CuartelDTO> create(@Valid @RequestBody CuartelCreateDTO dto) {
        CuartelDTO created = cuartelService.create(dto);
        return ResponseEntity.created(URI.create("/api/cuarteles/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuartelDTO> update(@PathVariable Long id, @Valid @RequestBody CuartelCreateDTO dto) {
        return ResponseEntity.ok(cuartelService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuartelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
