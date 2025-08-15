package com.example.demo.repository;

import com.example.demo.entity.Soldado;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SoldadoRepository extends JpaRepository<Soldado, Long> {}
