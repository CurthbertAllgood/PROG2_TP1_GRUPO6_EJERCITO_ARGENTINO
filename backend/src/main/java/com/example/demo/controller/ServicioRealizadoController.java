package com.example.demo.controller;

import com.example.demo.dto.ServicioRealizadoCreateDTO;
import com.example.demo.dto.ServicioRealizadoDTO;
import com.example.demo.service.ServicioRealizadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/servicios-realizados")
public class ServicioRealizadoController {

    private final ServicioRealizadoService service;

    @PostMapping
    public ResponseEntity<ServicioRealizadoDTO> create(@Valid @RequestBody ServicioRealizadoCreateDTO dto) {
        ServicioRealizadoDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/servicios-realizados/" + created.getId())).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServicioRealizadoDTO>> find(
            @RequestParam(required = false) Long militarId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

        if (militarId != null) {
            return ResponseEntity.ok(service.findByMilitar(militarId));
        }
        if (desde != null && hasta != null) {
            return ResponseEntity.ok(service.findByFechaBetween(desde, hasta));
        }

        return ResponseEntity.ok(List.of());
    }
}
