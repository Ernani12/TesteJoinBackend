package com.example.teste.da.join.controller;

import com.example.teste.da.join.DTO.ProdutoDTO;
import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.model.Produto;
import com.example.teste.da.join.repository.CategoriaRepository;
import com.example.teste.da.join.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/listar")
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping("/salvarP")
    public ResponseEntity<Produto> salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
        // Buscando a categoria no banco de dados usando o id da categoria enviado pelo cliente
        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    
        // Criando o produto e associando a categoria encontrada
        Produto produto = new Produto(
            produtoDTO.getNome(),
            produtoDTO.getDescricao(),
            produtoDTO.getPreco(),
            categoria // A categoria é associada diretamente aqui
        );
    
        // Salvando o produto no banco de dados
        Produto produtoSalvo = produtoService.salvarProduto(produto);
    
        // Retornando o produto salvo como resposta
        return ResponseEntity.ok(produtoSalvo);
    }

    @PutMapping("/updateP/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/deleteP/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarid/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
}
