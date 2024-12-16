package com.example.teste.da.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Endpoint para mensagem de boas-vindas
    @GetMapping("/")
    public String home() {
        return "Hello Join Us";
    }

    // Endpoint para listar todas as categorias
    @GetMapping("/listar")
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    // Endpoint para salvar uma nova categoria
    @PostMapping("/SalvarC")
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaService.salvarCategoria(categoria);
        return ResponseEntity.ok(categoriaSalva);
    }

    // Endpoint para atualizar uma categoria existente
    @PutMapping("updateC/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.atualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    // Endpoint para deletar uma categoria
    @DeleteMapping("deleteC/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para buscar uma categoria por ID
    @GetMapping("listarid/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
