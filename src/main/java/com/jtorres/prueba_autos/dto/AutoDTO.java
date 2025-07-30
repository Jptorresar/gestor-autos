package com.jtorres.prueba_autos.dto;

import com.jtorres.prueba_autos.entity.Auto;
import lombok.Getter;

@Getter
public class AutoDTO {
    private String placa;
    private String modelo;
    private String marca;
    private int year;
    private String color;

    public AutoDTO(Auto auto) {
        this.placa = auto.getPlaca();
        this.modelo = auto.getModelo();
        this.marca = auto.getMarca();
        this.year = auto.getYear();
        this.color = auto.getColor();
    }
}
