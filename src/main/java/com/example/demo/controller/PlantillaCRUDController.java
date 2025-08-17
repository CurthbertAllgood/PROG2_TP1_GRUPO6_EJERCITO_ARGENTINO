package com.example.demo.controller;


import com.example.demo.service.CRUDService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;

/**
 * Controlador CRUD genérico.
 * - POST devuelve 201 Created con Location
 * - Maneja @Valid en create/update
 */
public abstract class PlantillaCRUDController<D, ID> {

    protected final CRUDService<D, ID> service;

    protected PlantillaCRUDController(CRUDService<D, ID> service) {
        this.service = service;
    }

    @GetMapping
    public List<D> list() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public D get(@PathVariable ID id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody @Valid D dto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        D created = service.create(dto);
        // Si tu DTO tiene getId(), la mayoría lo tiene:
        Object id = created.getClass().getMethod("getId").invoke(created);
        return ResponseEntity
                .created(URI.create("./" + id)) // Location relativa
                .body(created);
    }

    @PutMapping("{id}")
    public D update(@PathVariable ID id, @RequestBody @Valid D dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
