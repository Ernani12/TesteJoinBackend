package com.example.teste.da.join.model;

import com.example.teste.da.join.repository.CategoriaRepository;
import com.example.teste.da.join.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criação das categorias
        Categoria categoria1 = new Categoria("Eletrônicos", "Produtos eletrônicos, como TVs, celulares, etc.", "2024-12-14");
        Categoria categoria2 = new Categoria("Eletrodomésticos", "Produtos como geladeiras, fogões, micro-ondas.", "2024-12-14");
        Categoria categoria3 = new Categoria("Móveis", "Móveis para sala, quarto, cozinha.", "2024-12-14");
        Categoria categoria4 = new Categoria("Vestuário", "Roupas e acessórios.", "2024-12-14");
        Categoria categoria5 = new Categoria("Alimentos", "Produtos alimentícios como snacks, bebidas, etc.", "2024-12-14");

        // Salvar as categorias no banco de dados
        categoriaRepository.saveAll(List.of(categoria1, categoria2, categoria3, categoria4, categoria5));

        // Criar e associar produtos às categorias
        Produto produto1 = new Produto("Smartphone Samsung Galaxy", "Smartphone de última geração da Samsung.", new BigDecimal("2500.99"), categoria1 );
        Produto produto2 = new Produto("Geladeira Brastemp", "Geladeira de 400L da Brastemp.", new BigDecimal("2899.00"), categoria2 );
        Produto produto3 = new Produto("Sofá de 3 lugares", "Sofá de 3 lugares em tecido.", new BigDecimal("1200.00"), categoria3 );
        Produto produto4 = new Produto("Camiseta Polo", "Camiseta Polo masculina de algodão.", new BigDecimal("99.90"), categoria4 );
        Produto produto5 = new Produto("Cerveja Skol Lata", "Cerveja Skol 350ml.", new BigDecimal("2.50"),  categoria5 );
       
        produtoRepository.save(produto1);      // Salva o produto com a categoria associada
        produtoRepository.save(produto2);      // Salva o produto com a categoria associada
        produtoRepository.save(produto3);      // Salva o produto com a categoria associada
        produtoRepository.save(produto4);      // Salva o produto com a categoria associada
        produtoRepository.save(produto5);      // Salva o produto com a categoria associada

        
       


        

    }
}
