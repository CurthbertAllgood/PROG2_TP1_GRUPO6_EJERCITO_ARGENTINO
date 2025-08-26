package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Civil extends Persona {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}