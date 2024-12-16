package com.example.teste.da.join.DTO;

import com.example.teste.da.join.model.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {
    private Long id;
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e setters
}