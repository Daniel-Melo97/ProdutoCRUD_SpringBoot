package com.example.desafioNeurotech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuario")
public class Usuario {

    //atributos e annotations de validação
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Nome de usuário não pode ser nulo")
    @NotBlank(message="Nome de usuário não pode ser vazio")
    @Size(max=100, message="Nome de usuário não pode ter mais que 100 caracteres")
    @Pattern(regexp = "^(?!\\s).*$", message = "Nome de usuário não pode iniciar com espaço em branco")
    @Pattern(regexp = "^(?!.*\\s$).*$", message = "Nome de usuário não pode terminar com espaço em branco")
    @Pattern(regexp="^[a-zA-Z][a-zA-Z0-9._]{2,99}$", message="Nome de usuário deve iniciar com letra, pode ter apenas letras, números e '_' e '.'")
    @Column(unique=true, nullable=false, columnDefinition="varchar(100)")
    private String username;

    @NotNull(message="Senha não pode ser nula")
    @NotBlank(message="Senha não pode ser vazia")
    @Size(max=200, message="Senha não pode ter mais que 200 caracteres")
    @Pattern(regexp = "^(?!\\s).*$", message = "Senha não pode iniciar com espaço em branco")
    @Pattern(regexp = "^(?!.*\\s$).*$", message = "Senha não pode terminar com espaço em branco")
    @Pattern(regexp=".*[^a-zA-Z0-9\\s].*", message="Senha não possui caracter especial")
    @Pattern(regexp=".*[A-Z].*", message="Senha não possui letras maiúsculas")
    @Pattern(regexp=".*[a-z].*", message="Senha não possui letras minúsculas")
    @Pattern(regexp=".*[0-9].*", message="Senha não possui números")
    @Size(min=8, message="Senha deve ter pelo menos 8 caracteres")
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

    public void setId(Long id){
        this.id = id;
    }
}
