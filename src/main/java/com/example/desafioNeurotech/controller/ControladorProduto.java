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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafioNeurotech.controller.swaggerAnnotations.DeleteInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.GetByIdInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.GetInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.PostInterfaceSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.PutInterfaceSwagger;
import com.example.desafioNeurotech.exceptions.ParametrosInvalidosException;
import com.example.desafioNeurotech.exceptions.RecursoNaoEncontradoException;
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
    public List<Produto> listar(@RequestParam(required=false) @Parameter(description="Nome a ser utilizado na busca.") String nome, @RequestParam(defaultValue = "true") @Parameter(description="Booleano que indica se o retorno será ascendente ou descendente, de acordo com o preço do produto.") boolean asc) {
        return serviceProduto.listar(nome, asc);
    }

    @GetMapping("/buscarPorId/{id}")
    @GetByIdInterfaceSwagger
    public ResponseEntity<?> buscarPorId(
            @PathVariable @Parameter(description = "Id do produto a ser buscado.") Long id) {

        if (id == null || id <= 0) {//valida o ID recebido nos parâmetros
            throw new ParametrosInvalidosException("O id não pode ser nulo");
        }
        Optional<Produto> produto = serviceProduto.buscar(id);//realiza a busca

        return ResponseEntity.ok(produto);

    }

    @PostMapping("/cadastrar")
    @PostInterfaceSwagger
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Produto entity, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {//verifica se há erros de validade
            String mensagem = "";
            for (ObjectError erro : bindingResult.getAllErrors()) {//concatena mensagens de erro
                mensagem = mensagem + erro.getDefaultMessage() + ",";
            }
            mensagem = mensagem.substring(0, mensagem.length() - 1);// remove "," que fica no final
            throw new ParametrosInvalidosException(mensagem);//levanta exceção de parâmetros inválidos
        }
        entity.setId(null);//garante que o ID será nulo, para que seja criado um novo registro
        entity = serviceProduto.cadastrar(entity);//realiza cadastro
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/remover/{id}")
    @DeleteInterfaceSwagger
    public ResponseEntity<?> remover(@PathVariable @Parameter(description = "Id do produto a ser deletado.") Long id) {
        if (id == null || id <= 0) {//verifica validade do ID informado
            throw new ParametrosInvalidosException("O id não pode ser nulo");
        }

        serviceProduto.deletar(id);//realiza delete
        return ResponseEntity.ok("Produto removido com sucesso");
    }

    @PutMapping("/atualizar/{id}")
    @PutInterfaceSwagger
    public ResponseEntity<?> atualizarProduto(
            @PathVariable @Parameter(description = "Id do produto a ser atualizado.") Long id,
            @RequestBody @Valid Produto entity, BindingResult bindingResult) {
        if (id == null || id <= 0) {//Verifica validade do ID informado
            throw new ParametrosInvalidosException("O id não pode ser nulo ou menor que 1");
        }

        if (bindingResult.hasErrors()) {//Verifica erros de validação
            StringBuilder mensagem = new StringBuilder();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                mensagem.append(erro.getDefaultMessage()).append(",");
            }
            mensagem.deleteCharAt(mensagem.length() - 1); // remove a última vírgula
            throw new ParametrosInvalidosException(mensagem.toString());
        }

        Optional<Produto> produtoExistente = serviceProduto.buscar(id);//Busca produto existente no Banco de Dados
        if (produtoExistente.isEmpty()) {//Verifica se o produto retornado existe
            throw new RecursoNaoEncontradoException("Produto não encontrado");//Caso não existe, retornar erro informando ao usuário
        }

        // Garante que o ID do produto será mantido como o original
        entity.setId(id);
        Produto produtoAtualizado = serviceProduto.atualizar(id, entity);//realiza Update do produto

        return ResponseEntity.ok(produtoAtualizado);
    }

}
