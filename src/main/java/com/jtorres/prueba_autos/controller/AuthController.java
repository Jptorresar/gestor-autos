package com.jtorres.prueba_autos.controller;

import com.jtorres.prueba_autos.config.JwtUtil;
import com.jtorres.prueba_autos.dto.UserDTO;
import com.jtorres.prueba_autos.entity.User;
import com.jtorres.prueba_autos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> loginData) {
        System.out.println("Login data recibida: " + loginData);
        String username = (String) loginData.get("username");
        String password = (String) loginData.get("password");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
            System.out.println("Autenticación exitosa");
            User user = userRepository.findByUsername(username).get();
            UserDTO userDTO = new UserDTO(user);
            final String token = jwtUtil.generateToken(username);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", userDTO);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
