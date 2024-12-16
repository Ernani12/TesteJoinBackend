package com.example.teste.da.join.services;

import com.example.teste.da.join.model.Produto;
import com.example.teste.da.join.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Método para salvar um novo produto
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Método para atualizar um produto existente pelo seu ID
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setCategoria(produtoAtualizado.getCategoria());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    // Método para deletar um produto pelo seu ID
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    // Método para buscar um produto pelo seu ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    // Método para obter o ID da categoria associada a um produto pelo seu ID
    public String getCategoriaIdByProdutoId(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId).orElse(null);
        if (produto == null) {
            return null;
        }
        return String.valueOf(produto.getCategoria().getId());
    }
    
    // Método para obter o nome da categoria associada a um produto pelo seu ID
    public String getCategoriaNomeByProdutoId(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId).orElse(null);
        if (produto == null) {
            return null;
        }
        return produto.getCategoria().getNome();
    }
}
