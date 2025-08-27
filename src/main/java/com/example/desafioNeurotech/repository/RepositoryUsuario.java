package com.example.desafioNeurotech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafioNeurotech.model.Usuario;



@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByUsername(String username);//Definindo busca pelo username

}