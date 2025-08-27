package com.example.desafioNeurotech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.desafioNeurotech.exceptions.ExceptionRecursoNaoEncontrado;
import com.example.desafioNeurotech.model.Produto;
import com.example.desafioNeurotech.repository.RepositoryProduto;

import jakarta.validation.Valid;

@Service
public class ServiceProduto {

    
    private final RepositoryProduto repositoryProduto;

    public ServiceProduto(RepositoryProduto repositoryProduto){
        this.repositoryProduto = repositoryProduto;
    }



    public Produto cadastrar(@Valid Produto produto) {

        produto.setNome(produto.getNome().trim());//aplica a função trim() para remover espaços no início e no fim
        produto.setDescricao(produto.getDescricao().trim());
        
        produto = repositoryProduto.save(produto);//realiza cadastro
        return produto;

    }

    public void deletar(Long id) {

        if (repositoryProduto.existsById(id)) {//verfifica existência do ID do produto
            repositoryProduto.deleteById(id);//caso exista, deletar
        } else {
            throw new ExceptionRecursoNaoEncontrado("Produto com id=" + id + " Não encontrado");//caso contrário, informar ao usuário que a operação não foi realizada com sucesso
        }

    }

    public Optional<Produto> buscar(Long id) {
        if (repositoryProduto.existsById(id)) {//verifica existência do produto pelo ID
            return repositoryProduto.findById(id);//Caso exista, retorna produto pelo ID
        } else {
            throw new ExceptionRecursoNaoEncontrado("Produto com id=" + id + " Não encontrado");// caso contrário, informa ao usuário que a operação não foi realizada com sucesso
        }
    }

    public List<Produto> listar(String nome, boolean asc) {

        Sort sort = asc ? Sort.by("preco").ascending() : Sort.by("preco").descending();//verifica se a ordenação será ascendente ou descendente
        //se asc = true -> ordena de forma ascendente(crescente)
        //se asc = false -> ordena de forma descendente(decrescente)

        if(nome == null || nome.isEmpty()){//caso a pesquisa esteja vazia, retornar todos
            return repositoryProduto.findAll(sort);
        }else{//se não, filtrar por produtos que contenham a string de busca em seus nomes
            return repositoryProduto.findByNomeContainingIgnoreCase(nome, sort);
        }
        
    }

    public Produto atualizar(Long id, @Valid Produto produtoAtualizado) {

           if (!repositoryProduto.existsById(id)) {//verifica existência do produto e informa erro, caso não exista
            throw new ExceptionRecursoNaoEncontrado("Produto com id=" + id + " não encontrado");
        }

        Produto produtoExistente = repositoryProduto.findById(id).get();//retorna produto existente na base de dados

        // Atualiza apenas os campos necessários, para garantir que ID e data_criação não sejam alterados
        produtoExistente.setNome(produtoAtualizado.getNome().trim());//aplica a função trim() para remover espaços no início e no fim
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao().trim());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        return repositoryProduto.save(produtoExistente);//atualiza o produto
    }

}
