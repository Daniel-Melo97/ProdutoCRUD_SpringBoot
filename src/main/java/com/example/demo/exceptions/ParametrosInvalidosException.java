package com.example.demo.exceptions;

public class ParametrosInvalidosException extends RuntimeException {

    public ParametrosInvalidosException(String mensagem) {
        super(mensagem);
    }
    
}