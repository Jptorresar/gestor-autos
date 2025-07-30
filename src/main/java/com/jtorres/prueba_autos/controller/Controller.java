package com.jtorres.prueba_autos.controller;

import com.jtorres.prueba_autos.dto.UserDTO;
import com.jtorres.prueba_autos.entity.Auto;
import com.jtorres.prueba_autos.entity.User;
import com.jtorres.prueba_autos.repository.AutoRepository;
import com.jtorres.prueba_autos.repository.UserRepository;
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

    @Autowired
    private AutoRepository autoRepository;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/autos")
    public ResponseEntity<List<Auto>> getAllAutos() {
        List<Auto> autos = autoRepository.findAll();
        return ResponseEntity.ok(autos);
    }

    @GetMapping("/autos/{placa}")
    public ResponseEntity<Auto> getAutoByPlaca(@PathVariable String placa) {
        Optional<Auto> auto = autoRepository.findById(placa);
        return auto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}/autos")
    public ResponseEntity<List<Auto>> getAutosByUserId(@PathVariable Integer userId) {
        List<Auto> autos = autoRepository.findByUserId(userId);
        return ResponseEntity.ok(autos);
    }
}
