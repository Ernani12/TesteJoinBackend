package com.example.teste.da.join;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.model.Produto;

public class CategoriaTest {

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        // Preparação de uma instância para os testes
        categoria = new Categoria("Categoria Teste", "Descrição da categoria", "2024-12-16");
    }

    // Testes Unitários (5)

    @Test
    void testCategoriaConstructor() {
        assertEquals("Categoria Teste", categoria.getNome());
        assertEquals("Descrição da categoria", categoria.getDescricao());
        assertEquals("2024-12-16", categoria.getDataCriacao());
    }

    @Test
    void testAddProduto() {
        Produto produto = new Produto("Produto Teste", "Descrição do produto", categoria);
        categoria.getProdutos().add(produto);

        assertEquals(1, categoria.getProdutos().size());
        assertTrue(categoria.getProdutos().contains(produto));
    }

    @Test
    void testRemoveProduto() {
        Produto produto = new Produto("Produto Teste", "Descrição do produto", categoria);
        categoria.getProdutos().add(produto);
        categoria.getProdutos().remove(produto);

        assertEquals(0, categoria.getProdutos().size());
        assertFalse(categoria.getProdutos().contains(produto));
    }

    @Test
    void testCategoriaNome() {
        // Verifica se o nome da categoria é correto
        assertEquals("Categoria Teste", categoria.getNome());
    }

    @Test
    void testCategoriaDescricao() {
        // Verifica se a descrição da categoria é correta
        assertEquals("Descrição da categoria", categoria.getDescricao());
    }

    // Testes BDD (5)

    @Test
    void testCategoriaConstructorBDD() {
        // Given: Uma categoria com nome, descrição e data de criação
        // When: A categoria é criada com esses parâmetros
        // Then: A categoria deve ter os valores corretos
        assertEquals("Categoria Teste", categoria.getNome());
        assertEquals("Descrição da categoria", categoria.getDescricao());
        assertEquals("2024-12-16", categoria.getDataCriacao());
    }

    @Test
    void testAddProdutoBDD() {
        // Given: Uma categoria e um produto
        Produto produto = new Produto("Produto Teste", "Descrição do produto", categoria);

        // When: O produto é adicionado à categoria
        categoria.getProdutos().add(produto);

        // Then: O produto deve estar presente na lista de produtos da categoria
        assertEquals(1, categoria.getProdutos().size());
        assertTrue(categoria.getProdutos().contains(produto));
    }

    @Test
    void testRemoveProdutoBDD() {
        // Given: Uma categoria com um produto
        Produto produto = new Produto("Produto Teste", "Descrição do produto", categoria);
        categoria.getProdutos().add(produto);

        // When: O produto é removido da categoria
        categoria.getProdutos().remove(produto);

        // Then: A lista de produtos da categoria deve estar vazia e não deve conter o produto
        assertEquals(0, categoria.getProdutos().size());
        assertFalse(categoria.getProdutos().contains(produto));
    }

    @Test
    void testCategoriaNameChangeBDD() {
        // Given: Uma categoria com o nome "Categoria Teste"
        // When: O nome da categoria é alterado para "Nova Categoria"
        categoria.setNome("Nova Categoria");

        // Then: O nome da categoria deve ser "Nova Categoria"
        assertEquals("Nova Categoria", categoria.getNome());
    }

    @Test
    void testCategoriaDescricaoChangeBDD() {
        // Given: Uma categoria com a descrição "Descrição da categoria"
        // When: A descrição da categoria é alterada para "Nova Descrição"
        categoria.setDescricao("Nova Descrição");

        // Then: A descrição da categoria deve ser "Nova Descrição"
        assertEquals("Nova Descrição", categoria.getDescricao());
    }
}
