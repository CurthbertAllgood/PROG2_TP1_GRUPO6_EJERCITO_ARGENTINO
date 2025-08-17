package com.example.demo.service.impl;


import com.example.demo.mapper.PlantillaMapper;
import com.example.demo.service.CRUDService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class PlantillaCRUDService<E, D, ID> implements CRUDService<D, ID> {

    protected final JpaRepository<E, ID> repo;
    protected final PlantillaMapper<E, D> mapper;

    protected PlantillaCRUDService(JpaRepository<E, ID> repo, PlantillaMapper<E, D> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<D> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public D findById(ID id) {
        E e = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe id=" + id));
        return mapper.toDto(e);
    }

    public D create(D dto) {
        E e = mapper.toEntity(dto);
        beforeSave(dto, e, true);
        return mapper.toDto(repo.save(e));
    }

    public D update(ID id, D dto) {
        E current = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe id=" + id));
        mapper.updateEntityFromDto(dto, current);
        beforeSave(dto, current, false);
        return mapper.toDto(repo.save(current));
    }

    public void delete(ID id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("No existe id=" + id);
        repo.deleteById(id);
    }

    /** Hook para resolver asociaciones/validaciones personalizadas */
    protected void beforeSave(D dto, E entity, boolean creating) {}
}
