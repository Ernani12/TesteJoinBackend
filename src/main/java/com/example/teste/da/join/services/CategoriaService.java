package com.example.teste.da.join.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.teste.da.join.model.Categoria;
import com.example.teste.da.join.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para listar todas as categorias
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Método para salvar uma nova categoria
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para atualizar uma categoria existente pelo seu ID
    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNome(categoriaAtualizada.getNome());
                    categoria.setDescricao(categoriaAtualizada.getDescricao());
                    categoria.setDataCriacao(categoriaAtualizada.getDataCriacao());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
    }

    // Método para deletar uma categoria pelo seu ID
    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Método para buscar uma categoria pelo seu ID
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }
}
