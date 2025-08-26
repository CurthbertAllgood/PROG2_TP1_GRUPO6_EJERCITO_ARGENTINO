package com.example.demo.repository;


import com.example.demo.entity.Civil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CivilRepository extends JpaRepository<Civil, Long> {}

