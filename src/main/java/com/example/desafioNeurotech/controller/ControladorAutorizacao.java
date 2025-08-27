package com.example.desafioNeurotech.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafioNeurotech.model.Usuario;
import com.example.desafioNeurotech.security.JwtUtil;
import com.example.desafioNeurotech.service.ServiceUsuario;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name="Autenticação")
@RequestMapping("/auth")
public class ControladorAutorizacao {

     private final ServiceUsuario serviceUsuario;

    public ControladorAutorizacao(ServiceUsuario serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        Usuario usuario = serviceUsuario.registrarUsuario(request.get("username"), request.get("password"));
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
       Optional<Usuario> usuario = serviceUsuario.buscarPorUsername(request.get("username"));
       if (usuario.isPresent() && usuario.get().getPassword().equals(request.get("password"))) {
         String token = JwtUtil.generateToken(usuario.get().getUsername());
         return ResponseEntity.ok(Map.of("token", token));
       }
       return ResponseEntity.status(401).body("Credenciais inválidas");
    }
    
}
