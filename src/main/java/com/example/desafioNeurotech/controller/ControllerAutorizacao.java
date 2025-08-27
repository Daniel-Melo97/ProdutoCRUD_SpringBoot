package com.example.desafioNeurotech.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafioNeurotech.controller.swaggerAnnotations.LoginUserSwagger;
import com.example.desafioNeurotech.controller.swaggerAnnotations.RegisterUserSwagger;
import com.example.desafioNeurotech.controller.validations.ValidationUtils;
import com.example.desafioNeurotech.exceptions.ExceptionRecursoNaoEncontrado;
import com.example.desafioNeurotech.model.Usuario;
import com.example.desafioNeurotech.security.JwtUtil;
import com.example.desafioNeurotech.service.ServiceUsuario;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@Tag(name="Autenticação")
@RequestMapping("/auth")
public class ControllerAutorizacao {

    private final ValidationUtils validationUtils;

    private final ServiceUsuario serviceUsuario;

    public ControllerAutorizacao(ServiceUsuario serviceUsuario, ValidationUtils validationUtils){
        this.serviceUsuario = serviceUsuario;
        this.validationUtils = validationUtils;
    }

    @PostMapping("/cadastro")//criação de usuários
    @RegisterUserSwagger
    public ResponseEntity<?> cadastro(@RequestBody @Valid Usuario usuario, BindingResult bindingResult) {

        validationUtils.parametrosInvalidos(bindingResult);//retorna erro caso haja dados inválidos

        Usuario newUsuario = serviceUsuario.registrarUsuario(usuario.getUsername(), usuario.getPassword());
        return ResponseEntity.ok(newUsuario);
    }
    
    @PostMapping("/login")//autenticação de usuários
    @LoginUserSwagger
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
       
        Optional<Usuario> usuario = serviceUsuario.buscarPorUsername(request.get("username"));//busca o usuário no BD pelo username informado

        if(usuario.isEmpty()){//verfica se a consulta retornou um usuário válido, caso contrário, retorna erro
            throw new ExceptionRecursoNaoEncontrado("Usuário não encontrado");
        }

       String senha = request.get("password");//senha informada pelo usuário durante a realização do login
       String senhaCriptografada = usuario.get().getPassword(); //senha criptografada salva no BD

       if (usuario.isPresent() &&  this.serviceUsuario.verificarSenha(senha, senhaCriptografada)) {//verifica se a senha informada corresponde a senha criptografada no BD
         String token = JwtUtil.generateToken(usuario.get().getUsername());
         return ResponseEntity.ok(Map.of("token", token));//devolve o token de autenticação
       }
       return ResponseEntity.status(401).body("Credenciais inválidas");//retorna mensagem informando que o login não foi efetuado
    }
    
}
