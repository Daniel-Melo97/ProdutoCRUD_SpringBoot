package com.example.desafioNeurotech.controller.dto;

import com.example.desafioNeurotech.model.Usuario;

public class UserDto {

    private Long id;
    private String username;

    public UserDto(Usuario usuario){//classe DTO criada apenas para ocultar campo de senha ao retornar dados do usuário
        this.id = usuario.getId();
        this.username = usuario.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    
}
