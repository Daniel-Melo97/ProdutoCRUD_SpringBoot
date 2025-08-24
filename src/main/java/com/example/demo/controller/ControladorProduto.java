package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.swaggerAnnotations.DeleteInterfaceSwagger;
import com.example.demo.controller.swaggerAnnotations.GetByIdInterfaceSwagger;
import com.example.demo.controller.swaggerAnnotations.GetInterfaceSwagger;
import com.example.demo.controller.swaggerAnnotations.PostInterfaceSwagger;
import com.example.demo.exceptions.ParametrosInvalidosException;
import com.example.demo.model.Produto;
import com.example.demo.service.ServiceProduto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;






@RestController
public class ControladorProduto {

    @Autowired
    private ServiceProduto serviceProduto;


    @GetMapping("/")
    @GetInterfaceSwagger
    public List<Produto> getMethodName() {
        return serviceProduto.listar();
    }

    @GetMapping("/{id}")
    @GetByIdInterfaceSwagger
    public ResponseEntity<?> getMethodName(@PathVariable @Parameter(description = "Id do produto a ser buscado.") Long id) {
        
        if(id == null || id <= 0){
            throw new ParametrosInvalidosException("O id não pode ser nulo");
        }
        Optional<Produto> produto = serviceProduto.buscar(id);
        
        return ResponseEntity.ok(produto);

    }
    
    @PostMapping("/cadastro")
    @PostInterfaceSwagger
    public ResponseEntity<?> postMethodName(@RequestBody @Valid Produto entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String mensagem = "";
            for (ObjectError erro : bindingResult.getAllErrors()) {
                mensagem = mensagem + erro.getDefaultMessage() + ",";
            }
            mensagem = mensagem.substring(0, mensagem.length()-1);//remove "," que fica no final
            throw new ParametrosInvalidosException(mensagem);
        }

        entity = serviceProduto.cadastrar(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("remover/{id}")
    @DeleteInterfaceSwagger
    public ResponseEntity<?> remover(@PathVariable @Parameter(description = "Id do produto a ser deletado.") Long id){
        if(id == null || id <= 0){
            throw new ParametrosInvalidosException("O id não pode ser nulo");
        }

        serviceProduto.deletar(id);
        return ResponseEntity.ok("Produto removido com sucesso");
    }
    
}
