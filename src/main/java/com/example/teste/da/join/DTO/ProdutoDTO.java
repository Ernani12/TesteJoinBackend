package com.example.teste.da.join.DTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Long categoriaId;

    public ProdutoDTO(String nome, String descricao, BigDecimal preco, Long categoriaId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaId = categoriaId;
    }

    // Getters e Setters
}
