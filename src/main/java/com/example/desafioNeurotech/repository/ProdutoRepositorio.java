package com.example.desafioNeurotech.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafioNeurotech.model.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{
    
    List<Produto> findByNomeContainingIgnoreCase(String nome, Sort sort);//retorna produtos, filtrando por produtos que contenham a palavra pesquisada em seus nomes, também permite ordenação
}
