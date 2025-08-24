package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="O nome não pode estar vazio")
    @Size(max=100, min=10, message="O tamanho do nome deve ser maior que 10 caracteres e não pode ultrapassar 100 caracteres")
    @Column(columnDefinition="varchar(100)", nullable=false, unique=false)
    private String nome;

    public void SetId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }
}
