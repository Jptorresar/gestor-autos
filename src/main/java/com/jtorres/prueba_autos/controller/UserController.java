package com.jtorres.prueba_autos.controller;

import com.jtorres.prueba_autos.dto.UserCreateDTO;
import com.jtorres.prueba_autos.dto.UserDTO;
import com.jtorres.prueba_autos.entity.User;
import com.jtorres.prueba_autos.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private User getAuthenticatedUser() {
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

    //Crear un usuario
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userDto) {
        User user = userDto.toUser(userDto);
        //Hashear la contraseña antes de guardar
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        System.out.println("Usuario creado");
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
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuto(@PathVariable String id, @RequestBody UserCreateDTO userDto) {
        User user = getAuthenticatedUser();
        Optional<User> optionalUser = userRepository.findById(Integer.valueOf(id));
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getId().equals(optionalUser.get().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No estas modificando tu propio usuario");
        }

        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return ResponseEntity.ok(new UserDTO(user));
    }

    //Borrar un usuario
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        User user = getAuthenticatedUser();
        Optional<User> optionalUser = userRepository.findById(Integer.valueOf(id));
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!user.getId().equals(optionalUser.get().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No estas eliminando tu propio usuario");
        }
        userRepository.delete(user);
        String mensaje = "Usuario con el id:  " + id + " eliminado correctamente";
        Map<String, String> response = new HashMap<>();
        response.put("message", mensaje);
        return ResponseEntity.ok(response);
    }
}
