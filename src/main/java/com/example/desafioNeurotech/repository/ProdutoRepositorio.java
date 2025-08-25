package com.example.desafioNeurotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafioNeurotech.model.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{
    
}
