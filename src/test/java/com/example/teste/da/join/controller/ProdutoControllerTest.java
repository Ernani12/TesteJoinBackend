package com.example.teste.da.join.controller;

import com.example.teste.da.join.DTO.ProdutoDTO;
import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.model.Produto;
import com.example.teste.da.join.services.ProdutoService;
import com.example.teste.da.join.repository.CategoriaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Helper method for ProdutoDTO creation
    private ProdutoDTO createProdutoDTO(String nome, String descricao, BigDecimal preco, Long categoriaId) {
        return new ProdutoDTO(nome, descricao, preco, categoriaId);
    }

    // Helper method for Produto creation
    private Produto createProduto(String nome, String descricao, BigDecimal preco) {
        return new Produto(nome, descricao, preco, new Categoria());
    }

    // Test: Salvar produto válido
    @SuppressWarnings("null")
    @Test
    void salvarProduto_quandoProdutoValido_entaoRetornaProdutoSalvo() {
        ProdutoDTO produtoDTO = createProdutoDTO("Produto1", "Descrição 1", new BigDecimal("1000.00"), 1L);
        Produto produtoSalvo = createProduto("Produto1", "Descrição 1", new BigDecimal("1000.00"));

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(new Categoria()));
        when(produtoService.salvarProduto(any(Produto.class))).thenReturn(produtoSalvo);

        ResponseEntity<Produto> response = produtoController.salvarProduto(produtoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto1", response.getBody().getNome());
    }

   

    // Test: Buscar produto por ID válido
    @SuppressWarnings("null")
    @Test
    void buscarProdutoPorId_quandoProdutoExistente_entaoRetornaProduto() {
        Produto produto = createProduto("Produto1", "Descrição 1", new BigDecimal("100.00"));
        when(produtoService.buscarPorId(1L)).thenReturn(produto);

        ResponseEntity<Produto> response = produtoController.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto1", response.getBody().getNome());
    }

  
    // Test: Listar produtos com resultados
    @Test
    void listarProdutos_quandoExistemProdutos_entaoRetornaLista() {
        Produto produto = createProduto("Produto1", "Descrição 1", new BigDecimal("100.00"));
        when(produtoService.listarProdutos()).thenReturn(List.of(produto));

        List<Produto> response = produtoController.listarProdutos();

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

    // Test: Listar produtos sem resultados
    @Test
    void listarProdutos_quandoNaoExistemProdutos_entaoRetornaListaVazia() {
        when(produtoService.listarProdutos()).thenReturn(List.of());

        List<Produto> response = produtoController.listarProdutos();

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    // Test: Deletar produto com sucesso
    @Test
    void deletarProduto_quandoProdutoExistir_entaoRetornaSucesso() {
        doNothing().when(produtoService).deletarProduto(1L);

        ResponseEntity<Void> response = produtoController.deletarProduto(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    // Test: Deletar produto inexistente
    @Test
    void deletarProduto_quandoProdutoNaoExistir_entaoRetornaErro() {
        doThrow(new RuntimeException("Produto não encontrado")).when(produtoService).deletarProduto(999L);

        Exception exception = assertThrows(RuntimeException.class, () -> produtoController.deletarProduto(999L));

        assertEquals("Produto não encontrado", exception.getMessage());
    }

    @SuppressWarnings("null")
    @Test
    void atualizarProduto_quandoProdutoValido_entaoRetornaProdutoAtualizado() {
        Long produtoId = 1L;
        Produto produtoAtualizado = createProduto("Produto Atualizado", "Nova Descrição", new BigDecimal("200.00"));
        Produto produtoInput = createProduto("Produto Atualizado", "Nova Descrição", new BigDecimal("200.00"));

        when(produtoService.atualizarProduto(produtoId, produtoInput)).thenReturn(produtoAtualizado);

        ResponseEntity<Produto> response = produtoController.atualizarProduto(produtoId, produtoInput);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto Atualizado", response.getBody().getNome());
        assertEquals(new BigDecimal("200.00"), response.getBody().getPreco());
    }

    // Test: Atualizar produto com ID inexistente
    @Test
    void atualizarProduto_quandoProdutoNaoExistir_entaoRetornaErro() {
        Long produtoId = 999L;
        Produto produtoInput = createProduto("Produto Não Existente", "Descrição Inexistente", new BigDecimal("150.00"));

        when(produtoService.atualizarProduto(produtoId, produtoInput)).thenThrow(new RuntimeException("Produto não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> produtoController.atualizarProduto(produtoId, produtoInput));

        assertEquals("Produto não encontrado", exception.getMessage());
    }
}
