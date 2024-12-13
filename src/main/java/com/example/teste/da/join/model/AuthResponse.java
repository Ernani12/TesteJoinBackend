package com.example.teste.da.join.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String message;
    private String token; // Opcional, se usar JWT

    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getters e setters
}
