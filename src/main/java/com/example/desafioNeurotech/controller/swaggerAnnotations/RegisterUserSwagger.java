package com.example.desafioNeurotech.controller.swaggerAnnotations;


import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.desafioNeurotech.model.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Realiza o cadastro de usuários.", //define a descrição da rota de cadastro no swagger
           description= "Ao enviar um objeto do tipo Usuário, contendo o nome de usuário e senha forte, ele será cadastrado")
@ApiResponses(value = {//define exemplos de possíveis retornos no swagger, considerando cenários de sucesso e erros
        @ApiResponse(    responseCode = "200",
            description = "Successful response",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class),
                examples = {
                    @ExampleObject(
                        name = "Success Example",
                        value = "{\"id\": 1, \"username\": \"exemplo.usuario23\"}"
                    )
                }
            )),
        @ApiResponse(    responseCode = "500",
            description = "Error: response status is 500",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class),
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
                schema = @Schema(implementation = Usuario.class),
                examples = {
                    @ExampleObject(
                        name = "Validation Error Example",
                        value = "{\"timestamp\": \"2025-08-24T13:36:48.968323\", \"status\": 400, \"error\": \"Parâmetros inválidos\", \"message\": [\n\"Senha não possui números\"\n]}"
                    )
                }
            ))

        })
public @interface RegisterUserSwagger {

}
