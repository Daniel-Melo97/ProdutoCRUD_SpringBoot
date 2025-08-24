package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.RecursoNaoEncontradoException;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepositorio;

import jakarta.validation.Valid;

@Service
public class ServiceProduto {
    
    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public Produto cadastrar(@Valid Produto produto){
    
        produto = produtoRepositorio.save(produto);
        return produto;
     
        
    }

    public void deletar(Long id){

        if(produtoRepositorio.existsById(id)){
            produtoRepositorio.deleteById(id);
        }else{
            throw new RecursoNaoEncontradoException("Produto com id="+id+" Não encontrado");
        }

    }

    public Optional<Produto> buscar(Long id){
        if(produtoRepositorio.existsById(id)){
            return produtoRepositorio.findById(id);
        }else{
            throw new RecursoNaoEncontradoException("Produto com id="+id+" Não encontrado");
        }
    }

    public List<Produto> listar(){
        return produtoRepositorio.findAll();
    }

    
}
