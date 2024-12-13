package com.example.teste.da.join.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.example.teste.da.join.DTO.*;
import com.example.teste.da.join.model.AuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            // Autenticação bem-sucedida
            AuthResponse response = new AuthResponse("Login successful!", "some-jwt-token-if-needed");
            return ResponseEntity.ok(response); // Retorna o objeto dentro de um ResponseEntity com status 200
        } else {
            // Caso improvável de falha (em teoria, a exceção deve capturar isso)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(new AuthResponse("Autenticação falhou.", null));
        }
    } catch (AuthenticationException e) {
        // Credenciais inválidas
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new AuthResponse("Credenciais inválidas", null));
    }
}

}


