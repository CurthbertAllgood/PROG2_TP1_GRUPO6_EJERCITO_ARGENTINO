package com.example.demo.repository;

import com.example.demo.entity.Cuerpo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CuerpoRepository extends JpaRepository<Cuerpo, Long> {}
