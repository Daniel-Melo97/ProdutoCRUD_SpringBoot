package com.example.desafioNeurotech.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.RecursoNaoEncontradoException;
import com.example.desafioNeurotech.controller.swaggerAnnotations.DeleteInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.GetByIdInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.GetInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.PostInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.PutInterfaceSwagger;
import com.example.desafioNeurotech.exceptions.ParametrosInvalidosException;
import com.example.desafioNeurotech.model.Produto;
import com.example.desafioNeurotech.service.ServiceProduto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController("/produtos")
public class ControladorProduto {

    @Autowired
    private ServiceProduto serviceProduto;

    @GetMapping("/listar")
    @GetInterfaceSwagger
    public List<Produto> listar() {
        return serviceProduto.listar();
    }

    @GetMapping("/buscarPorId/{id}")
    @GetByIdInterfaceSwagger
    public ResponseEntity<?> buscarPorId(
            @PathVariable @Parameter(description = "Id do produto a ser buscado.") Long id) {

        if (id == null || id <= 0) {
            throw new ParametrosInvalidosException("O id não pode ser nulo");
        }
        Optional<Produto> produto = serviceProduto.buscar(id);

        return ResponseEntity.ok(produto);

    }

    @PostMapping("/cadastrar")
    @PostInterfaceSwagger
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Produto entity, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            String mensagem = "";
            for (ObjectError erro : bindingResult.getAllErrors()) {
                mensagem = mensagem + erro.getDefaultMessage() + ",";
            }
            mensagem = mensagem.substring(0, mensagem.length() - 1);// remove "," que fica no final
            throw new ParametrosInvalidosException(mensagem);
        }
        entity.setId(null);
        entity = serviceProduto.cadastrar(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/remover/{id}")
    @DeleteInterfaceSwagger
    public ResponseEntity<?> remover(@PathVariable @Parameter(description = "Id do produto a ser deletado.") Long id) {
        if (id == null || id <= 0) {
            throw new ParametrosInvalidosException("O id não pode ser nulo");
        }

        serviceProduto.deletar(id);
        return ResponseEntity.ok("Produto removido com sucesso");
    }

    @PutMapping("/atualizar/{id}")
    @PutInterfaceSwagger
    public ResponseEntity<?> atualizarProduto(
            @PathVariable @Parameter(description = "Id do produto a ser atualizado.") Long id,
            @RequestBody @Valid Produto entity, BindingResult bindingResult) {
        if (id == null || id <= 0) {
            throw new ParametrosInvalidosException("O id não pode ser nulo ou menor que 1");
        }

        if (bindingResult.hasErrors()) {
            StringBuilder mensagem = new StringBuilder();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                mensagem.append(erro.getDefaultMessage()).append(",");
            }
            mensagem.deleteCharAt(mensagem.length() - 1); // remove a última vírgula
            throw new ParametrosInvalidosException(mensagem.toString());
        }

        Optional<Produto> produtoExistente = serviceProduto.buscar(id);
        if (produtoExistente.isEmpty()) {
            throw new RecursoNaoEncontradoException("Produto não encontrado");
        }

        // Garante que o ID do produto será mantido como o original
        entity.setId(id);
        Produto produtoAtualizado = serviceProduto.atualizar(id, entity);

        return ResponseEntity.ok(produtoAtualizado);
    }

}
