package com.example.desafioNeurotech.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode estar vazio")
    @Column(name="nome", columnDefinition="varchar(100)", nullable=false, unique=false)
    private String nome;

    @NotNull(message="A descrição não pode ser nula")
    @Column(name="descricao", columnDefinition="varchar(200)", nullable=false, unique=false)
    private String descricao;

    @DecimalMin(value="0.0", inclusive=false, message="O preço deve ser maior que zero")
    @Column(name="preco", columnDefinition="DECIMAL(10,2)")
    private double preco;

    @PositiveOrZero(message="A quantidade em estoque não pode ser negativa")    
    @Column(name="quantida_de_estoque", columnDefinition="INT")
    private int quantidadeEstoque;

    @Column(name = "data_criacao", nullable = false, updatable=false)
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    public void setId(Long id){
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @PrePersist
    protected void OnCreate(){
        this.dataCriacao = new Date();
    }
}
