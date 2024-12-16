package com.example.teste.da.join;

import com.example.teste.da.join.controller.CategoriaController;
import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria("Categoria Teste", "Descrição", "2024-12-16");
    }

    @Test
    void testHome() {
        String response = categoriaController.home();
        assertEquals("Hello Join Us", response);
    }

    @Test
    void testListarCategorias() {
        List<Categoria> categorias = Arrays.asList(categoria);
        when(categoriaService.listarCategorias()).thenReturn(categorias);

        List<Categoria> response = categoriaController.listarCategorias();
        assertEquals(1, response.size());
        assertEquals("Categoria Teste", response.get(0).getNome());
    }

    @SuppressWarnings("null")
    @Test
    void testSalvarCategoria() {
        when(categoriaService.salvarCategoria(categoria)).thenReturn(categoria);

        ResponseEntity<Categoria> response = categoriaController.salvarCategoria(categoria);
        assertEquals(200, response.getStatusCode().value()); // Atualizado para usar getStatusCode().value()
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo.");
        assertEquals("Categoria Teste", response.getBody().getNome());
    }

    @SuppressWarnings("null")
    @Test
    void testAtualizarCategoria() {
        when(categoriaService.atualizarCategoria(1L, categoria)).thenReturn(categoria);

        ResponseEntity<Categoria> response = categoriaController.atualizarCategoria(1L, categoria);
        assertEquals(200, response.getStatusCode().value()); // Atualizado para usar getStatusCode().value()
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo.");
        assertEquals("Categoria Teste", response.getBody().getNome());
    }

    @Test
    void testDeletarCategoria() {
        doNothing().when(categoriaService).deletarCategoria(1L);

        ResponseEntity<Void> response = categoriaController.deletarCategoria(1L);
        assertEquals(204, response.getStatusCode().value()); // Atualizado para usar getStatusCode().value()
    }

    @SuppressWarnings("null")
    @Test
    void testBuscarPorIdCategoria() {
        when(categoriaService.buscarPorId(1L)).thenReturn(Optional.of(categoria));

        ResponseEntity<Categoria> response = categoriaController.buscarPorId(1L);
        assertEquals(200, response.getStatusCode().value()); // Atualizado para usar getStatusCode().value()
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo.");
        assertEquals("Categoria Teste", response.getBody().getNome());
    }

    @Test
    void testBuscarPorIdCategoriaNotFound() {
        when(categoriaService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Categoria> response = categoriaController.buscarPorId(1L);
        assertEquals(404, response.getStatusCode().value()); // Atualizado para usar getStatusCode().value()
        assertNull(response.getBody(), "O corpo da resposta deve ser nulo quando não encontrar a categoria.");
    }
}
