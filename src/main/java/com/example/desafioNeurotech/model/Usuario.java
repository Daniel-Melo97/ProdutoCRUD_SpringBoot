package com.example.desafioNeurotech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Nome de usuár90 não pode ser nulo")
    @NotBlank(message="Nome de usuário não pode ser vazio")
    @Size(max=100, message="Nome de usuário não pode ter mais que 100 caracteres")
    @Column(unique=true, nullable=false, columnDefinition="varchar(100)")
    private String username;

    @NotNull(message="Senha não pode ser nula")
    @NotBlank(message="Senha não pode ser vazia")
    @Size(max=200, message="Senha não pode ter mais que 200 caracteres")
    @Column(nullable = false, columnDefinition="varchar(200)")
    private String password;

    //construtores

    public Usuario() {}

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
