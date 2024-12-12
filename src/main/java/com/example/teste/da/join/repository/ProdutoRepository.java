package com.example.teste.da.join.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.da.join.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
