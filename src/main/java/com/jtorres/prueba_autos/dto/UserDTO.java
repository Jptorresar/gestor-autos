package com.jtorres.prueba_autos.dto;

import com.jtorres.prueba_autos.entity.User;
import lombok.Getter;

@Getter
public class UserDTO {
    private Long id;
    private String username;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}