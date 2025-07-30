package com.jtorres.prueba_autos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "auto")
@Getter
@Setter
public class Auto {

    @Id
    private String placa;

    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String color;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
