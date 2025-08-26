package com.example.desafioNeurotech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafioNeurotech.exceptions.RecursoNaoEncontradoException;
import com.example.desafioNeurotech.model.Produto;
import com.example.desafioNeurotech.repository.ProdutoRepositorio;

import jakarta.validation.Valid;

@Service
public class ServiceProduto {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;



    public Produto cadastrar(@Valid Produto produto) {

        produto = produtoRepositorio.save(produto);//realiza cadastro
        return produto;

    }

    public void deletar(Long id) {

        if (produtoRepositorio.existsById(id)) {//verfifica existência do ID do produto
            produtoRepositorio.deleteById(id);//caso exista, deletar
        } else {
            throw new RecursoNaoEncontradoException("Produto com id=" + id + " Não encontrado");//caso contrário, informar ao usuário que a operação não foi realizada com sucesso
        }

    }

    public Optional<Produto> buscar(Long id) {
        if (produtoRepositorio.existsById(id)) {//verifica existência do produto pelo ID
            return produtoRepositorio.findById(id);//Caso exista, retorna produto pelo ID
        } else {
            throw new RecursoNaoEncontradoException("Produto com id=" + id + " Não encontrado");// caso contrário, informa ao usuário que a operação não foi realizada com sucesso
        }
    }

    public List<Produto> listar() {
        return produtoRepositorio.findAll();
    }

    public Produto atualizar(Long id, @Valid Produto produtoAtualizado) {
           if (!produtoRepositorio.existsById(id)) {//verifica existência do produto e informa erro, caso não exista
            throw new RecursoNaoEncontradoException("Produto com id=" + id + " não encontrado");
        }

        Produto produtoExistente = produtoRepositorio.findById(id).get();//retorna produto existente na base de dados

        // Atualiza apenas os campos necessários, para garantir que ID e data_criação não sejam alterados
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        return produtoRepositorio.save(produtoExistente);//atualiza o produto
    }

}
