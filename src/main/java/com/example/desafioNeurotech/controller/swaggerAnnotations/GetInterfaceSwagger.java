package com.example.desafioNeurotech.controller.swaggerAnnotations;


import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.desafioNeurotech.model.Produto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Retorna lista de produtos.", 
           description= "Retorna lista de produtos existentes na base de dados, podendo filtrar pelo nome e ordernar a lista retornada pelo preço(crescente ou decrescente).")//define a descrição do retorno de produtos no swagger
@ApiResponses(value = {//define exemplos de possíveis retornos no swagger, considerando cenários de sucesso e erros
        @ApiResponse(    responseCode = "200",
            description = "Successful response",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Produto.class),
                examples = {
                    @ExampleObject(
                        name = "Success Example",
                        value = "[\n{\"id\": 1, \"nome\": \"Cerveja Itaipava 473ml\", \"descricao\": \"cerveja mediana\", \"preco\": 4.50, \"quantidadeEstoque\": 100, \"dataCriacao\": \"2025-08-26T19:15:10.693Z\"  },\n{\"id\": 2, \"nome\": \"Bola Quadrada\", \"descricao\": \"Bola em formato quadrado\", \"preco\": 4.00, \"quantidadeEstoque\": 10, \"dataCriacao\": \"2024-07-20T19:15:10.693Z\"}\n]"
                    )
                }
            )),
        @ApiResponse(    responseCode = "500",
            description = "Error: response status is 500",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Produto.class),
                examples = {
                    @ExampleObject(
                        name = "Error Example",
                        value = "{\"timestamp\": \"2025-08-24T13:36:48.968323\", \"status\": 500, \"error\": \"Erro interno do servidor\", \"message\": \"Operation failed\"}"
                    )
                }
            ))

        })
public @interface GetInterfaceSwagger {
    
}
