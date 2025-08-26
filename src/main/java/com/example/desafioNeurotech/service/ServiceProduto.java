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

        produto = produtoRepositorio.save(produto);
        return produto;

    }

    public void deletar(Long id) {

        if (produtoRepositorio.existsById(id)) {
            produtoRepositorio.deleteById(id);
        } else {
            throw new RecursoNaoEncontradoException("Produto com id=" + id + " Não encontrado");
        }

    }

    public Optional<Produto> buscar(Long id) {
        if (produtoRepositorio.existsById(id)) {
            return produtoRepositorio.findById(id);
        } else {
            throw new RecursoNaoEncontradoException("Produto com id=" + id + " Não encontrado");
        }
    }

    public List<Produto> listar() {
        return produtoRepositorio.findAll();
    }

    public Produto atualizar(Long id, @Valid Produto produtoAtualizado) {
        Optional<Produto> produtoExistenteOptional = produtoRepositorio.findById(id);

        if (produtoExistenteOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Produto com id=" + id + " não encontrado");
        }

        Produto produtoExistente = produtoExistenteOptional.get();

        // Atualiza apenas os campos necessários
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        return produtoRepositorio.save(produtoExistente);
    }

}
