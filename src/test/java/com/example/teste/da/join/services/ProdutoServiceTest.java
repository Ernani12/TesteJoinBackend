package com.example.teste.da.join.services;


import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.model.Produto;
import com.example.teste.da.join.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// TDD com BDD estilo Given/When/Then
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializa dados de teste
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Eletrônicos");

        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Notebook");
        produto.setDescricao("Descrição do produto");
        produto.setPreco(new BigDecimal("2500.00"));
        produto.setCategoria(categoria);
    }

    // Teste 1: Listar todos os produtos
    @Test
    void listarProdutos_quandoExistiremProdutos_entaoRetornaListaDeProdutos() {
        // GIVEN
        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        // WHEN
        List<Produto> produtos = produtoService.listarProdutos();

        // THEN
        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());
        assertEquals(1, produtos.size());
        assertEquals("Notebook", produtos.get(0).getNome());
        verify(produtoRepository, times(1)).findAll();
    }

    // Teste 2: Salvar um novo produto
    @Test
    void salvarProduto_quandoProdutoValido_entaoRetornaProdutoSalvo() {
        // GIVEN
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        // WHEN
        Produto produtoSalvo = produtoService.salvarProduto(produto);

        // THEN
        assertNotNull(produtoSalvo);
        assertEquals("Notebook", produtoSalvo.getNome());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    // Teste 3: Buscar produto por ID existente
    @Test
    void buscarPorId_quandoProdutoExistir_entaoRetornaProduto() {
        // GIVEN
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // WHEN
        Produto produtoEncontrado = produtoService.buscarPorId(1L);

        // THEN
        assertNotNull(produtoEncontrado);
        assertEquals("Notebook", produtoEncontrado.getNome());
        verify(produtoRepository, times(1)).findById(1L);
    }

    // Teste 4: Buscar produto por ID inexistente
    @Test
    void buscarPorId_quandoProdutoNaoExistir_entaoLancaExcecao() {
        // GIVEN
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        // WHEN / THEN
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> produtoService.buscarPorId(1L));

        assertEquals("Produto não encontrado com id: 1", exception.getMessage());
        verify(produtoRepository, times(1)).findById(1L);
    }

    // Teste 5: Atualizar produto existente
    @Test
    void atualizarProduto_quandoProdutoExistente_entaoRetornaProdutoAtualizado() {
        // GIVEN
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setNome("Notebook Atualizado");
        produtoAtualizado.setDescricao("Nova Descrição");
        produtoAtualizado.setPreco(new BigDecimal("3000.00"));
        produtoAtualizado.setCategoria(categoria);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        // WHEN
        Produto result = produtoService.atualizarProduto(1L, produtoAtualizado);

        // THEN
        assertNotNull(result);
        assertEquals("Notebook Atualizado", result.getNome());
        verify(produtoRepository, times(1)).findById(1L);
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    // Teste 6: Deletar produto existente
    @Test
    void deletarProduto_quandoProdutoExistir_entaoDeletaComSucesso() {
        // GIVEN
        doNothing().when(produtoRepository).deleteById(1L);

        // WHEN
        produtoService.deletarProduto(1L);

        // THEN
        verify(produtoRepository, times(1)).deleteById(1L);
    }

    // Teste 7: Obter ID da categoria pelo produto ID
    @Test
    void getCategoriaIdByProdutoId_quandoProdutoExistir_entaoRetornaCategoriaId() {
        // GIVEN
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // WHEN
        String categoriaId = produtoService.getCategoriaIdByProdutoId(1L);

        // THEN
        assertNotNull(categoriaId);
        assertEquals("1", categoriaId);
        verify(produtoRepository, times(1)).findById(1L);
    }

    // Teste 8: Obter nome da categoria pelo produto ID
    @Test
    void getCategoriaNomeByProdutoId_quandoProdutoExistir_entaoRetornaCategoriaNome() {
        // GIVEN
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // WHEN
        String categoriaNome = produtoService.getCategoriaNomeByProdutoId(1L);

        // THEN
        assertNotNull(categoriaNome);
        assertEquals("Eletrônicos", categoriaNome);
        verify(produtoRepository, times(1)).findById(1L);
    }
}

