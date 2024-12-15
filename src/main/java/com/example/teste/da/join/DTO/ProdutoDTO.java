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

    // Getters e Setters
}
