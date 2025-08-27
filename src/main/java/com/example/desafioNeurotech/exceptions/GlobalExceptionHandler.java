package com.example.desafioNeurotech.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {//classe criada para ter o controle e definição do formato de retorno de erros na API
    
    @ExceptionHandler(ExceptionRecursoNaoEncontrado.class)//define como será retornado os erros de Recursos não encontrados
    public ResponseEntity<Object> handleRecursoNaoEncontrado(ExceptionRecursoNaoEncontrado ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        // body.put("error", "Recurso não encontrado");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceptionParametrosInvalidos.class)//define como será retornado os erros de parâmetros inválidos
    public ResponseEntity<Object> handleParametrosInvalidos(ExceptionParametrosInvalidos ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        // body.put("error", "Parâmetros inválidos");

        String[] errorList = ex.getMessage().split(",");//transforma as mensagens de erro, separadas por vírgula, em lista de string com uma ou várias mensagens de erro

        body.put("message", errorList);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)//Define o retorno de erros genéricos
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        // body.put("error", "Erro interno do servidor");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}