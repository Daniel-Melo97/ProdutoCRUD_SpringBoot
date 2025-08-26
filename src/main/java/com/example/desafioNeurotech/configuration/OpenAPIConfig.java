package com.example.desafioNeurotech.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "API para gerenciamento de produtos",
        version = "1.0",
        description = "API contemplando o desafio proposto pela Neurotech"
    )
)
@Configuration
public class OpenAPIConfig {//define informações da API que serão exibidas no Swagger, para melhor apresentação
}

