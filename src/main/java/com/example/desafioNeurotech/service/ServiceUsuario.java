package com.example.desafioNeurotech.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.desafioNeurotech.model.Usuario;
import com.example.desafioNeurotech.repository.RepositoryUsuario;

@Service
public class ServiceUsuario {

      private final RepositoryUsuario repositoryUsuario;
    private final PasswordEncoder passwordEncoder;

    public ServiceUsuario(RepositoryUsuario repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario registrarUsuario(String username, String password) {
        String senhaCriptografada = passwordEncoder.encode(password);//criptografando para salvar com senha criptografada
        Usuario usuario = new Usuario(username, senhaCriptografada);
        return repositoryUsuario.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username) {//retorna usuário cujo username seja igual
        return repositoryUsuario.findByUsername(username);
    }

    public boolean verificarSenha(String senha, String senhaCriptografada){//retorna True se a senha informada corresponder a senha criptografada no BD, false caso contrário
        return this.passwordEncoder.matches(senha, senhaCriptografada);
    }
    
}
