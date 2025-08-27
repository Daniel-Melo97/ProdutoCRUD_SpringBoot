package com.example.desafioNeurotech.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        title = "API para gerenciamento de produtos",
        version = "1.0",
        description = "API contemplando o desafio proposto pela Neurotech"
    )
)
@Configuration
public class OpenAPIConfig {//define informações da API que serão exibidas no Swagger, para melhor apresentação

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()//definindo autenticação no Swagger, para permitir enviar solicitações para rotas protegidas
                .addSecuritySchemes("bearerAuth", new SecurityScheme() 
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT") 
                    .in(SecurityScheme.In.HEADER)
                    .description("JWT Token for authentication")))
            
            ;
    }

}

