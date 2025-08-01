package com.jtorres.prueba_autos.controller;

import com.jtorres.prueba_autos.config.JwtUtil;
import com.jtorres.prueba_autos.dto.AutoDTO;
import com.jtorres.prueba_autos.entity.Auto;
import com.jtorres.prueba_autos.entity.User;
import com.jtorres.prueba_autos.repository.AutoRepository;
import com.jtorres.prueba_autos.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private User getAuthenticatedUser(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    //Listar todos los autos
    @GetMapping("/all")
    public ResponseEntity<List<AutoDTO>> getAllAutos() {
        List<Auto> autos = autoRepository.findAll();
        List<AutoDTO> dtos = autos.stream()
                .map(AutoDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    //Listar un auto por placa
    @GetMapping("/{placa}")
    public ResponseEntity<AutoDTO> getAutoByPlaca(@PathVariable String placa) {
        Optional<Auto> auto = autoRepository.findById(placa);
        return auto.map(a -> ResponseEntity.ok(new AutoDTO(a)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Obtener los autos del usuario logueado
    @GetMapping("/")
    public ResponseEntity<List<AutoDTO>> getUserAutos(HttpServletRequest request) {
        User user = getAuthenticatedUser(request);
        List<Auto> autos = autoRepository.findByUser(user);
        List<AutoDTO> dtos = autos.stream().map(AutoDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

    //Crear un auto para el usuario logueado
    @PostMapping("/")
    public ResponseEntity<AutoDTO> createAuto(@RequestBody AutoDTO autoDto, HttpServletRequest request) {
        User user = getAuthenticatedUser(request);
        Auto auto = autoDto.toAuto();
        auto.setUser(user);
        Auto savedAuto = autoRepository.save(auto);
        return ResponseEntity.ok(new AutoDTO(savedAuto));
    }

    // Actualizar un auto segun su placa
    @PutMapping("/{placa}")
    public ResponseEntity<?> updateAuto(@PathVariable String placa, @RequestBody AutoDTO autoDto, HttpServletRequest request) {
        User user = getAuthenticatedUser(request);
        Optional<Auto> optionalAuto = autoRepository.findById(placa);
        if (optionalAuto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Auto auto = optionalAuto.get();
        if (!auto.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes modificar este auto");
        }

        auto.setPlaca(autoDto.getPlaca());
        auto.setMarca(autoDto.getMarca());
        auto.setModelo(autoDto.getModelo());
        auto.setColor(autoDto.getColor());
        auto.setYear(autoDto.getYear());
        autoRepository.save(auto);

        return ResponseEntity.ok(new AutoDTO(auto));
    }

    // Borrar un auto
    @DeleteMapping("/{placa}")
    public ResponseEntity<?> deleteAuto(@PathVariable String placa, HttpServletRequest request) {
        User user = getAuthenticatedUser(request);
        Optional<Auto> optionalAuto = autoRepository.findById(placa);
        if (optionalAuto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Auto auto = optionalAuto.get();
        if (!auto.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes eliminar este auto");
        }
        autoRepository.delete(auto);
        String mensaje = "Auto con placa " + placa + " eliminado correctamente";
        Map<String, String> response = new HashMap<>();
        response.put("message", mensaje);
        return ResponseEntity.ok(response);
    }
}
