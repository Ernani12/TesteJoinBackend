package com.example.teste.da.join.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.example.teste.da.join.DTO.AuthRequest;
import com.example.teste.da.join.model.AuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Endpoint de login que recebe as credenciais e tenta autenticar o usuário
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {

            Authentication authentication = authenticateUser(authRequest);

            if (authentication.isAuthenticated()) {
                return buildSuccessResponse(); // Retorna resposta de sucesso se a autenticação for bem-sucedida
            } else {
                return buildUnauthorizedResponse("Autenticação falhou."); // Retorna resposta de erro caso a autenticação falhe
            }
        } catch (AuthenticationException e) {
            return buildUnauthorizedResponse("Credenciais inválidas"); // Retorna resposta de erro com a mensagem de falha
        }
    }

    // Método para autenticar o usuário com base nas credenciais fornecidas
    private Authentication authenticateUser(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        
        return authenticationManager.authenticate(authenticationToken);
    }

    // Método para construir a resposta de sucesso quando a autenticação for bem-sucedida
    private ResponseEntity<AuthResponse> buildSuccessResponse() {

        AuthResponse response = new AuthResponse("Login successful!", "some-jwt-token-if-needed");
        
        return ResponseEntity.ok(response);
    }

    // Método para construir a resposta de erro quando a autenticação falhar
    private ResponseEntity<AuthResponse> buildUnauthorizedResponse(String message) {
        
        AuthResponse response = new AuthResponse(message, null);
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
