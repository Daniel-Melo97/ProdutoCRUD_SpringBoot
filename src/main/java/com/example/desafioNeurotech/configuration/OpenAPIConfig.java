package com.example.desafioNeurotech.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "API para gerenciamento de produtos",
        version = "1.0",
        description = "API contemplando o desafio proposto pela Neurotech"
    )
)
@Configuration
public class OpenAPIConfig {
}

