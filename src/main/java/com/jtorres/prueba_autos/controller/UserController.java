package com.jtorres.prueba_autos.controller;

import com.jtorres.prueba_autos.dto.UserCreateDTO;
import com.jtorres.prueba_autos.dto.UserDTO;
import com.jtorres.prueba_autos.entity.User;
import com.jtorres.prueba_autos.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private User getAuthenticatedUser(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    //Listar todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllAutos() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    //Crear un auto para el usuario logueado
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userDto, HttpServletRequest request) {
        User user = userDto.toUser(userDto);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new UserDTO(savedUser));
    }

    //Listar un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getuserByPlaca(@PathVariable String id) {
        Optional<User> auto = userRepository.findById(Integer.valueOf(id));
        return auto.map(a -> ResponseEntity.ok(new UserDTO(a)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un usuario segun su id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuto(@PathVariable String id, @RequestBody UserCreateDTO userDto, HttpServletRequest request) {
        User user = getAuthenticatedUser(request);
        Optional<User> optionalUser = userRepository.findById(Integer.valueOf(id));
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getId().equals(optionalUser.get().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No estas modificando tu propio usuario");
        }

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return ResponseEntity.ok(new UserDTO(user));
    }
}
