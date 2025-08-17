package com.example.demo.service;


import java.util.List;

public interface CRUDService<D, ID> {
    List<D> findAll();
    D findById(ID id);
    D create(D dto);
    D update(ID id, D dto);
    void delete(ID id);
}
