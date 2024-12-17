package com.example.teste.da.join.services;


import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configura uma categoria para uso nos testes
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Categoria de produtos eletrônicos");
    }

    @Test
    void testListarCategorias() {
        // Simula o comportamento do repositório
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));

        // Chama o método do serviço
        List<Categoria> categorias = categoriaService.listarCategorias();

        // Verifica os resultados
        assertNotNull(categorias);
        assertEquals(1, categorias.size());
        assertEquals("Eletrônicos", categorias.get(0).getNome());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void testSalvarCategoria() {
        // Simula o comportamento do save
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        // Chama o método
        Categoria resultado = categoriaService.salvarCategoria(categoria);

        // Verificações
        assertNotNull(resultado);
        assertEquals("Eletrônicos", resultado.getNome());
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    void testBuscarPorIdExistente() {
        // Simula encontrar a categoria pelo ID
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        // Chama o método
        Optional<Categoria> resultado = categoriaService.buscarPorId(1L);

        // Verificações
        assertTrue(resultado.isPresent());
        assertEquals("Eletrônicos", resultado.get().getNome());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdInexistente() {
        // Simula não encontrar a categoria
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        // Chama o método
        Optional<Categoria> resultado = categoriaService.buscarPorId(1L);

        // Verificações
        assertFalse(resultado.isPresent());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testAtualizarCategoriaExistente() {
        // Categoria atualizada
        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setNome("Nova Categoria");
        categoriaAtualizada.setDescricao("Nova descrição");

        // Simula o comportamento do repositório
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaAtualizada);

        // Chama o método
        Categoria resultado = categoriaService.atualizarCategoria(1L, categoriaAtualizada);

        // Verificações
        assertNotNull(resultado);
        assertEquals("Nova Categoria", resultado.getNome());
        verify(categoriaRepository, times(1)).findById(1L);
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    void testAtualizarCategoriaInexistente() {
        // Categoria não encontrada
        Categoria categoriaAtualizada = new Categoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifica a exceção
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoriaService.atualizarCategoria(1L, categoriaAtualizada);
        });

        assertEquals("Categoria não encontrada com id: 1", exception.getMessage());
        verify(categoriaRepository, times(1)).findById(1L);
        verify(categoriaRepository, never()).save(any());
    }

    @Test
    void testDeletarCategoria() {
        // Simula o comportamento do delete
        doNothing().when(categoriaRepository).deleteById(1L);

        // Chama o método
        categoriaService.deletarCategoria(1L);

        // Verificações
        verify(categoriaRepository, times(1)).deleteById(1L);
    }
}
