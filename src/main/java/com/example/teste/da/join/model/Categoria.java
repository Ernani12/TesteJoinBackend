package com.example.teste.da.join.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import lombok.Setter;

@Entity
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private String dataCriacao;


    public Categoria(){}

    public Categoria(String nome, String descricao, String dataCriacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }




    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonManagedReference // Serializa normalmente os produtos
    private List<Produto> produtos= new ArrayList<>();;
}
