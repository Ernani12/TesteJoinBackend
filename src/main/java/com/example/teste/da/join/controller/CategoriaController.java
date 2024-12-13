package com.example.teste.da.join.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    
    @GetMapping("/")
    public String  home(){
        return "Hello Join Us";
    }
}
