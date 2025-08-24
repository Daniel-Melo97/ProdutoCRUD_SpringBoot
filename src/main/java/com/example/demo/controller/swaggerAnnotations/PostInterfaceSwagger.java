package com.example.demo.controller.swaggerAnnotations;


import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.model.Produto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Realiza o cadastro ou atualização de um produto.", 
           description= "Ao enviar um objeto do tipo Produto, contendo o nome, sendo maior que 10 caracteres e máximo de 100, ele será cadastrado, ao informar um ID, ele realiza atualização de um produto já existente.")
@ApiResponses(value = {
        @ApiResponse(    responseCode = "200",
            description = "Successful response",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Produto.class),
                examples = {
                    @ExampleObject(
                        name = "Success Example",
                        value = "{\"id\": 1, \"nome\": \"Cerveja Itaipava 473ml\"}"
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
            )),
        @ApiResponse(    responseCode = "400",
            description = "Error: response status is 400",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Produto.class),
                examples = {
                    @ExampleObject(
                        name = "Validation Error Example",
                        value = "{\"timestamp\": \"2025-08-24T13:36:48.968323\", \"status\": 400, \"error\": \"Parâmetros inválidos\", \"message\": [\n\"O tamanho do nome deve ser maior que 10 caracteres e não pode ultrapassar 100 caracteres\"\n]}"
                    )
                }
            ))

        })
public @interface PostInterfaceSwagger {

}
