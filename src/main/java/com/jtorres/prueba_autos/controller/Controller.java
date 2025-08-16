package com.jtorres.prueba_autos.controller;

import com.jtorres.prueba_autos.dto.UserDTO;
import com.jtorres.prueba_autos.entity.User;
import com.jtorres.prueba_autos.repository.AutoRepository;
import com.jtorres.prueba_autos.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Api backen auto gestionador - Sistema funcionando correctamente!");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostConstruct
    public void verificarUsuarios() {
        System.out.println("Usuarios en base de datos:");
        userRepository.findAll().forEach(user -> System.out.println(user.getUsername()));
    }
}
