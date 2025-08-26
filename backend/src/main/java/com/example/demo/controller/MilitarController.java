package com.example.demo.controller;

import com.example.demo.dto.MilitarCreateDTO;
import com.example.demo.dto.MilitarDTO;
import com.example.demo.service.MilitarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/militares")
public class MilitarController {

    private final MilitarService militarService;

    @GetMapping
    public ResponseEntity<List<MilitarDTO>> getAll() {
        return ResponseEntity.ok(militarService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilitarDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(militarService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MilitarDTO> create(@Valid @RequestBody MilitarCreateDTO dto) {
        MilitarDTO created = militarService.create(dto);
        return ResponseEntity.created(URI.create("/api/militares/" + created.getId())).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        militarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
