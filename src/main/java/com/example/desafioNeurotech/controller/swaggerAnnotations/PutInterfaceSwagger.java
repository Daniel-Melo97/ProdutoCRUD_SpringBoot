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
@Operation(
    summary = "Atualiza um produto existente.",//define a descrição da rota de atualização do produto no swagger
    description = "Realiza a atualização de um produto ao informar um ID válido no path e um objeto Produto válido no corpo da requisição."
)
@ApiResponses(value = {//define exemplos de possíveis retornos no swagger, considerando cenários de sucesso e erros
    @ApiResponse(
        responseCode = "200",
        description = "Produto atualizado com sucesso.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Produto.class),
            examples = {
                @ExampleObject(
                    name = "Success Example",
                    value = "{\"id\": 1, \"nome\": \"Cerveja Itaipava 473ml\", \"descricao\": \"Nova descrição\", \"preco\": 5.99}"
                )
            }
        )
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Parâmetros inválidos.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Produto.class),
            examples = {
                @ExampleObject(
                    name = "Validation Error Example",
                    value = "{\"timestamp\": \"2025-08-24T13:36:48.968323\", \"status\": 400, \"error\": \"Parâmetros inválidos\", \"message\": [\"O nome deve ter entre 10 e 100 caracteres\"]}"
                )
            }
        )
    ),
    @ApiResponse(
        responseCode = "404",
        description = "Produto não encontrado.",
        content = @Content(
            mediaType = "application/json",
            examples = {
                @ExampleObject(
                    name = "Not Found Example",
                    value = "{\"timestamp\": \"2025-08-24T13:36:48.968323\", \"status\": 404, \"error\": \"Recurso não encontrado\", \"message\": \"Produto com id=99 não encontrado\"}"
                )
            }
        )
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Erro interno do servidor.",
        content = @Content(
            mediaType = "application/json",
            examples = {
                @ExampleObject(
                    name = "Internal Server Error Example",
                    value = "{\"timestamp\": \"2025-08-24T13:36:48.968323\", \"status\": 500, \"error\": \"Erro interno do servidor\", \"message\": \"Operation failed\"}"
                )
            }
        )
    )
})
public @interface PutInterfaceSwagger {
}

