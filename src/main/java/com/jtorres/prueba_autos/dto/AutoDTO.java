package com.jtorres.prueba_autos.dto;

import com.jtorres.prueba_autos.entity.Auto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

    public Auto toAuto(AutoDTO autoDTO) {
        Auto auto = new Auto();
        auto.setPlaca(autoDTO.getPlaca());
        auto.setModelo(autoDTO.getModelo());
        auto.setMarca(autoDTO.getMarca());
        auto.setYear(autoDTO.getYear());
        auto.setColor(autoDTO.getColor());
        return auto;
    }
}
