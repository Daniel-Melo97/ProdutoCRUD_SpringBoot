package com.example.desafioNeurotech.controller.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.desafioNeurotech.exceptions.ExceptionParametrosInvalidos;

@Component
public class ValidationUtils {

    public void parametrosInvalidos(BindingResult bindingResult){//Verifica se há erros nos parâmteros, se houver, devolve exceção, se não, faz nada

        if (bindingResult.hasErrors()) {//verifica se há erros de validade
            String mensagem = "";
            for (ObjectError erro : bindingResult.getAllErrors()) {//concatena mensagens de erro
                mensagem = mensagem + erro.getDefaultMessage() + "#";
            }
            mensagem = mensagem.substring(0, mensagem.length() - 1);// remove "#" que fica no final
            throw new ExceptionParametrosInvalidos(mensagem);//levanta exceção de parâmetros inválidos
        }

    }

    
}
