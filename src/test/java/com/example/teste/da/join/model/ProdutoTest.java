package com.example.teste.da.join.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ProdutoTest {

    private Produto produto;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        // Configurações iniciais antes de cada teste
        categoria = new Categoria("Categoria 1", "Descrição da Categoria", "2024-12-16");
        produto = new Produto("Produto 1", "Descrição do Produto", BigDecimal.valueOf(100.00), categoria);
    }

    // Testes Unitários (5)

    @Test
    public void testProdutoCreationWithPrice() {
        assertNotNull(produto);
        assertEquals("Produto 1", produto.getNome());
        assertEquals("Descrição do Produto", produto.getDescricao());
        assertEquals(BigDecimal.valueOf(100.00), produto.getPreco());
        assertNotNull(produto.getCategoria());
        assertEquals("Categoria 1", produto.getCategoria().getNome());
    }

    @Test
    public void testProdutoCreationWithoutPrice() {
        Produto produtoSemPreco = new Produto("Produto 2", "Descrição sem preço", categoria);
        assertNotNull(produtoSemPreco);
        assertEquals("Produto 2", produtoSemPreco.getNome());
        assertEquals("Descrição sem preço", produtoSemPreco.getDescricao());
        assertNull(produtoSemPreco.getPreco());  // O preço deve ser nulo
    }

    @Test
    public void testSettersAndGetters() {
        produto.setNome("Produto Alterado");
        produto.setDescricao("Descrição Alterada");
        produto.setPreco(BigDecimal.valueOf(150.00));

        assertEquals("Produto Alterado", produto.getNome());
        assertEquals("Descrição Alterada", produto.getDescricao());
        assertEquals(BigDecimal.valueOf(150.00), produto.getPreco());
    }

    @Test
    public void testProdutoCategoria() {
        assertNotNull(produto.getCategoria());
        assertEquals("Categoria 1", produto.getCategoria().getNome());
    }

    @Test
    public void testAlterarCategoriaProduto() {
        Categoria novaCategoria = new Categoria("Categoria 2", "Nova descrição", "2024-12-17");
        produto.setCategoria(novaCategoria);
        
        assertNotNull(produto.getCategoria());
        assertEquals("Categoria 2", produto.getCategoria().getNome());
    }

    // Testes BDD (5)

    @Test
    public void testProdutoCreationWithPriceBDD() {
        // Given: Produto com preço e categoria definidos
        // When: Produto é criado com os parâmetros dados
        // Then: O produto deve ter os valores corretos
        assertNotNull(produto);
        assertEquals("Produto 1", produto.getNome());
        assertEquals("Descrição do Produto", produto.getDescricao());
        assertEquals(BigDecimal.valueOf(100.00), produto.getPreco());
        assertNotNull(produto.getCategoria());
        assertEquals("Categoria 1", produto.getCategoria().getNome());
    }

    @Test
    public void testProdutoWithNullCategoryBDD() {
        // Given: Produto criado com uma categoria nula
        Produto produtoSemCategoria = new Produto("Produto Sem Categoria", "Sem Categoria", BigDecimal.valueOf(50.00), null);

        // When: Verificamos a categoria
        // Then: A categoria do produto deve ser nula
        assertNull(produtoSemCategoria.getCategoria());
    }

    @Test
    public void testProdutoCategoryChangeBDD() {
        // Given: Produto com categoria "Categoria 1"
        Categoria novaCategoria = new Categoria("Categoria 3", "Nova Categoria", "2024-12-16");
        produto.setCategoria(novaCategoria);

        // When: Categoria do produto é alterada
        // Then: O nome da nova categoria do produto deve ser "Categoria 3"
        assertEquals("Categoria 3", produto.getCategoria().getNome());
    }

    @Test
    public void testProdutoWithPriceChangeBDD() {
        // Given: Produto com preço inicial de 100.00
        // When: O preço do produto é alterado para 120.00
        produto.setPreco(BigDecimal.valueOf(120.00));

        // Then: O preço do produto deve ser 120.00
        assertEquals(BigDecimal.valueOf(120.00), produto.getPreco());
    }

    @Test
    public void testProdutoWithoutPriceBDD() {
        // Given: Produto criado sem preço definido
        Produto produtoSemPreco = new Produto("Produto Sem Preço", "Sem preço", null);

        // When: Verificamos o preço do produto
        // Then: O preço do produto deve ser nulo
        assertNull(produtoSemPreco.getPreco());
    }
}
