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
@Operation(summary = "Retorna todos os produtos.", 
           description= "Retorna todos os produtos existentes na base de dados.")
@ApiResponses(value = {
        @ApiResponse(    responseCode = "200",
            description = "Successful response",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Produto.class),
                examples = {
                    @ExampleObject(
                        name = "Success Example",
                        value = "[\n{\"id\": 1, \"nome\": \"Cerveja Itaipava 473ml\"},\n{\"id\": 1, \"nome\": \"Bola Quadrada\"}\n]"
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
