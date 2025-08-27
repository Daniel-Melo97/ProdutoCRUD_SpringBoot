package com.example.desafioNeurotech.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.desafioNeurotech.model.Usuario;
import com.example.desafioNeurotech.repository.RepositoryUsuario;

@Service
public class UsuarioDetailsService implements UserDetailsService{

     private final RepositoryUsuario repositoryUsuario;

    public UsuarioDetailsService(RepositoryUsuario repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        Usuario usuario = repositoryUsuario.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
       
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
    
}
