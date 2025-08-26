// src/main/java/com/example/demo/controller/AsignacionServicioController.java
package com.example.demo.controller;

import com.example.demo.dto.AsignacionCreateDTO;
import com.example.demo.dto.AsignacionDTO;
import com.example.demo.service.AsignacionServicioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asignaciones")
public class AsignacionServicioController {

    private final AsignacionServicioService service;

    @PostMapping
    public ResponseEntity<AsignacionDTO> crear(@RequestBody AsignacionCreateDTO dto,
                                               Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(service.crear(dto, username));
    }

    @GetMapping
    public ResponseEntity<List<AsignacionDTO>> listar(@RequestParam(required = false) Long militarId,
                                                      @RequestParam(required = false) String estado,
                                                      Authentication auth,
                                                      HttpServletRequest req) {
        String role = auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        Long personaId = (Long) req.getAttribute("personaId"); // ver punto 2
        return ResponseEntity.ok(service.listar(militarId, estado, role, personaId));
    }

    @PatchMapping("/{id}/realizar")
    public ResponseEntity<AsignacionDTO> realizar(@PathVariable Long id,
                                                  @RequestParam(required = false) LocalDate fecha,
                                                  Authentication auth,
                                                  HttpServletRequest req) {
        String role = auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        Long personaId = (Long) req.getAttribute("personaId"); // ver punto 2
        return ResponseEntity.ok(service.marcarRealizado(id, fecha, role, personaId));
    }
}
